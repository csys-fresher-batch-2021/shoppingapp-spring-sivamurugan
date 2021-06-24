package in.siva.vegapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.siva.vegapp.dto.OrderDTO;
import in.siva.vegapp.model.Discount;
import in.siva.vegapp.model.Order;
import in.siva.vegapp.model.OrderedVeg;
import in.siva.vegapp.service.CartService;
import in.siva.vegapp.service.DiscountService;
import in.siva.vegapp.service.OrderService;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private DiscountService discountService;

	/**
	 * This method will send vegetable id and quantity to service layer to add
	 * vegetable to cart and it will return response from service layer
	 * 
	 * @param vegId
	 * @param quantity
	 * @return
	 */
	@PostMapping("cart/add")
	public List<OrderedVeg> add(@RequestParam("vegId") Integer vegId, @RequestParam("quantity") Integer quantity) {
		return cartService.addItem(vegId, quantity);
	}

	/**
	 * This method will send vegetable id to service layer to remove vegetable from
	 * cart and it will return response from service layer
	 * 
	 * @param vegId
	 * @return
	 */
	@PostMapping("cart/remove")
	public List<OrderedVeg> get(@RequestParam("vegId") Integer vegId) {
		return cartService.removeItem(vegId);
	}

	/**
	 * This method is used for purchasing vegetables
	 * 
	 * @param orderDetail
	 * @return
	 */
	@PostMapping("purchase")
	public boolean purchase(@Valid @RequestBody Order orderDetail) {
		orderService.storeOrder(orderDetail);
		return true;
	}

	/**
	 * This method is used to get orders of a specific user
	 * 
	 * @param userId
	 * @return
	 */
	@PostMapping("myOrders")
	public List<OrderDTO> getMyOrders(@RequestParam("userId") Integer userId) {
		return orderService.getMyOrders(userId);
	}

	/**
	 * This method is used to get current date orders
	 * 
	 * @return
	 */
	@PostMapping("deliveryOrders")
	public List<OrderDTO> getDeliveryDetails() {
		return orderService.getDeliveryOrders();
	}

	/**
	 * This method is used to get all orders
	 * 
	 * @return
	 */
	@PostMapping("getAll")
	public List<OrderDTO> getAll() {
		return orderService.getAllOrders();
	}

	/**
	 * This method is used to generate a discount coupon if the bill amount is more
	 * than a specific amount. It will send data to service layer. Depending on
	 * service layer's response corresponding messages will be sent to front end
	 * 
	 * 
	 * @param userId
	 * @param totalBill
	 * @return
	 */
	@PostMapping("discount/generate")
	public int generateDiscount(@RequestParam("userId") Integer userId, @RequestParam("totalBill") Integer totalBill) {
		return discountService.storeDiscount(userId, totalBill);
	}

	/**
	 * This method is used to get available discount coupons of a specific user. It
	 * will not provide used coupons of a user.
	 * 
	 * @param userId
	 * @return
	 */
	@PostMapping("discount/get")
	public List<Discount> getDiscounts(@RequestParam("userId") Integer userId) {
		return discountService.getValidDiscountsOfUser(userId);
	}
}
