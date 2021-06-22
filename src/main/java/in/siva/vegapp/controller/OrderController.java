package in.siva.vegapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.siva.vegapp.dto.Message;
import in.siva.vegapp.dto.OrderDetailDTO;
import in.siva.vegapp.model.Discount;
import in.siva.vegapp.model.OrderDetail;
import in.siva.vegapp.model.OrderItem;
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
	public List<OrderItem> add(@RequestParam("vegId") Integer vegId, @RequestParam("quantity") Integer quantity) {
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
	public List<OrderItem> get(@RequestParam("vegId") Integer vegId) {
		return cartService.removeItem(vegId);
	}

	/**
	 * This method is used for purchasing vegetables
	 * 
	 * @param orderDetail
	 * @return
	 */
	@PostMapping("purchase")
	public ResponseEntity<Message> purchase(@Valid @RequestBody OrderDetail orderDetail) {
		Message message = new Message();
		try {
			orderService.storeOrder(orderDetail);

			message.setInfoMessage("Order Confirmed");
		} catch (Exception e) {
			message.setErrorMessage("Something went wrong try again");
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	/**
	 * This method is used to get orders of a specific user
	 * 
	 * @param userId
	 * @return
	 */
	@PostMapping("myOrders")
	public List<OrderDetailDTO> getMyOrders(@RequestParam("userId") Integer userId) {
		return orderService.getMyOrders(userId);
	}

	/**
	 * This method is used to get current date orders
	 * 
	 * @return
	 */
	@PostMapping("deliveryOrders")
	public List<OrderDetailDTO> getDeliveryDetails() {
		return orderService.getDeliveryOrders();
	}

	/**
	 * This method is used to get all orders
	 * 
	 * @return
	 */
	@PostMapping("getAll")
	public List<OrderDetailDTO> getAll() {
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
	public ResponseEntity<Message> generateDiscount(@RequestParam("userId") Integer userId,
			@RequestParam("totalBill") Integer totalBill) {
		int discount = discountService.storeDiscount(userId, totalBill);
		Message message = new Message();
		if (discount == 0) {
			message.setErrorMessage("No discount Coupons generated for this order");
		} else {
			message.setInfoMessage("Discount coupon generated for this order of an amount of " + discount);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
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
