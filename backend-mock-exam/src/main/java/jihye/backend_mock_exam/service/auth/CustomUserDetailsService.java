package jihye.backend_mock_exam.service.auth;

import jihye.backend_mock_exam.domain.user.User;
import jihye.backend_mock_exam.repository.auth.AuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
//@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthRepository authRepository;

    public CustomUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {

        User findByIdUser = authRepository.findByAccountId(accountId);
        log.info("loadUserByUsername.findByIdUser={}", findByIdUser);

        if (findByIdUser == null) {
            throw new UsernameNotFoundException(accountId + "은(는) 없는 아이디 입니다.");
        }

        return new User(
                findByIdUser.getUserId(),
                findByIdUser.getAccountId(),
                findByIdUser.getNickname(),
                findByIdUser.getHashedPassword(),
                findByIdUser.getRoles()
        );
    }

}
