# spring-react-security

본 프로젝트는 Spring Security, React 를 사용하여 기본적인 회원 관리 예제 프로젝트 입니다.<br />
프로젝트 구현시 빠르게 기본기능을 가져다 쓸 수 있습니다.

### Start

설치 및 개발서버 실행

```sh
# 1. 설치
$ npm install

# 2. Java 실행
$ ./gradlew clean bootRun

# 3. 개발서버 실행
$ npm start
```

### Local 개발

- Backend 실행
  ![](images/img_1.png)


- Frontend 실행
  ![](images/img_2.png)


- 통합 실행 (배포 환경)
  ![](images/img_3.png)
  
### API

- `SecurityConfig` 참조
  - 로그인
  - 로그아웃
  

- `Swagger` 참조
  - 회원가입
  - 아이디, 비밀번호 찾기
  - 회원 정보 수정
  - 관리자 모드
  
### Swagger

```shell
http://localhost:28080/swagger-ui/
```

### UPCOMING

아래와 같은 기능에 관해서 개발자들의 기여를 기대한다.

- [x] SMTP (send Email)
- [ ] 휴대폰 인증
- [ ] Oauth2 인증 (Google, Naver 등)
- [ ] HTTPS
- [ ] Redis
- [ ] 모니터링툴
- [ ] Docker
- [ ] 국제화 (영어)
