package in.siva.vegapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import in.siva.vegapp.dto.Message;
import in.siva.vegapp.exception.InvalidInputException;
import in.siva.vegapp.exception.InvalidVegException;
import in.siva.vegapp.exception.UserRepeatedException;
import in.siva.vegapp.exception.VegRepeatedException;

@ControllerAdvice
public class ErrorHandler {
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Message> methodArgumentNotValidException(MethodArgumentNotValidException e) {
		BindingResult result = e.getBindingResult();
		List<String> errors = new ArrayList<>();
		Message message = new Message();
		List<FieldError> fieldErrors = result.getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			errors.add(fieldError.getDefaultMessage());
		}
		message.setErrors(errors);
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserRepeatedException.class)
	public ResponseEntity<Message> userRepeatedException(UserRepeatedException e) {
		Message message = new Message();
		message.setErrorMessage(e.getMessage());
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<Message> invalidInputException(InvalidInputException e) {
		Message message = new Message();
		message.setErrorMessage(e.getMessage());
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Message> exception(Exception e) {
		Message message = new Message();
		message.setErrorMessage("Something went wrong try again");
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidVegException.class)
	public ResponseEntity<Message> invalidVegException(InvalidVegException e) {
		Message message = new Message();
		message.setErrorMessage(e.getMessage());
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(VegRepeatedException.class)
	public ResponseEntity<Message> vegRepeatedException(VegRepeatedException e) {
		Message message = new Message();
		message.setErrorMessage(e.getMessage());
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
}
