package in.siva.vegapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.siva.vegapp.dao.VegRepository;
import in.siva.vegapp.model.VegDetail;

@Component
public class VegValidator {
	@Autowired
	UtilValidator util;
	@Autowired
	VegRepository vegRepo;

	/**
	 * This method is used to validate the vegetable details were valid or not
	 * 
	 * @param vegetable
	 * @return
	 */
	public boolean isVegValid(VegDetail vegetable) {
		boolean valid = false;
		if (util.isStringValid(vegetable.getName()) && util.isNumberValid(vegetable.getPrice())
				&& util.isNumberValid(vegetable.getQuantity())) {
			valid = true;
		}
		return valid;
	}

	/**
	 * This method is used to validate whether the entered vegetable is already
	 * present in DB or not
	 * 
	 * @param vegName
	 * @return
	 */
	public boolean isVegNotRepeated(String vegName) {
		boolean isNotRepeated = false;
		Integer id = vegRepo.findIdByName(vegName);
		if (id == null) {
			isNotRepeated = true;
		}
		return isNotRepeated;
	}
}
