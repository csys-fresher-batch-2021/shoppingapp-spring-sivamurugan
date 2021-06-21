package in.siva.vegapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.siva.vegapp.dao.VegRepository;
import in.siva.vegapp.dto.OrderItemDTO;
import in.siva.vegapp.model.OrderItem;
import in.siva.vegapp.model.VegDetail;
import in.siva.vegapp.util.OptionalToObject;

@Service
public class CartService {

	@Autowired
	OrderItemDTO orderItemDTO;
	@Autowired
	VegRepository vegRepo;
	@Autowired
	OptionalToObject toObj;

	/**
	 * This method is used to add selected vegetables to cart
	 * @param vegId
	 * @param quantity
	 * @return
	 */
	public List<OrderItem> addItem(Integer vegId, Integer quantity) {
		Optional<VegDetail> vegDetailOptional = vegRepo.findById(vegId);
		if (vegDetailOptional.isPresent()) {
			List<VegDetail> vegDetails = toObj.toList(vegDetailOptional);
			OrderItem orderItem = orderItemDTO.setOrderItem(vegDetails, quantity);
			orderItemDTO.addVegetable(orderItem);
		}
		return orderItemDTO.getOrderedVegetables();
	}

	/**
	 * This method is used to remove vegetables from cart
	 * @param vegId
	 * @return
	 */
	public List<OrderItem> removeItem(Integer vegId) {
		orderItemDTO.removeVegetable(vegId);
		return orderItemDTO.getOrderedVegetables();
	}
}
