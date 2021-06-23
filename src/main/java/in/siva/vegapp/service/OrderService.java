package in.siva.vegapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.siva.vegapp.dao.OrderRepository;
import in.siva.vegapp.dao.OrderedVegRepository;
import in.siva.vegapp.dto.OrderDTO;
import in.siva.vegapp.dto.OrderWithVegDTO;
import in.siva.vegapp.dto.OrderedVegDTO;
import in.siva.vegapp.model.Order;
import in.siva.vegapp.model.OrderedVeg;

@Service
public class OrderService {
	@Autowired
	OrderedVegDTO orderItemDTO;
	@Autowired
	OrderRepository orderRepo;
	@Autowired
	OrderedVegRepository orderedVegRepository;
	@Autowired
	OrderWithVegDTO orderDetailWithVegDTO;
	@Autowired
	VegService vegService;
	
	/**
	 * This method is used to store order details
	 * @param orderDetail
	 */
	public void storeOrder(Order orderDetail) {
		Order savedOrder = orderRepo.save(orderDetail);		
		List<OrderedVeg> vegetables = orderItemDTO.getOrderedVegetables();
		List<OrderedVeg> vegetablesWithOrderId = orderItemDTO.setOrderIdToVeg(savedOrder.getOrderId(), vegetables);
		orderedVegRepository.saveAll((Iterable<OrderedVeg>)vegetablesWithOrderId);
		vegService.updateStock(vegetables);
		orderItemDTO.removeAll();
	}
	
	/**
	 * This method is used to get order details of a specific user 
	 * @param userId
	 * @return
	 */
	public List<OrderDTO> getMyOrders(Integer userId) {	
		List<Order> orders = orderRepo.findByUserId(userId);
		return setOrdersWithVeg(orders);
	}
	
	/**
	 * This method is used to get current date delivery orders
	 * @return
	 */
	public List<OrderDTO> getDeliveryOrders(){
		List<Order> orders = orderRepo.findForDelivery(LocalDate.now());
		return setOrdersWithVeg(orders);
	}
	
	/**
	 * This method is used to get all order details
	 * @return
	 */
	public List<OrderDTO> getAllOrders(){
		List<Order> orders = (List<Order>)orderRepo.findAll();
		return setOrdersWithVeg(orders);
	}
	
	/**
	 * This method is used to set vegetable details to appropriate orders  
	 * @param orders
	 * @return
	 */
	private List<OrderDTO> setOrdersWithVeg(List<Order> orders){
		List<OrderDTO> orderDetails = new ArrayList<>();
		for (Order order : orders) {
			List<OrderedVeg> vegetables = orderedVegRepository.findAllByOrderId(order.getOrderId());
			OrderDTO orderDetail = orderDetailWithVegDTO.setVegToOrder(order, vegetables);
			orderDetails.add(orderDetail);
		}
		return orderDetails;
	}
}
