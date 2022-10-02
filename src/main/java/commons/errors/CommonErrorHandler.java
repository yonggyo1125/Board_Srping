package commons.errors;

import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice({"controllers", "controller.member"})
public class CommonErrorHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public String handler(RuntimeException ex, Model model, HttpServletResponse response) {
		
		if (ex instanceof CommonException) {
			Map<String, Object> errorInfo = ((CommonException)ex).gets();
			int statusCode = (Integer)errorInfo.get("statusCode");
			if (statusCode > 0) response.setStatus(statusCode); 
				
			model.addAllAttributes(errorInfo);
		}
		
		model.addAttribute("message", ex.getMessage());
		ex.printStackTrace();
		
		return "common/error";
	}
}
