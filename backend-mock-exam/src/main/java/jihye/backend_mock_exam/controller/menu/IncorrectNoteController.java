package jihye.backend_mock_exam.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/incorrect-note")
public class IncorrectNoteController {

    @GetMapping
    public String incorrectNoteHome() {
        return "redirect:/incorrect-note/subject";
    }

    @GetMapping("/subject")
    public String subject() {
        return "menu/incorrectNote/incorrectNote-subject";
    }

    @GetMapping("/list")
    public String level() {
        return "menu/incorrectNote/incorrectNote-list";
    }

}
