## 네이버지도 API를 이용한 오프라인 결제 정보 시각화

### 사전 준비사항
#### 핵데이 시작시 각자의 구성에 대한 설명 및 논의를 진행할 예정 (논의 후 합의된 방안으로 핵데이 기간 동안 코드 통폐합 진행)
* Spring Boot를 이용한 서버 구축
* DB는 개인 자유
* 화면 구성은 최소한으로 구성
* 샘플 데이터 제공 (https://github.com/NAVER-CAMPUS-HACKDAY/offline_payment_visualization/issues/1)

### 서버 정보
* 공인 IP : 106.10.56.145
* ssh 접속 포트 : 2019
* 접속 명령어 : ssh -l root -p 2019 106.10.56.145
* 점속 비밀번호 : 별도 전달

### 개발 요구사항 (필수)
* Java, HTML, Javascript, DB 개발 능력 필요
* 네이버지도 API 명세 참조
* 데이터를 DB에 적재하는 인터페이스 개발  결제 데이터를 활용해 최소 일별 통계와 지도를 엮어낸 웹서비스 개발

### 개발 요구사항 (선택)
* 화면 구성에 사용할 마크업 템플릿 조사 및 활용 ( ex. 부트스트랩 )
* 지도 API 에서 제공되는 것 외에 데이터 시각화 관련 라이브러리 조사 및 활용 일별 통계 외에 유의미한 데이터 도출 및 시각화

### Role
* Full-stack Developer

### Technology 
* Git
* HTML
* Freemarker
* JSP
* MySQL
* MariaDB
* MongoDB
* Spring
* Linux

### 개발언어
* Java
* Javascript
* SQL

### 기타사항
* DB는 명시된 것 외에 것을 사용해도 무방
  - RDBMS, NoSQL 중 자유선택 
* 뷰템플릿 엔진은 명시된 것 외에 것을 사용해도 무방
* 마크업은 템플릿을 사용하지 않고 직접 코딩해도 무방
* 결제 정보데이터는 일별데이터를 파일로 전달

