package commons.errors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

import commons.rest.JsonMessage;

public class CommonRestException extends CommonException {
	
	private JsonMessage jsonMessage;
	
	public CommonRestException(String message, HttpServletRequest request, HttpStatus status) {
		super(message, request, status);
	}

	public CommonRestException(String message, HttpServletRequest request, int statusCode) {
		super(message, request, statusCode);

	}

	public CommonRestException(String message, HttpServletRequest request) {
		super(message, request);
	}

	public CommonRestException(String message) {
		super(message);

	}

	public CommonRestException(Errors errors, HttpServletRequest request, HttpStatus status) {
		super(errors, request, status);
	}	
	
	public JsonMessage getJsonMessage() {
		jsonMessage = new JsonMessage();
		jsonMessage.setSuccess(false);
		jsonMessage.setStatusCode(getStatusCode());
		jsonMessage.setMessage(getMessage());
		return jsonMessage;
	}
}
