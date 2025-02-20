package jihye.backend_mock_exam.service.menu.myQuestions.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jihye.backend_mock_exam.domain.exam.Answer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MyQuestionAddDto {
    
    @NotNull
    private Long userId;
    @NotNull(message = "난이도를 선택해주세요")
    private Integer level;
    @NotBlank(message = "문제의 질문을 입력해주세요")
    private String questionText;
    @NotNull
    private Answer correctAnswer;
    @NotNull
    private Answer wrongAnswer1;
    @NotNull
    private Answer wrongAnswer2;
    @NotNull
    private Answer wrongAnswer3;
//    @Valid
//    private List<@NotBlank String> wrongAnswers = new ArrayList<>();
}
