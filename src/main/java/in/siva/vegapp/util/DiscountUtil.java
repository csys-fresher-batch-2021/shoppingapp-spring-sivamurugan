package in.siva.vegapp.util;

import org.springframework.stereotype.Component;

@Component
public class DiscountUtil {
	/**
	 * This method is used to generate a random 10 digit coupon code
	 * @return
	 */
	public String generateCoupon() {
		// choose a Character random from this String
		String availableCharacters = "ABCDEFGHIJKLMNPQRSTUVWXYZ" + "123456789";

		StringBuilder sb = new StringBuilder(5);
		for (int i = 0; i < 15; i++) {
			// generate a random number
			double indexDouble = availableCharacters.length() * Math.random();
			int index = (int) Math.round(indexDouble);
			if (index > 0 && index < 33) {
				sb.append(availableCharacters.charAt(index));
			}
		}
		return sb.toString().substring(0, 10);
	}
	
	/**
	 * This method is used to find discount amount for a particular order
	 * @param totalAmount
	 * @return
	 */
	public int findDiscount(int totalAmount) {
		int discount = 0;
		if(totalAmount < 2000 && totalAmount >= 1000) {
			discount = 50;
		}
		else if(totalAmount < 3000 && totalAmount >= 2000) {
			discount = 150;
		}
		else if(totalAmount < 4000 && totalAmount >= 3000) {
			discount = 300;
		}
		else if(totalAmount <5000 && totalAmount >= 4000) {
			discount = 450;
		}
		else if(totalAmount >= 5000) {
			discount = 600;
		}
		return discount;
	}
}
