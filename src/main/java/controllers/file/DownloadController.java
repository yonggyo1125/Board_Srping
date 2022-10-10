package controllers.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.file.service.FileDownloadService;

@RestController
public class DownloadController {
	
	@Autowired
	private FileDownloadService service;
	
	@RequestMapping("/file/download/{id}")
	public void download(@PathVariable(name="id") Long id) {
		service.download(id);
	}
	
}
