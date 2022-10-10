package controllers.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import commons.rest.JsonMessage;
import models.file.service.FileDeleteService;

@RestController
public class DeleteController {
	@Autowired
	private FileDeleteService service;
	
	@RequestMapping("/file/delete/{id}")
	public JsonMessage delete(@PathVariable("id") Long id) {
		service.process(id);
		
		return new JsonMessage(200, true, "" + id);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<JsonMessage> deleteErrors(RuntimeException e) {
		JsonMessage jsonMessage = new JsonMessage(200, false, e.getMessage());
		
		return ResponseEntity.status(jsonMessage.getStatusCode()).body(jsonMessage);
	}
}
