package com.store.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.store.domain.Address;
import com.store.domain.City;
import com.store.domain.Client;
import com.store.domain.enums.ClientType;
import com.store.domain.enums.Profile;
import com.store.dto.ClientDTO;
import com.store.dto.ClientNewDTO;
import com.store.repositories.AddressRepository;
import com.store.repositories.ClientRepository;
import com.store.resources.exceptions.AuthorizationException;
import com.store.resources.exceptions.DataIntegrityException;
import com.store.resources.exceptions.ObjectNotFoundException;
import com.store.security.UserSS;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private S3Service s3Service;

	// Find All
	public List<Client> findAll() {
		return repo.findAll();
	}

	// Find By Id
	public Client find(Integer id) {
		
		// Get logged in user and check
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Access denied");
		}
		
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));
	}

	// Save
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repo.save(obj);
		addressRepository.saveAll(obj.getAddresses());
		return obj;
	}

	// Update
	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	// Delete
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It's not possible to delete a client that has orders.");
		}
	}

	// Import objects - Pagination
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// Conversion
	public Client fromDTO(ClientDTO objDTO) {
		return new Client(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), null, null, null);
	}
	
	public Client fromDTO(ClientNewDTO objDTO) {
		Client client = new Client(null, objDTO.getName(), objDTO.getEmail(), objDTO.getCpfOrCnpj(), ClientType.toEnum(objDTO.getType()), pe.encode(objDTO.getPassword()));
		City city = new City(objDTO.getCityId(), null, null);
		Address address = new Address(null, objDTO.getStreet(), objDTO.getNumber(), objDTO.getComplement(),
				objDTO.getNeighborhood(), objDTO.getZipcode(), client, city);
		client.getAddresses().add(address);
		client.getPhones().add(objDTO.getPhone1());
		if (objDTO.getPhone2() != null) {
			client.getPhones().add(objDTO.getPhone2());
		}
		if (objDTO.getPhone3() != null) {
			client.getPhones().add(objDTO.getPhone3());
		}
		return client;
	}
	
	
	//UpdateData
	public void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	//Upload client photo
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Access denied");
		}
		
		URI uri = s3Service.uploadFile(multipartFile);
		
		Client cli = repo.findByEmail(user.getUsername());
		cli.setImageUrl(uri.toString());
		repo.save(cli);
		
		return uri;
	}

}
