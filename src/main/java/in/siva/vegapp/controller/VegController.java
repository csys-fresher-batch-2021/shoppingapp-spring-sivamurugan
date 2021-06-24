package in.siva.vegapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.siva.vegapp.model.Vegetable;
import in.siva.vegapp.service.VegService;

@RestController
@RequestMapping("api/v1/stock")
public class VegController {

	@Autowired
	VegService vegService;

	/**
	 * This method will pass the vegetable details to service layer. Depending on
	 * the response from service it will send error or info message to front end
	 * 
	 * @param vegetable
	 * @return
	 */
	@PostMapping("save")
	public boolean addVeg(@RequestBody Vegetable vegetable) {
		return vegService.addVegetable(vegetable);

	}

	/**
	 * This method is used to send vegetable name to service to remove the
	 * vegetable. Depending on the response from service layer it will send error or
	 * Info message to front end
	 * 
	 * @param vegName
	 * @return
	 */
	@PostMapping("remove")
	public boolean removeVeg(@RequestParam String vegName) {
		return vegService.removeVegetable(vegName);
	}

	/**
	 * This method is used to get all stock vegetables
	 * 
	 * @return
	 */
	@PostMapping("get")
	public List<Vegetable> getAllStocks() {
		return vegService.getAllStock();
	}
}
