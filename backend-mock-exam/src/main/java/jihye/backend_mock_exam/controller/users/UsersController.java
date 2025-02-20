package jihye.backend_mock_exam.controller.users;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jihye.backend_mock_exam.domain.user.Guest;
import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.service.users.UsersService;
import jihye.backend_mock_exam.service.users.dto.DeleteAccountDto;
import jihye.backend_mock_exam.service.users.dto.EditAccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @ModelAttribute("themeColor")
    public String themeColor() {
        return "#71B961";
    }

    @ModelAttribute("user")
    public User loginUser(@PathVariable("userId") long userId) {
        return usersService.userInfo(userId).orElseThrow();
    }

    @GetMapping("/edit/{userId}")
    public String editAccountPage(@PathVariable("userId") long userId, Model model) {
        User user = loginUser(userId);

        EditAccountDto dto = new EditAccountDto(user.getUserId(), user.getAccountId(), user.getNickname(), user.getGender(), user.getBirthYear());

        model.addAttribute("editAccountDto", dto);
        return "users/edit-account";
    }

    @PostMapping("/edit/{userId}")
    public String editAccount(@PathVariable("userId") long userId,
                              @Valid @ModelAttribute EditAccountDto dto, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "users/edit-account";
        }

        boolean result = usersService.editAccount(userId, dto);

        if (!result) {
            bindingResult.rejectValue("password", "unmatched.user.password");
            return "users/edit-account";
        }

        redirectAttributes.addAttribute("userId", userId);
        redirectAttributes.addFlashAttribute("status", "edit-success");

        return "redirect:/users/edit/{userId}";
    }

    @GetMapping("/delete/{userId}")
    public String deleteAccountPage(@PathVariable("userId") long userId, Model model) {
        DeleteAccountDto dto = new DeleteAccountDto();
        dto.setUserId(userId);

        model.addAttribute("deleteAccountDto", dto);
        return "users/delete-account";
    }

    @PostMapping("/delete/{userId}")
    public String deleteAccount(@Valid @ModelAttribute DeleteAccountDto dto, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "users/delete-account";
        }

        boolean result = usersService.deleteAccount(dto.getUserId(), dto, request);

        if (!result) {
            bindingResult.rejectValue("password", "unmatched.user.password");
            return "users/delete-account";
        }

        return "redirect:/auth/sign-out";
    }

}
