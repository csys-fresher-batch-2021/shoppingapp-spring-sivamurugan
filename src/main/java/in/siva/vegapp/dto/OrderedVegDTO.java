package in.siva.vegapp.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import in.siva.vegapp.model.OrderedVeg;
import in.siva.vegapp.model.Vegetable;

@Component
public class OrderedVegDTO {
	private List<OrderedVeg> orderedVegetables = new ArrayList<>();

	/**
	 * This method will add details of vegetables from stock to ordered vegetables
	 * @param vegDetails
	 * @param quantity
	 * @return
	 */
	public OrderedVeg setOrderItem(List<Vegetable> vegDetails, int quantity) {
		List<OrderedVeg> orderItems = new ArrayList<>();
		for (Vegetable vegDetail : vegDetails) {
			OrderedVeg orderItem = new OrderedVeg();
			orderItem.setVegId(vegDetail.getId());
			orderItem.setPrice(vegDetail.getPrice());
			orderItem.setQuantity(quantity);
			orderItem.setVegBill(vegDetail.getPrice() * quantity);

			orderItems.add(orderItem);
		}
		return orderItems.get(0);
	}

	/**
	 * This method is used to add cart vegetables 
	 * @param vegetable
	 */
	public void addVegetable(OrderedVeg vegetable) {
		orderedVegetables.add(vegetable);
	}

	/**
	 * This method is used to remove vegetables from cart
	 * @param vegId
	 * @return
	 */
	public boolean removeVegetable(Integer vegId) {
		int index = 0;
		boolean isRemoved = false;
		for (OrderedVeg vegetable : orderedVegetables) {
			if (vegetable.getVegId().equals(vegId)) {
				orderedVegetables.remove(index);
				isRemoved = true;
				break;
			}
			index++;
		}
		return isRemoved;
	}

	/**
	 * This method is used to get all cart vegetables
	 * @return
	 */
	public List<OrderedVeg> getOrderedVegetables() {
		return orderedVegetables;
	}
	
	public List<OrderedVeg> setOrderIdToVeg(Long orderId, List<OrderedVeg> vegetables){
		for (OrderedVeg vegetable : vegetables) {
			vegetable.setOrderId(orderId);
		}
		return vegetables;
	}
	
	/**
	 * This method is used to remove all cart items
	 */
	public void removeAll() {
		orderedVegetables.clear();
	}
}
