package jihye.backend_mock_exam.repository.menu.incorrectNote;

import jihye.backend_mock_exam.domain.exam.Subject;
import jihye.backend_mock_exam.domain.incorrectNote.IncorrectNote;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class IncorrectNoteRepositoryImpl implements IncorrectNoteRepository {

    private final IncorrectNoteMapper incorrectNoteMapper;

    // 주제 목록 조회 (항목이 있는 주제만)
    @Override
    public List<Subject> findAllSubjectsWithItem(Long userId) {
        return incorrectNoteMapper.findAllSubjectsWithItem(userId);
    }

    // 사용자의 오답노트 속 나만의 문제 갯수
    @Override
    public int countOfMyQuestionInIncorrectNote(Long userId) {
        return incorrectNoteMapper.countOfMyQuestionInIncorrectNote(userId);
    }

    // 주제별 난이도 목록 조회 (문항이 존재하는 것만 조회)
    @Override
    public List<Integer> levelListOfSubjectInIncorrectNoteWithItem(Long userId, Long subjectId, boolean isMyQuestion) {
        return incorrectNoteMapper.levelListOfSubjectInIncorrectNoteWithItem(userId, subjectId, isMyQuestion);
    }

    @Override
    // 문항 ID로 오답노트 저장 유무 조회
    public IncorrectNote findIncorrectNoteById(Long userId, Long questionId, boolean isMyQuestion) {
        return incorrectNoteMapper.findIncorrectNoteById(userId, questionId, isMyQuestion);
    };

    // 주제, 난이도, 페이지 선택에 따른 사용자의 오답노트 목록
    @Override
    public List<IncorrectNote> findIncorrectList(Long userId, Long subjectId, int level, String searchKeyword, int offset, int pageSize) {
        return incorrectNoteMapper.findIncorrectList(userId, subjectId, level, searchKeyword, offset, pageSize);
    }

    // 사용자의 오답노트 총 개수
    @Override
    public int findIncorrectTotalCount(Long userId, Long subjectId, int level, String searchKeyword) {
        return incorrectNoteMapper.findIncorrectTotalCount(userId, subjectId, level, searchKeyword);
    }

    // 오답노트에서 문항 삭제
    @Override
    public void deleteQuestionFromIncorrectNote(Long userId, Long questionId, boolean isMyQuestion) {
        incorrectNoteMapper.deleteQuestionFromIncorrectNote(userId, questionId, isMyQuestion);
    }

    // 오답노트에 문항 추가
    @Override
    public void insertQuestionFromIncorrectNote(Long userId, Long questionId, boolean isMyQuestion) {
        incorrectNoteMapper.insertQuestionFromIncorrectNote(userId, questionId, isMyQuestion);
    }
}
