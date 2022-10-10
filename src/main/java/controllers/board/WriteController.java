package controllers.board;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import models.board.BoardDataDto;
import models.member.MemberDto;

@Controller
public class WriteController {
	
	@GetMapping("/board/write/{boardId}")
	public String form(@PathVariable(name="boardId") String boardId, Model model, HttpSession session) {
		BoardDataDto boardDataDto = new BoardDataDto();
		MemberDto member = (MemberDto)session.getAttribute("member");
		if (member != null) {
			boardDataDto.setPoster(member.getMemNm());
		}
		
		String[] addJs = new String[] { "ckeditor/ckeditor", "board/form" };
		String[] addCss = new String[] { "board/style" };
		
		model.addAttribute("boardDataDto", boardDataDto);
		model.addAttribute("addJs", addJs);
		model.addAttribute("addCss", addCss);
		
		return "board/form";
	}
	
	@PostMapping("/board/write")
	public String process(@Valid BoardDataDto boardDataDto, Errors errors) {
		
		if (errors.hasErrors()) {
			return "board/form";
		}
		
		return "redirect:/board/view";
	}
}




