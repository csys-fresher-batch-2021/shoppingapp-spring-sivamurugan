package in.siva.vegapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.siva.vegapp.dao.VegRepository;
import in.siva.vegapp.exception.InvalidVegException;
import in.siva.vegapp.exception.VegRepeatedException;
import in.siva.vegapp.model.OrderedVeg;
import in.siva.vegapp.model.Vegetable;
import in.siva.vegapp.validator.VegValidator;

@Service
public class VegService {

	@Autowired
	VegRepository vegRepo;
	@Autowired
	VegValidator vegValidator;

	/**
	 * This method is used to add vegetable in DB. If entered details were valid and
	 * not already added then vegetable details will be added to DB. Else it will
	 * throw exception
	 * 
	 * @param vegetable
	 */
	public void addVegetable(Vegetable vegetable) {
		if (vegValidator.isVegValid(vegetable)) {
			if (vegValidator.isVegNotRepeated(vegetable.getName())) {
				vegRepo.save(vegetable);
			} else {
				throw new VegRepeatedException("Vegetable already exists");
			}
		} else {
			throw new InvalidVegException("Invalid vegetable details");
		}
	}

	/**
	 * This method is used to remove vegetable from the DB. It will remove the
	 * vegetable details if given vegetable name is already present in DB.
	 * 
	 * @param vegName
	 * @return
	 */
	public boolean removeVegetable(String vegName) {
		boolean isRemoved = false;
		Integer id = vegRepo.deleteByName(vegName);
		if (id != null) {
			isRemoved = true;
		}
		return isRemoved;
	}
	
	/**
	 * This method is used to get all stock vegetables 
	 */
	public List<Vegetable> getAllStock() {
		return (List<Vegetable>)vegRepo.findAll();
	}
	
	public void updateStock(List<OrderedVeg> orderedVegetables) {
		for (OrderedVeg orderItem : orderedVegetables) {
			vegRepo.updateStockById(orderItem.getQuantity(), orderItem.getVegId());
		}
	}
}
