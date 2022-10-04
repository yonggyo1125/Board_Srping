package controllers.file;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/file/upload")
public class UploadController {
	
	@GetMapping
	public String form(Model model) {
		model.addAttribute("addJs", new String[] {"fileUpload"});
		model.addAttribute("addCss", new String[] {"popup"});
		return "file/upload";
	}

	@PostMapping
	@ResponseBody
	public String process() {
		
		return null;
	}
}
