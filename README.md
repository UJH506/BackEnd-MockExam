## 백엔드 모의고사
🕹 서비스 체험하기

[▶ 여기를 눌러 시작해보세요 ! ◀](http://13.124.189.46:8080/)
<br /><br />

### 📖 프로젝트 소개
백엔드 기술에 대한 지식을 테스트할 수 있는<br />
온라인 모의고사 플랫폼입니다.
<br /><br />

### ⚙ 개발 환경
- JDK : 17
- Web Server : Apatch Tomcat 10
- Database : MySQL 8
- 프로그래밍 언어 : Java
- 프레임워크 : Spring Boot
- IDE : IntelliJ IDEA 2024.3.1.1
- 버전 관리 : Git
- 배포환경 :
  - 클라우드 서비스 : AWS EC2
  - 운영체제 : Ubuntu 22.04 LTS
  - 백엔드 : Java
  - 데이터베이스 : MySQL
  - 배포 방식 : SSH와 도커 이미지를 사용하여 EC2 인스턴스에 배포
  - URL : http://13.124.189.46:8080/
<br /><br />

### 📌 주요 기능
#### 회원 서비스
- 회원가입 및 로그인
- 비회원으로 시작 (서비스 제한)
- 회원정보 수정
- 비밀번호 찾기 및 재설정
- 회원 탈퇴

#### 문제풀기
- 주제, 난이도, 문항 수 선택
- 조건에 맞는 시험 출제
- 문제와 문제에 속한 보기는 랜덤 순서로 출제
- 답안 선택, 건너뛰기, 제출, 이전/다음 기능
- 시험 체점 및 결과
  - 정답률과 체점 문항 조회
  - 옵션(전체, 정답만, 오답만)별 결과 보기
  - 문항 별 오답노트 저장 및 해제
  - 오답 전체 오답노트에 저장 기능

#### 오답노트
- 주제별 오답노트 조회
- 난이도 및 검색어 필터링
- 문제를 눌러 문제의 보기조회, 정답보기 버튼 눌러 정답 조회
- 오답노트 해제

#### 히스토리
- 히스토리 목록 조회
- 히스토리 상세 조회
- 시험결과와 동일하게 옵션별 결과조회, 오답노트 저장 및 해제, 오답 전체 오답노트 저장 등

#### 메모장
- 메모 작성, 조회, 수정, 삭제
- 목록에서 선택 삭제 및 전체 선택 삭제 등
- 검색 기능

#### 나만의 문제
- 나만의 문제 구성 편집 (난이도 구성 편집)
- 나만의 문제 등록, 조회, 수정, 삭제
- 난이도 및 검색어로 필터링
- 목록에서 선택 삭제 및 전체 선택 삭제 등
<br /><br />
