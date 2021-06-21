package in.siva.vegapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.siva.vegapp.model.OrderItem;
import in.siva.vegapp.service.CartService;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

	@Autowired
	private CartService cartService;

	/**
	 * This method will send vegetable id and quantity to service layer to add
	 * vegetable to cart and it will return response from service layer
	 * 
	 * @param vegId
	 * @param quantity
	 * @return
	 */
	@PostMapping("cart/add")
	public List<OrderItem> add(@RequestParam("vegId") Integer vegId, @RequestParam("quantity") Integer quantity) {
		return cartService.addItem(vegId, quantity);
	}

	/**
	 * This method will send vegetable id to service layer to remove 
	 * vegetable from cart and it will return response from service layer
	 * @param vegId
	 * @return
	 */
	@PostMapping("cart/remove")
	public List<OrderItem> get(@RequestParam("vegId") Integer vegId) {
		return cartService.removeItem(vegId);
	}
}
