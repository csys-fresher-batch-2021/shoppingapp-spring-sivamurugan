package in.siva.vegapp.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import in.siva.vegapp.model.OrderDetail;
import in.siva.vegapp.model.OrderItem;

@Component
public class OrderDetailWithVegDTO {

	/**
	 * This method is used to store list of vegetable details with respect to their
	 * specific order
	 * 
	 * @param orderDetail
	 * @param orderItems
	 * @return
	 */
	public OrderDetailDTO setVegToOrder(OrderDetail orderDetail, List<OrderItem> orderItems) {
		OrderDetailDTO orderDetailsWithVeg = new OrderDetailDTO();
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
