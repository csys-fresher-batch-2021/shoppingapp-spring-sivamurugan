package in.siva.vegapp.util;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import in.siva.vegapp.model.Vegetable;

@Component
public class OptionalToObject {
	/**
	 * This method is used to convert optional data type to a List of VegDetail
	 * object
	 * 
	 * @param opt
	 * @return
	 */
	public List<Vegetable> toList(Optional<Vegetable> opt) {
		return opt.map(Collections::singletonList).orElseGet(Collections::emptyList);
	}
}
