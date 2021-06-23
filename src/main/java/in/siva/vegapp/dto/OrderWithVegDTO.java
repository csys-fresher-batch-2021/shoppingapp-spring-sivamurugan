package in.siva.vegapp.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import in.siva.vegapp.model.Order;
import in.siva.vegapp.model.OrderedVeg;

@Component
public class OrderWithVegDTO {

	/**
	 * This method is used to store list of vegetable details with respect to their
	 * specific order
	 * 
	 * @param orderDetail
	 * @param orderItems
	 * @return
	 */
	public OrderDTO setVegToOrder(Order orderDetail, List<OrderedVeg> orderItems) {
		OrderDTO orderDetailsWithVeg = new OrderDTO();
		orderDetailsWithVeg.setActive(orderDetail.getActive());
		orderDetailsWithVeg.setAddress(orderDetail.getAddress());
		orderDetailsWithVeg.setPaymentMethod(orderDetail.getPaymentMethod());
		orderDetailsWithVeg.setStatus(orderDetail.getStatus());
		orderDetailsWithVeg.setUserId(orderDetail.getUserId());
		orderDetailsWithVeg.setCreatedDate(orderDetail.getCreatedDate());
		orderDetailsWithVeg.setDeliveryDate(orderDetail.getDeliveryDate());
		orderDetailsWithVeg.setOrderId(orderDetail.getOrderId());
		orderDetailsWithVeg.setOrderItems(orderItems);
		return orderDetailsWithVeg;
	}
}
