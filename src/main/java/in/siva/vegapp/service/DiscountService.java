package in.siva.vegapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.siva.vegapp.dao.DiscountRepository;
import in.siva.vegapp.dto.DiscountDTO;
import in.siva.vegapp.model.Discount;
import in.siva.vegapp.util.DiscountUtil;

@Service
public class DiscountService {
	@Autowired
	DiscountUtil discountUtil;
	@Autowired
	DiscountDTO discountDTO;
	@Autowired
	DiscountRepository discountRepo;

	/**
	 * This method will generate a discount coupon for the order, if bill amount is
	 * more than 1000Rs. Then this discount details will be stored in DB. After
	 * storing it in DB discount amount will be returned
	 * 
	 * @param userId
	 * @param totalBill
	 * @return
	 */
	public int storeDiscount(Integer userId, Integer totalBill) {
		String coupon = discountUtil.generateCoupon();
		int discount = discountUtil.findDiscount(totalBill);
		Discount discountDetails = discountDTO.setDetails(coupon, discount, userId);
		discountRepo.save(discountDetails);
		return discount;
	}

	/**
	 * This method is used to get available discount coupons of a user. It will not
	 * return used discount coupons
	 * 
	 * @param userId
	 * @return
	 */
	public List<Discount> getValidDiscountsOfUser(Integer userId) {
		return discountRepo.findAllByUserId(userId);
	}
}
