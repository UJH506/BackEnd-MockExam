package jihye.backend_mock_exam.repository.user.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignUpDTO {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String accountId;
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @NotBlank(message = "비밀번호 확인란을 입력해주세요.")
    private String passwordCheck;
    @NotBlank(message = "비밀번호 찾기 질문을 선택해주세요.")
    private String findPasswordQuestion;
    @NotEmpty(message = "비밀번호 찾기 답변을 입력해주세요.")
    private String findPasswordAnswer;

    private String gender;
    private Integer birthYear;
}
