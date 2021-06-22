package in.siva.vegapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.siva.vegapp.dao.OrderRepository;
import in.siva.vegapp.dao.OrderedVegRepository;
import in.siva.vegapp.dto.OrderDetailDTO;
import in.siva.vegapp.dto.OrderDetailWithVegDTO;
import in.siva.vegapp.dto.OrderItemDTO;
import in.siva.vegapp.model.OrderDetail;
import in.siva.vegapp.model.OrderItem;

@Service
public class OrderService {
	@Autowired
	OrderItemDTO orderItemDTO;
	@Autowired
	OrderRepository orderRepo;
	@Autowired
	OrderedVegRepository orderedVegRepository;
	@Autowired
	OrderDetailWithVegDTO orderDetailWithVegDTO;
	
	/**
	 * This method is used to store order details
	 * @param orderDetail
	 */
	public void storeOrder(OrderDetail orderDetail) {
		OrderDetail savedOrder = orderRepo.save(orderDetail);		
		List<OrderItem> vegetables = orderItemDTO.getOrderedVegetables();
		List<OrderItem> vegetablesWithOrderId = orderItemDTO.setOrderIdToVeg(savedOrder.getOrderId(), vegetables);
		orderedVegRepository.saveAll((Iterable<OrderItem>)vegetablesWithOrderId);
		orderItemDTO.removeAll();
	}
	
	/**
	 * This method is used to get order details of a specific user 
	 * @param userId
	 * @return
	 */
	public List<OrderDetailDTO> getMyOrders(Integer userId) {	
		List<OrderDetail> orders = orderRepo.findByUserId(userId);
		return setOrdersWithVeg(orders);
	}
	
	/**
	 * This method is used to get current date delivery orders
	 * @return
	 */
	public List<OrderDetailDTO> getDeliveryOrders(){
		List<OrderDetail> orders = orderRepo.findForDelivery(LocalDate.now());
		return setOrdersWithVeg(orders);
	}
	
	/**
	 * This method is used to get all order details
	 * @return
	 */
	public List<OrderDetailDTO> getAllOrders(){
		List<OrderDetail> orders = (List<OrderDetail>)orderRepo.findAll();
		return setOrdersWithVeg(orders);
	}
	
	/**
	 * This method is used to set vegetable details to appropriate orders  
	 * @param orders
	 * @return
	 */
	private List<OrderDetailDTO> setOrdersWithVeg(List<OrderDetail> orders){
		List<OrderDetailDTO> orderDetails = new ArrayList<>();
		for (OrderDetail order : orders) {
			List<OrderItem> vegetables = orderedVegRepository.findAllByOrderId(order.getOrderId());
			OrderDetailDTO orderDetail = orderDetailWithVegDTO.setVegToOrder(order, vegetables);
			orderDetails.add(orderDetail);
		}
		return orderDetails;
	}
}
