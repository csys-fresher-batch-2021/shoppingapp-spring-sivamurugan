package in.siva.vegapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.siva.vegapp.dto.Message;
import in.siva.vegapp.exception.InvalidVegException;
import in.siva.vegapp.exception.VegRepeatedException;
import in.siva.vegapp.model.VegDetail;
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
	public ResponseEntity<?> addVeg(@RequestBody VegDetail vegetable) {
		Message message = new Message();
		try {
			vegService.addVegetable(vegetable);
			message.setInfoMessage("Vegetable Added Successfully");
		} catch (InvalidVegException | VegRepeatedException e) {
			e.printStackTrace();
			message.setErrorMessage(e.getMessage());
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
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
	public ResponseEntity<?> removeVeg(@RequestParam String vegName) {
		Message message = new Message();
		if (vegService.removeVegetable(vegName)) {
			message.setInfoMessage("Vegetable Removed Successfully");
		} else {
			message.setErrorMessage("Vegetable not found");
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	/**
	 * This method is used to get all stock vegetables
	 * @return
	 */
	@PostMapping("get")
	public List<VegDetail> getAllStocks(){
		return vegService.getAllStock();
	}
}
