package controllers.file;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import commons.rest.JsonMessage;
import models.file.FileInfoDto;
import models.file.service.FileUploadService;

@Controller
@RequestMapping("/file/upload")
public class UploadController {
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@GetMapping
	public String form(@RequestParam(name="gid", required=false) String gid, 
									@RequestParam(name="imageOnly", required=false) boolean imageOnly, Model model) {
		
		model.addAttribute("addJs", new String[] {"fileUpload"});
		model.addAttribute("gid", gid);
		model.addAttribute("imageOnly", imageOnly);
		
		return "file/upload";
	}

	@PostMapping
	@ResponseBody
	public List<FileInfoDto> process(@RequestParam(name="gid", required=false) String gid,  // 그룹 ID
										@RequestParam(name="imageOnly", required=false) boolean imageOnly, // 이미지만 업로드 통제 
										@RequestParam(name="file", required=false) MultipartFile[] files) {
		

		List<FileInfoDto> data = fileUploadService.upload(files, gid, imageOnly);

		return data;
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public ResponseEntity<JsonMessage> fileUploadError(RuntimeException ex) {
		JsonMessage jsonMessage = new JsonMessage(200, false, ex.getMessage());
		
		return ResponseEntity.status(jsonMessage.getStatusCode()).body(jsonMessage);
	}
}
