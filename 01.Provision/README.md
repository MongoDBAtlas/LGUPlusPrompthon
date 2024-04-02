<img src="https://companieslogo.com/img/orig/MDB_BIG-ad812c6c.png?t=1648915248" width="50%" title="Github_Logo"/> <br>


# MongoDB Atlas Training for LGU Prompthon

### Provision MongoDB

#### Atlas Account
MongoDB Atlas의 무료 계정을 생성 합니다.     
Atlas는 관리형 데이터 베이스로 간편하게 데이터 베이스를 생성 하고 인터넷을 통한 엑세스로 편리하게 사용 할 수 있습니다.   

계정 생성을 위해 Atlas 사이트에 접속 합니다.   

https://www.mongodb.com/ko-kr/cloud/atlas/register
  

#### Database 생성
Atlas에 로그인 후 테스트용 데이터 베이스를 생성 합니다.    
로그인 후 Deployment 메뉴에 Database 를 클릭 합니다. 오른쪽 화면에 생성되어 진 데이터 베이스 정보를 볼 수 있으며 최초에는 데이터 베이스가 없음으로 Create를 클릭 하여 데이터베이스 클러스터를 생성 합니다.    

<img src="/01.Provision/images/images01.png" width="90%" height="90%">     

클러스터 사양을 선택 할 수 있으며 무료로 사용 할 수 있는 Shared를 선택 하고 Cloud Provider로 Azure를 선택 하고 지역은 Seoul을 선택 합니다.

<img src="/01.Provision/images/images20.png" width="90%" height="90%">     

Cluster Tier는 M10을 선택 합니다 (M0는 무료로 사용 가능하지만 기능에 제한이 있습니다. 전체 기능 활용을 위해 M10을 사용 합니다)    

<img src="/01.Provision/images/images21.png" width="90%" height="90%">  

Cluster Name을 입력 하고 Create Cluster를 클릭 하여 데이터 베이스를 생성합니다. (소요시간은 대략 10분이내가 소요 됩니다.)


#### Database Account 생성
Atlas 데이터베이스 클러스터를 접근하기 위한 계정 생성으로 Security 메뉴에 Database Access를 클릭 하여 계정을 생성 할 수 있습니다.    
Hands-on에서는 Id/password를 이용하는 방식의 데이터베이스 계정을 생성 합니다.   

<img src="/01.Provision/images/images08.png" width="90%" height="90%">  

계정은 atlas-account로 하여 생성 합니다. Built-in Role 은 편의상 Read and Write to any database 를 선택합니다.

#### 초기 데이터 로드
생성된 데이터 베이스 클러스터에 초기 샘플 데이터를 적재하여 Hands on을 진행 합니다.   
Database 메뉴를 클릭 하면 생성된 데이터 베이스 클러스터를 볼 수 있습니다. 최초에는 데이터가 없음으로 클러스터 메뉴 버튼을 "..."을 클릭 하면 추가 메뉴 중 Load Sample Dataset 을 선택 합니다.   
생성이 완료된 후 Browse Collections를 클릭하먄 데이터를 볼 수 있습니다.
생성된 데이터 베이스는 sample_airbnb외 8개의 데이터베이스가 생성 되고 최소 1개 이상의 컬렉션(테이블)이 생성되게 됩니다.

<img src="/01.Provision/images/images15.png" width="90%" height="90%"> 


#### 기타 필요한 소프트웨어
Query를 실행하기 위한 쉘(mongosh)을 이용하여 실습을 진행 함으로 이를 다운로드 설치 합니다.   
MongoDB에 접속하고 데이터를 조회 하는 GUI Tool (Compass)를 다운로드 합니다.


Compass :   
https://www.mongodb.com/products/compass

Mongosh :
https://www.mongodb.com/docs/mongodb-shell/install/

