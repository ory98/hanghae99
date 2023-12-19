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
> >   - cd-prod.yml: test코드 검증, build 후 aws 배포- main (merge 후 CD 설정)
> >   - cd-staging.yml: test코드 검증, build 후 aws 배포 - staging (merge CD 후 설정)
> >   - pr-ci.yml: lint check, test코드 검증, build
 
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
> > - AWS ECR, ECS 작업
> >   - IAM 생성
> >   - AWS ECR 레포지토리 생성
> >   - AWS ECS CLUSTER 구성 및 SERVICE 생성 - prod, staging (참고자료 - https://velog.io/@linho1150/ECS-ECR-Github-Actions를-사용하여-Nest.js-CICD하기#github-actions-설정)
> >     - git dev에서 작업 요망 > aws 서버 실행됨
> >   - AWS 로드밸랜서 생성 예정

> ### 2주차
> > #### Step1
> > - 시나리오 선정: e-커머스 서비스 
> >   - 잔액 충전 / 조회 API
> >   - 상품 조회 API
> >   - 주문 / 결제 조회 API
> >   - 상위 상품 조회 API
> >   
> >
> > - API 명세  
> >   - https://www.notion.so/API-7449b32639d44cf7a091f3dfbf305719?pvs=4
> >   - 보완 예정
> > 
> > 
> > - API 기능 구현 진행중 
> > 
> ### 3주차
> > #### Step2
> > - 동시성 문제 등을 검출할 수 있는 테스트 코드 작성
> >   - 테스트 케이스 작성중
> >   - Api 구현중