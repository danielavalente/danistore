package com.store.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.domain.BankSlipPayment;
import com.store.domain.Client;
import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.domain.enums.PaymentState;
import com.store.repositories.OrderItemRepository;
import com.store.repositories.OrderRepository;
import com.store.repositories.PaymentRepository;
import com.store.resources.exceptions.AuthorizationException;
import com.store.resources.exceptions.ObjectNotFoundException;
import com.store.security.UserSS;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private BankSlipService bankSlipService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmailService emailService;

	// Find All
	public List<Order> findAll() {
		return repo.findAll();
	}

	// Find By Id
	public Order find(Integer id) {
		Optional<Order> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Order.class.getName()));
	}
	
	// Add
	@Transactional
	public Order insert(Order obj) {
		obj.setId(null);
		obj.setInstance(new Date());
		obj.setClient(clientService.find(obj.getClient().getId()));
		obj.getPayment().setState(PaymentState.PENDING);
		obj.getPayment().setOrder(obj);
		if (obj.getPayment() instanceof BankSlipPayment) {
			BankSlipPayment pay = (BankSlipPayment) obj.getPayment();
			bankSlipService.fillBankSlipDate(pay, obj.getInstance());
		}
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for (OrderItem ip : obj.getItens()) {
			ip.setDiscount(0);
			ip.setProduct(productService.find(ip.getProduct().getId()));
			ip.setPrice(ip.getProduct().getPrice());
			ip.setOrder(obj);
		}
		orderItemRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
	
	// Import objects - Pagination
		public Page<Order> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
			UserSS user = UserService.authenticated();
			if (user == null) {
				throw new AuthorizationException("Access denied");
			}
			
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			Client client = clientService.find(user.getId());
			return repo.findByClient(client, pageRequest);
		}
	
}
