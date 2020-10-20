package funShop.services.mapValidation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class MapValidationErrorService {

	public ResponseEntity<?> MapValidationService(BindingResult result) {
		var errorMsg = "";

		if (result.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();

			for (int i = 0; i < result.getFieldErrors().size(); i++) {
				if (i < result.getFieldErrors().size() - 1) {
					errorMsg += result.getFieldErrors().get(i).getDefaultMessage() + ", ";
				} else {
					errorMsg += result.getFieldErrors().get(i).getDefaultMessage();
				}
			}

			errorMap.put("message", errorMsg);

			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}

		return null;
	}

}
