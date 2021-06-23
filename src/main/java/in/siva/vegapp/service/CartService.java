package in.siva.vegapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.siva.vegapp.dao.VegRepository;
import in.siva.vegapp.dto.OrderedVegDTO;
import in.siva.vegapp.model.OrderedVeg;
import in.siva.vegapp.model.Vegetable;
import in.siva.vegapp.util.OptionalToObject;

@Service
public class CartService {

	@Autowired
	OrderedVegDTO orderItemDTO;
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
	public List<OrderedVeg> addItem(Integer vegId, Integer quantity) {
		Optional<Vegetable> vegDetailOptional = vegRepo.findById(vegId);
		if (vegDetailOptional.isPresent()) {
			List<Vegetable> vegDetails = toObj.toList(vegDetailOptional);
			OrderedVeg orderItem = orderItemDTO.setOrderItem(vegDetails, quantity);
			orderItemDTO.addVegetable(orderItem);
		}
		return orderItemDTO.getOrderedVegetables();
	}

	/**
	 * This method is used to remove vegetables from cart
	 * @param vegId
	 * @return
	 */
	public List<OrderedVeg> removeItem(Integer vegId) {
		orderItemDTO.removeVegetable(vegId);
		return orderItemDTO.getOrderedVegetables();
	}
}
