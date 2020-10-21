package com.store.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.store.domain.Address;
import com.store.domain.BankSlipPayment;
import com.store.domain.CardPayment;
import com.store.domain.Category;
import com.store.domain.City;
import com.store.domain.Client;
import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.domain.Payment;
import com.store.domain.Product;
import com.store.domain.State;
import com.store.domain.enums.ClientType;
import com.store.domain.enums.PaymentState;
import com.store.domain.enums.Profile;
import com.store.repositories.AddressRepository;
import com.store.repositories.CategoryRepository;
import com.store.repositories.CityRepository;
import com.store.repositories.ClientRepository;
import com.store.repositories.OrderItemRepository;
import com.store.repositories.OrderRepository;
import com.store.repositories.PaymentRepository;
import com.store.repositories.ProductRepository;
import com.store.repositories.StateRepository;

@Service
public class DbService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository; 
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void instatiateTestDatabase() throws ParseException {
		
		Category cat1 = new Category(null, "Computers");
		Category cat2 = new Category(null, "Office");
		Category cat3 = new Category(null, "Household");
		Category cat4 = new Category(null, "Electronics");
		Category cat5 = new Category(null, "Gardening");
		Category cat6 = new Category(null, "Decor");
		Category cat7 = new Category(null, "Health and Personal Care");
		
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Desk Table", 300.00);
		Product p5 = new Product(null, "Towel", 50.00);
		Product p6 = new Product(null, "Quilt", 200.00);
		Product p7 = new Product(null, "TV True Color", 1200.00);
		Product p8 = new Product(null, "Brush Cutter", 800.00);
		Product p9 = new Product(null, "Lamp", 100.00);
		Product p10 = new Product(null, "Pending", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);
		
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));
		
		
		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));
		
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
		
		
		State s1 = new State(null, "Minas Gerais");
		State s2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia", s1);
		City c2 = new City(null, "São Paulo", s2);
		City c3 = new City(null, "Campinas", s2);
		
		s1.getCities().addAll(Arrays.asList(c1));
		s2.getCities().addAll(Arrays.asList(c2,c3));
		
		
		stateRepository.saveAll(Arrays.asList(s1,s2));
		cityRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
		Client cli1 = new Client(null, "Maria Silva", "danieladiasaraujo@hotmail.com", "36378912377", ClientType.PESSOAFISICA,pe.encode("123"));
		cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));
		
		Client cli2 = new Client(null, "Ana Costa", "anacosta@gmail.com", "90779307011", ClientType.PESSOAFISICA,pe.encode("123"));
		cli1.getPhones().addAll(Arrays.asList("93883321", "34252625"));
		cli2.addProfile(Profile.ADMIN);
		
		Address ad1 = new Address(null, "Rua Flores", "300", "Apt 203", "Jardim", "38220834", cli1, c1);
		Address ad2 = new Address(null, "Av Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		Address ad3 = new Address(null, "Av Floriano", "2106", null, "Centro", "281777012", cli2, c2);
		
		cli1.getAddresses().addAll(Arrays.asList(ad1, ad2));
		cli2.getAddresses().addAll(Arrays.asList(ad3));		
		
		clientRepository.saveAll(Arrays.asList(cli1, cli2));
		addressRepository.saveAll(Arrays.asList(ad1, ad2, ad3));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Order ord1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, ad1);
		Order ord2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli1, ad2);
		
		Payment pay1 = new CardPayment(null, PaymentState.PAID, ord1, 6);
		ord1.setPayment(pay1);
		Payment pay2 = new BankSlipPayment(null, PaymentState.PENDING, ord2, sdf.parse("20/10/2017 00:00"), null);
		ord2.setPayment(pay2);
		
		cli1.getOrders().addAll(Arrays.asList(ord1, ord2));
		
		
		orderRepository.saveAll(Arrays.asList(ord1, ord2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		
		
		OrderItem oi1 = new OrderItem(ord1, p1, 0, 1, p1.getPrice());
		OrderItem oi2 = new OrderItem(ord1, p3, 0, 2, p3.getPrice());
		OrderItem oi3 = new OrderItem(ord2, p2, 100, 1, p2.getPrice());
		
		ord1.getItens().addAll(Arrays.asList(oi1,oi2));
		ord2.getItens().addAll(Arrays.asList(oi3));
		
		p1.getItens().addAll(Arrays.asList(oi1));
		p2.getItens().addAll(Arrays.asList(oi3));
		p3.getItens().addAll(Arrays.asList(oi2));
		
		
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3));	
		
	}

}
