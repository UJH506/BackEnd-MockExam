package jihye.backend_mock_exam.controller.menu;

import jakarta.validation.Valid;
import jihye.backend_mock_exam.controller.menu.validation.MyQuestionEditValidator;
import jihye.backend_mock_exam.controller.menu.validation.MyQuestionValidator;
import jihye.backend_mock_exam.domain.Questions;
import jihye.backend_mock_exam.domain.exam.Answer;
import jihye.backend_mock_exam.domain.exam.ExamItem;
import jihye.backend_mock_exam.domain.exam.Question;
import jihye.backend_mock_exam.domain.myQuestion.MyQuestion;
import jihye.backend_mock_exam.domain.user.Role;
import jihye.backend_mock_exam.service.Page;
import jihye.backend_mock_exam.service.menu.myQuestions.MyQuestionsService;
import jihye.backend_mock_exam.service.menu.myQuestions.dto.MyQuestionAddDto;
import jihye.backend_mock_exam.service.menu.myQuestions.dto.MyQuestionEditDto;
import jihye.backend_mock_exam.service.menu.myQuestions.dto.MyQuestionSelectDeleteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/my-questions")
@RequiredArgsConstructor
public class MyQuestionsController {

    private final MyQuestionsService myQuestionsService;
    private final MyQuestionValidator myQuestionValidator;
    private final MyQuestionEditValidator myQuestionEditValidator;

    @ModelAttribute("themeColor")
    public String themeColor() {
        return "#A2BFF7";
    }

    @GetMapping
    public String myQuestions() {
        return "redirect:/my-questions/list";
    }

    @GetMapping("/list")
    public String myQuestionsList(@RequestAttribute("user") Role user,
                              @RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "level", required = false, defaultValue = "전체") String level,
                              @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
                              Model model) {

        // 난이도 목록 (문항이 존재하는 것만)
        List<Integer> levels = myQuestionsService.levelListOfMyQuestionWithItem(user.getUserId());

        if (searchKeyword != null) {
            model.addAttribute("searchKeyword", searchKeyword);
        }
        if (level != null) {
            model.addAttribute("paramLevel", level);
        }

        Page<Question> questionList = myQuestionsService.myQuestionsList(user.getUserId(), level, searchKeyword, page);
        model.addAttribute("questionList", questionList);
        model.addAttribute("levels", levels);
        model.addAttribute("user", user);
        model.addAttribute("myQuestionSelectDeleteDto", new MyQuestionSelectDeleteDto());
        return "menu/myQuestions/my-questions-list";
    }

    @PostMapping("/delete")
    public String selectedMyQuestionDelete(@ModelAttribute MyQuestionSelectDeleteDto dto,
                                     @RequestParam(value = "level", required = false) String level,
                                     RedirectAttributes redirectAttributes) {

        log.info("dto={}", dto);
        myQuestionsService.myQuestionSelectDelete(dto);
        if (level != null) { redirectAttributes.addAttribute("level", level); }
        return "redirect:/my-questions/list";
    }

    @GetMapping("/{questionId}")
    public String myQuestionDetail(@PathVariable("questionId") Long questionId, Model model) {
        ExamItem examItem = myQuestionsService.myQuestionDetail(questionId);
        model.addAttribute("examItem", examItem);
        model.addAttribute("myQuestionSelectDeleteDto", new MyQuestionSelectDeleteDto(List.of(questionId)));
        return "menu/myQuestions/my-questions-detail";
    }

    @GetMapping("/{questionId}/edit")
    public String myQuestionEditPage(@RequestAttribute("user") Role user, @PathVariable("questionId") Long questionId,
                                 Model model) {

        List<Integer> levels = myQuestionsService.levelListOfMyQuestion(user.getUserId());

        ExamItem examItem = myQuestionsService.myQuestionDetail(questionId);
        Questions question = examItem.getQuestion();
        List<Answer> answers = examItem.getAnswers();
        MyQuestionEditDto myQuestionEditDto = new MyQuestionEditDto(question.getQuestionId(), question.getLevel(), question.getQuestionText(),
                                                                    examItem.getCorrectAnswer(), answers.get(0), answers.get(1), answers.get(2));

        model.addAttribute("examItem", examItem);
        model.addAttribute("levels", levels);
        model.addAttribute("myQuestionEditDto", myQuestionEditDto);
        return "menu/myQuestions/my-questions-edit";
    }

    @PostMapping("/{questionId}/edit")
    public String myQuestionEdit(@RequestAttribute("user") Role user, @PathVariable Long questionId,
                                 @Valid @ModelAttribute MyQuestionEditDto dto, BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {

        List<Integer> levels = myQuestionsService.levelListOfMyQuestion(user.getUserId());

        myQuestionEditValidator.validate(dto, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            ExamItem examItem = myQuestionsService.myQuestionDetail(questionId);
            model.addAttribute("examItem", examItem);
            model.addAttribute("levels", levels);
            model.addAttribute("myQuestionEditDto", dto);
            return "menu/myQuestions/my-questions-edit";
        }

        myQuestionsService.editMyQuestion(dto);

        redirectAttributes.addAttribute("questionId", questionId);
        return "redirect:/my-questions/{questionId}";
    }

    @GetMapping("/add")
    public String myQuestionAddPage(@RequestAttribute("user") Role user, Model model) {

        List<Integer> levels = myQuestionsService.levelListOfMyQuestion(user.getUserId());

        model.addAttribute("user", user);
        model.addAttribute("levels", levels);
        model.addAttribute("myQuestionAddDto", new MyQuestionAddDto());
        return "menu/myQuestions/my-questions-add";
    }

    @PostMapping("/add")
    public String myQuestionAdd(@RequestAttribute("user") Role user, @Valid @ModelAttribute MyQuestionAddDto dto, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                Model model) {

        List<Integer> levels = myQuestionsService.levelListOfMyQuestion(user.getUserId());

        myQuestionValidator.validate(dto, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            model.addAttribute("levels", levels);
            return "menu/myQuestions/my-questions-add";
        }

        MyQuestion savedQuestion = myQuestionsService.addMyQuestion(dto);
        redirectAttributes.addAttribute("questionId", savedQuestion.getQuestionId());
        return "redirect:/my-questions/{questionId}";
    }

    @GetMapping("/setting")
    public String myQuestionSetting(@RequestAttribute("user") Role user, Model model) {

        List<Integer> levels = myQuestionsService.levelListOfMyQuestion(user.getUserId());
        model.addAttribute("levels", levels);
        return "menu/myQuestions/my-questions-setting";
    }

    @PostMapping("/setting/delete")
    public String myQuestionSettingDelete(@RequestAttribute("user") Role user, @RequestParam("level") Integer level, Model model) {
        myQuestionsService.removeLevel(user.getUserId(), level);

        List<Integer> levels = myQuestionsService.levelListOfMyQuestion(user.getUserId());
        model.addAttribute("levels", levels);
        return "menu/myQuestions/my-questions-setting :: my-question-menu";
    }

    @PostMapping("/setting/add")
    public String myQuestionSettingAdd(@RequestAttribute("user") Role user, Model model) {
        myQuestionsService.addLevel(user.getUserId());

        List<Integer> levels = myQuestionsService.levelListOfMyQuestion(user.getUserId());
        model.addAttribute("levels", levels);
        return "menu/myQuestions/my-questions-setting :: my-question-menu";
    }
}
