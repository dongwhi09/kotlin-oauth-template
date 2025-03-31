# Kotlin OAuth2 Template

## 개요
이 프로젝트는 Spring Boot와 Kotlin을 사용한 OAuth2 인증 템플릿입니다.

## 기술 스택
- Kotlin
- Spring Boot 3.2.3
- Spring Security
- Spring Data JPA
- H2 Database

## 지원하는 OAuth2 제공자
- Google
- GitHub

## 시작하기

### 환경 변수 설정
다음 환경 변수들을 설정해야 합니다:
```
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret
GITHUB_CLIENT_ID=your_github_client_id
GITHUB_CLIENT_SECRET=your_github_client_secret
```

### 실행
```bash
./gradlew bootRun
```

## 주요 기능
- OAuth2 소셜 로그인 (Google, GitHub)
- 사용자 정보 저장 및 관리
- H2 인메모리 데이터베이스 사용

## 라이선스
MIT License