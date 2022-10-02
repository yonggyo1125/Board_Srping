package commons.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import commons.rest.JsonMessage;

@RestControllerAdvice("controllers.rest")
public class CommonRestErrorHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<JsonMessage> handler(RuntimeException ex) {
		JsonMessage jsonMessage = null;
		if (ex instanceof CommonRestException) {
			jsonMessage = ((CommonRestException)ex).getJsonMessage();
		} else {
			jsonMessage = new JsonMessage(400, false, ex.getMessage());
		}
		
		return ResponseEntity.status(jsonMessage.getStatusCode()).body(jsonMessage);
	}
}
