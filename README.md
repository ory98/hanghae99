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
> >   - cd-prod.yml: test코드 검증, build 후 도커 자동화 - main (merge 후 CD 설정)
> >   - cd-staging.yml: test코드 검증, build 후 aws - staging (merge CD 후 설정)
> >   - pr-ci.yml: lint check, test코드 검증, build
> >   - dependency-integration.yml: dependency 라이브러리 다음에 설치할 때, 변경 여부에 따라 갱신/유지 작업 분리 
> > - Setting 설정
> >   - branch
> >     - main: push 방지 및 강제 PR 추가 (merge 전 : pr-ci 작업들이 모두 성공한 후에 merge 가능)
> >     - dev: 
> >     - staging:
> >     - 관리자 권한일 경우 pr 적용 x > cd 과정만 진행
> >   - general
> >     - 원격 branch 자동 삭제 기능 추가
> > 
> > #### Step3. 배포 환경 구축