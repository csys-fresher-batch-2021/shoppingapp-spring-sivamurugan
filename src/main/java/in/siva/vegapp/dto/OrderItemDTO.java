package in.siva.vegapp.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import in.siva.vegapp.model.OrderItem;
import in.siva.vegapp.model.VegDetail;

@Component
public class OrderItemDTO {
	private List<OrderItem> orderedVegetables = new ArrayList<>();

	/**
	 * This method will add details of vegetables from stock to ordered vegetables
	 * @param vegDetails
	 * @param quantity
	 * @return
	 */
	public OrderItem setOrderItem(List<VegDetail> vegDetails, int quantity) {
		List<OrderItem> orderItems = new ArrayList<>();
		for (VegDetail vegDetail : vegDetails) {
			OrderItem orderItem = new OrderItem();
			orderItem.setVegId(vegDetail.getId());
			orderItem.setPrice(vegDetail.getPrice());
			orderItem.setQuantity(quantity);
			orderItem.setVegBill(vegDetail.getPrice() * quantity);

			// Default
			orderItem.setActive(true); // false - when user removed it
			orderItems.add(orderItem);
		}
		return orderItems.get(0);
	}

	/**
	 * This method is used to add cart vegetables 
	 * @param vegetable
	 */
	public void addVegetable(OrderItem vegetable) {
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
		for (OrderItem vegetable : orderedVegetables) {
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
	public List<OrderItem> getOrderedVegetables() {
		return orderedVegetables;
	}
}
