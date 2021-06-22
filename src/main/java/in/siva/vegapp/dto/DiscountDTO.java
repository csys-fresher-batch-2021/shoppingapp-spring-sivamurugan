package in.siva.vegapp.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import in.siva.vegapp.model.Discount;

@Component
public class DiscountDTO {
	/**
	 * This method is used to set discount details in a Discount object
	 * @param coupon
	 * @param discount
	 * @param userId
	 * @return
	 */
	public Discount setDetails(String coupon, Integer discount, Integer userId) {
		Discount discountDetail = new Discount();
		discountDetail.setAmount(discount);
		discountDetail.setCoupon(coupon);
		discountDetail.setExpiryDate(LocalDate.now().plusMonths(2));
		discountDetail.setStatus("AVAILABLE");
		discountDetail.setUserId(userId);
		return discountDetail;
	}
}
