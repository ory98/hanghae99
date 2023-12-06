# 항해99 프로젝트 
 
> ### 1주차
> > #### Step1. 웹서버 생성
> > - build.gradle
> >   - dependencies spring actuator 추가 (endPoint: /actuator/health)
> >   - resources - application
> >     - 서버 Phase 별 yml파일 분리 (dev/staging/prod)
> >   
> > #### Step2. 빌드 환경 구축 (Github)
> > - Dockerfile 생성
> > - Actions workflows 생성
> >   - cd-prod.yml: Push, jdk 실행, 도커 자동화, 작업 순서 추가
> >   - cd-staging.yml: Push, jdk 실행, 도커 자동화, 작업 순서 추가
> >   - pr-ci.yml: lint check, test코드 검증
> >   - dependency-integration.yml: dependency 라이브러리 다음에 설치할 때, 변경 여부에 따라 갱신/유지 작업 분리 
> > - Setting 설정
> >   - branch
> >     - main: push 방지 및 강제 PR 추가 (pr-ci_checkstyle, test)
> >     - dev: 
> >     - staging:
> >   - general
> >     - 원격 branch 자동 삭제 기능 추가
> > 
> > #### Step3. 배포 환경 구축