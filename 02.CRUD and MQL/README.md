<img src="https://companieslogo.com/img/orig/MDB_BIG-ad812c6c.png?t=1648915248" width="50%" title="Github_Logo"/> <br>


# MongoDB Atlas Training for LGU Prompthon

### [&rarr; CRUD with Nodejs](#CRUD)

### [&rarr; CRUD with Mongosh](#MONGOSH)

### [&rarr; CRUD with Spring Framework Project](#Project)

### [&rarr; CRUD with Java](#Java)

### [&rarr; Compass 를 이용한 데이터 확인](#Compass)

### [&rarr; 추가 Query](#option)

### [&rarr; Aggregation](#Aggregation)

### [&rarr; Lookup 을 이용한 조인](#Lookup)

### [&rarr; 추가 Aggregation](#option)

<br>



### CRUD

Nodejs로 Atlas 에 접속 하고 MongoDB Query 를 이용하여 데이터를 생성, 조회, 삭제를 테스트 합니다. <br>
코드는 application 폴더에 있으며 실행을 위해서는 NodeJS를 설치하고 테스트를 위해 관련 패키지를 설치 하여 줍니다.<br><br>
***(아래 명령어는 반드시 application 폴더에서 실행하셔야 합니다)***
````
% npm install

added 196 packages, and audited 197 packages in 2s

14 packages are looking for funding
  run `npm fund` for details

found 0 vulnerabilities
````
node_modules 폴더가 생성되어 관련된 라이브러리가 설치 됩니다.


#### Connection Test

MongoDB Atlas 와 연결을 위한 테스트 입니다.
MongoDB atlas Console에 접근 주소를 얻어야 합니다. 
접속 주소를 얻기 위해 Console에 로그인합니다. 
데이터베이스 클러스터의 Connect 버튼을 클릭 합니다.

<img src="/02.CRUD and MQL/images/image01.png" width="90%" height="90%">     


접근방법을 선택 하여 주는 단계에서 Connect your application를 선택 하면 접근 주소를 얻을 수 있습니다.   

<img src="/02.CRUD and MQL/images/image08.png" width="60%" height="60%">   

Driver는 Node.js를 선택 하고 버젼은 4.1 or later를 선택 하여 주면 연결을 위한 Connection String이 생성 됩니다.    

<img src="/02.CRUD and MQL/images/image09.png" width="70%" height="70%">     


connect.js 에 const uri을 수정 하여 줍니다. 생성한 Database Account 와 비밀 번호를 수정하여 줍니다. 만약 비밀번호에 특수문자가 포함되어있는 경우 ('@','#', '!' 등) HTML URI encoding이 필요합니다.  
(https://www.w3schools.com/tags/ref_urlencode.ASP)

````
const uri =mongodb+srv://atlas-account:<password>@cluster0.****.mongodb.net/myFirstDatabase?retryWrites=true&w=majority
````
연결 테스트를 위해 다음을 실행 합니다.

````
% node connect.js 
Connected successfully to server
````

#### Insert Test

MongoDB Atlas 와 연결하여 데이터를 생성 합니다.
insertOne.js 에 const uri을 수정 하여 줍니다.

````
const uri =mongodb+srv://atlas-account:<password>@cluster0.****.mongodb.net/myFirstDatabase?retryWrites=true&w=majority
````
입력할 데이터를 수정 하여 줍니다. 

````
      const newUser = <<query>>;  // query를 수정

      const newUser = {
        ssn:"123-456-0001", 
        email:"user@email.com", 
        name:"Gildong Hong", 
        DateOfBirth: "1st Jan.", 
        Hobbies:["Martial arts"],
        Addresses:[{"Address Name":"Work","Street":"431, Teheran-ro GangNam-gu ","City":"Seoul", "Zip":"06159"}], 
        Phones:[{"type":"mobile","number":"010-5555-1234"}]
      };
````

입력 테스트를 위해 다음을 실행 합니다.

````
% node insertOne.js 
A document was inserted with the _id: 63bba1f8e554c42df82f974e
````
Atlas Console 에서 데이터 생성 여부를 확인 합니다.


#### find Test

MongoDB Atlas 와 연결하여 데이터를 조회 합니다.
findeOne.js 에 const uri을 수정 하여 줍니다.

````
const uri =mongodb+srv://atlas-account:<password>@cluster0.****.mongodb.net/myFirstDatabase?retryWrites=true&w=majority
````
입력할 데이터를 수정 하여 줍니다.
조회할 데이터의 ssn을 확인 합니다.  

`````
const query = {ssn:"123-456-0001"};
`````

데이터를 조회 합니다
````
% node findOne.js
Find One Record: 63bba1f8e554c42df82f974e
Find One Record by SSN: 63bba1f8e554c42df82f974e
````

#### Update Test

MongoDB Atlas 와 연결하여 데이터를 업데이트 합니다.
updateOne.js 에 const uri을 수정 하여 줍니다.


````
const uri =mongodb+srv://atlas-account:<password>@cluster0.****.mongodb.net/myFirstDatabase?retryWrites=true&w=majority
````
수정할 데이터를 ssn을 입력 하여 줍니다.
수정 대상 데이터의 ssn 및 수정할 데이터 항목을 확인 수정 하여 줍니다.
`````
      const query = {"ssn":"123-456-0001"};
      const updatedata ={$set:{email:"gildong@email.com"}};
`````

데이터를 수정 합니다
````
% node updateOne.js
1 document(s) matched the filter, updated 0 document(s)
````

#### Update Hobbies Test


MongoDB Atlas 와 연결하여 데이터를 업데이트 합니다.
updateHobbies.js 에 const uri을 수정 하여 줍니다.

````
const uri =mongodb+srv://atlas-account:<password>@cluster0.****.mongodb.net/myFirstDatabase?retryWrites=true&w=majority
````
수정할 데이터를 ssn을 입력 하여 줍니다.
수정 대상 데이터의 ssn 및 Hobby 항목을 추가 하여 줍니다. (취미로 Reading 추가 하기)
`````
      const query = {"ssn":"123-456-0001"};
      const updatedata ={$push:{Hobbies:"Reading"}};     
`````

데이터를 수정 합니다
````
node updateHobbies.js 
1 document(s) matched the filter, updated 1 document(s)
````
Atlas Data Console에서 데이터가 수정 된 것을 확인 합니다.


#### Remove Test


MongoDB Atlas 와 연결하여 데이터를 삭제 합니다.
removeUser.js 에 const uri을 수정 하여 줍니다.

````
const uri =mongodb+srv://atlas-account:<password>@cluster0.****.mongodb.net/myFirstDatabase?retryWrites=true&w=majority
````
삭제할 데이터를 수정 하여 줍니다.
삭제할 데이터의 ssn 및 입력 하여줍니다.
`````
const qeury = {"ssn":"123-456-0001"};
`````

데이터를 삭제 합니다
````
% node removeUser.js 
1 document(s) removed
````


### MONGOSH

Mongosh로 Atlas 에 접속 하고 MongoDB Query 를 이용하여 데이터를 생성, 조회, 삭제를 테스트 합니다. NodeJS에 익숙하지 않은 경우 이를 이용하여 테스트 합니다.


#### Connection

MongoDB Atlas 와 Mongosh을 이용하여 연결 합니다.    
MongoDB atlas Mongosh 접근 주소를 얻어야 합니다. 
접속 주소를 얻기 위해 Console에 로그인합니다. 
데이터베이스 클러스터의 Connect 버튼을 클릭 합니다.

<img src="/02.CRUD and MQL/images/image01.png" width="90%" height="90%">     


접근방법을 선택 하여 주는 단계에서 Shell을 선택 하면 접근 주소를 얻을 수 있습니다.   

<img src="/02.CRUD and MQL/images/image20.png" width="60%" height="60%">   

Mongosh이 설치 되어 있음으로 I have the MongoDB Shell installed를 선택하고 계정 접근은 암호로 접근할 것임으로 Password를 선택하면 접근 할 수 있는 주소를 얻을 수 있습니다.    

<img src="/02.CRUD and MQL/images/image21.png" width="70%" height="70%">     


Terminal을 열고 해당 주소를 이용하여 mongosh를 실행 하여 줍니다. (접근하기 위한 Account로 입력 하여 줍니다.)

````
 % mongosh "mongodb+srv://cluster0.5qjlg.mongodb.net/myFirstDatabase" --apiVersion 1 --username admin    
Enter password: **********
Current Mongosh Log ID:	64454459813babb209a83f4c
Connecting to:		mongodb+srv://cluster0.5qjlg.mongodb.net/myFirstDatabase
Using MongoDB:		6.0.5 (API Version 1)
Using Mongosh:		1.0.5

For mongosh info see: https://docs.mongodb.com/mongodb-shell/

Atlas atlas-t0pzlo-shard-0 [primary] myFirstDatabase> 
````

#### Insert Test

Mongosh을 이용하여 Atlas와 연결하여 데이터를 생성 합니다.

먼저 데이터베이스를 선택하여야 합니다.
````
Atlas atlas-gamf6g-shard-0 [primary] MMT> use handson
switched to db handson
Atlas atlas-gamf6g-shard-0 [primary] handson>
````

다음 데이터 베이스 명령으로 데이터를 생성 합니다.

````
db.user.insert(
  {
        ssn:"123-456-0001", 
        email:"user@email.com", 
        name:"Gildong Hong", 
        DateOfBirth: "1st Jan.", 
        Hobbies:["Martial arts"],
        Addresses:[{"Address Name":"Work","Street":"431, Teheran-ro GangNam-gu ","City":"Seoul", "Zip":"06159"}], 
        Phones:[{"type":"mobile","number":"010-5555-1234"}]
  }
);

{
  acknowledged: true,
  insertedIds: { '0': ObjectId("653662c14771f95974766b8d") }
}
````
Atlas Console 에서 데이터 생성 여부를 확인 합니다.


#### find Test

Mongosh을 이용하여 Atlas와 연결하여 데이터를 조회 합니다.

먼저 데이터베이스를 선택하여야 합니다. (이미 해당 데이터베이스를 사용 하고 있으면 생략 합니다)
````
Atlas atlas-gamf6g-shard-0 [primary] MMT> use handson
switched to db handson
Atlas atlas-gamf6g-shard-0 [primary] handson>
````

데이터를 조회 합니다
````
db.user.find({ssn:"123-456-0001"});

[
  {
    _id: ObjectId("64454591813babb209a83f4d"),
    ssn: '123-456-0001',
    email: 'user@email.com',
    name: 'Gildong Hong',
    DateOfBirth: '1st Jan.',
    Hobbies: [ 'Martial arts' ],
    Addresses: [
      {
        'Address Name': 'Work',
        Street: '431, Teheran-ro GangNam-gu ',
        City: 'Seoul',
        Zip: '06159'
      }
    ],
    Phones: [ { type: 'mobile', number: '010-5555-1234' } ]
  }
]
````

#### Update Test

Mongosh을 이용하여 Atlas와 연결하여 데이터를 업데이트 합니다.

먼저 데이터베이스를 선택하여야 합니다. (이미 해당 데이터베이스를 사용 하고 있으면 생략 합니다)
````
Atlas atlas-gamf6g-shard-0 [primary] MMT> use handson
switched to db handson
Atlas atlas-gamf6g-shard-0 [primary] handson>
````

수정할 데이터를 ssn을 입력 하여 줍니다.
수정 대상 데이터의 ssn 및 수정할 데이터 항목을 확인 수정 하여 줍니다.
`````
db.user.updateOne(
  {"ssn":"123-456-0001"},
   [
      { $set: { email: "gildong@email.com" } }
   ]
);

{
  acknowledged: true,
  insertedId: null,
  matchedCount: 1,
  modifiedCount: 1,
  upsertedCount: 0
}      
`````

데이터를 수정 결과를 확인 합니다. (이메일 주소가 수정 된 것을 확인 합니다)
````
db.user.find({"ssn":"123-456-0001"});

[
  {
    _id: ObjectId("64454591813babb209a83f4d"),
    ssn: '123-456-0001',
    email: 'gildong@email.com',
    name: 'Gildong Hong',
    DateOfBirth: '1st Jan.',
    Hobbies: [ 'Martial arts' ],
    Addresses: [
      {
        'Address Name': 'Work',
        Street: '431, Teheran-ro GangNam-gu ',
        City: 'Seoul',
        Zip: '06159'
      }
    ],
    Phones: [ { type: 'mobile', number: '010-5555-1234' } ]
  }
]
````

#### Update Hobbies Test

Mongosh을 이용하여 Atlas와 연결하여 데이터를 업데이트 (Hobbies를 추가)합니다.

먼저 데이터베이스를 선택하여야 합니다. (이미 해당 데이터베이스를 사용 하고 있으면 생략 합니다)
````
Atlas atlas-gamf6g-shard-0 [primary] MMT> use handson
switched to db handson
Atlas atlas-gamf6g-shard-0 [primary] handson>
````

수정할 데이터를 ssn을 입력 하여 줍니다.
수정 대상 데이터의 ssn 및 Hobby 항목을 추가 하여 줍니다. (취미로 Reading 추가 하기)
`````
db.user.updateOne(
  {"ssn":"123-456-0001"},
  { $push: { Hobbies:"Reading" } }
);

{
  acknowledged: true,
  insertedId: null,
  matchedCount: 1,
  modifiedCount: 1,
  upsertedCount: 0
}
`````

데이터를 수정 결과를 확인 합니다. (Hobby에 Reading이 추가되어 있음)
````
db.user.find({"ssn":"123-456-0001"});

[
  {
    _id: ObjectId("64454591813babb209a83f4d"),
    ssn: '123-456-0001',
    email: 'gildong@email.com',
    name: 'Gildong Hong',
    DateOfBirth: '1st Jan.',
    Hobbies: [ 'Martial arts', 'Reading' ],
    Addresses: [
      {
        'Address Name': 'Work',
        Street: '431, Teheran-ro GangNam-gu ',
        City: 'Seoul',
        Zip: '06159'
      }
    ],
    Phones: [ { type: 'mobile', number: '010-5555-1234' } ]
  }
]

````

#### Remove Test

Mongosh을 이용하여 Atlas와 연결하여 데이터를 삭제 합니다.

먼저 데이터베이스를 선택하여야 합니다. (이미 해당 데이터베이스를 사용 하고 있으면 생략 합니다)
````
Atlas atlas-gamf6g-shard-0 [primary] MMT> use handson
switched to db handson
Atlas atlas-gamf6g-shard-0 [primary] handson>
````

삭제할 데이터를 수정 하여 줍니다.
삭제할 데이터의 ssn 및 입력 하여줍니다.
`````
db.user.deleteOne({ssn:"123-456-0001"});

{ acknowledged: true, deletedCount: 1 }

`````

데이터를 확인 합니다.
````
db.user.findOne({ssn:"123-456-0001"});
null
````


### Project
일반 Java Project로 생성하거나 Springframework 프로젝트를 사용 할 수 있습니다.       
(Spring Framework를 이용하는 경우 Spring Data MongoDB를 선택 할 수 있습니다.)

<img src="/02.CRUD and MQL/images/image92.png" width="90%" height="90%">   

SpringBoot에서 MongoDB를 이용한 개발 방법은 4 가지로 구분이 가능 합니다.

MongoDB Driver Only : Spring Framework 와 Spring Data를 이용하지 않는 개발 방법   
Spring Framework & MongoDB : Spring Framework를 이용하지만 Spring Data를 이용하지 않는 방법   
Spring Framework & Mongo Template : Spring Framework 와 Template를 이용한 Spring Data 활용    
Spring Framework & Spring Data : Spring Framework 와 Spring Data를 이용한 방법 

#### Driver

MongoDB Driver만을 사용하여 개발이 단순한 장점이 있으나 Spring Framewok가 제공 되지 않아 해당 API를 사용하지 못하는 단점이 있습니다.    
Dependency를 POM에 추가 하여 줍니다.  

```` pom.xml
		<dependency>
      <groupId>org.mongodb</groupId>
      <artifactId>mongodb-driver-sync</artifactId>
    </dependency>
````

MongoClient를 생성 하여 Connection 한 후 Database, Collection을 선택 한 후 쿼리를 진행 합니다.

[SpringFramework MongoDB Driver][0]

#### MongoTemplates

Spring Framework이 제공하는 Mongo Templates를 이용하여 개발 하게 됩니다.   
Repository가 제공하는 기본 CRUD등을 사용하지 않기 때문에 일이 구현해야 합니다. 

Dependency를 추가 하여 줍니다.

```` pom.xml		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-mongodb</artifactId>
		</dependency>
````

또한 application.properties 에 Atlas 접근 정보를 추가 하여야 합니다.


```` application.properties
spring.data.mongodb.uri=mongodb+srv://<<username>>:<<password>>@<<atlas address>>.mongodb.net
spring.data.mongodb.database=<<database>>
````

데이터는 model을 생성하고 MongoTemplate 객체를 이용하여 CRUD를 처리합니다. 

````

@Autowired
	private MongoTemplate mongoTemplate;

  ...

  User user = new User();
	user.setAge(50);
	user.setEmail("gildong.hong@email.com");
	user.setName("Gildong Hong");
			
	mongoTemplate.insert(user);

````

Update 처리
````

  @Autowired
	private MongoTemplate mongoTemplate;

  ...

  Query query = new Query();
  query.addCriteria(Criteria.where("ssn").is("123-456-7890"));
  query.addCriteria(Criteria.where("addresses.type").is("home"));
  
  Update update = new Update();
  update.set("addresses.$.postcode", "06230");
  
  UpdateResult rs = mongoTemplate.updateFirst(query, update, User.class);
  
  System.out.println("Update Result : "+ rs);

````

[SpringFramework MongoTemplate][1]

_class 를 제거 하는 방법

Application.properties를 사용하여 연결 되는 부분을 중간에 재정의 하는 것으로 다음과 클래스를 추가 하여 주고 생성된 mongoTemplate를 사용 하면 됩니다.   
converter.setTypeMapper로 DefaultMongoTypeMapper(null)를 넣어주면 됩니다.    


````
package com.mongodb.spring.template.ocnfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {

	@Value("${spring.data.mongodb.uri}")
    private String mongodb_uri;

    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate()  {
        MongoClient mongoClient = MongoClients.create(mongodb_uri);
        MongoDatabaseFactory factory = new SimpleMongoClientDatabaseFactory(mongoClient, "handson");
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(factory), new MongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return new MongoTemplate(factory, converter);
    }

}

````

#### Repository

Spring Framework이 제공하는 Respository를 사용 하는 것으로 기본 CRUD외에 필요한 메서드를 repository interface에 작성하고 implementation 에 구현해 주어야 한다.   

[SpringFramework JPA][2]


_class 를 제거 하는 방법    
아래와 같이 클래스를 추가 하여 주면 됩니다.    

````
package com.mongodb.spring.data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class MongoConfig {
    @Autowired
    private MongoDatabaseFactory mongoFactory;

    @Autowired
    private MongoMappingContext mongoMappingContext;

    @Bean
    public MappingMongoConverter mappingMongoConverter() {

        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, 
mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return converter;
    }
}

````


### Java

Github에서 소스를 다운 로드 한 후 02.Provision and CRUD 하위에 AtlasSpringDriver, AtlasSpringJPA, AtlasSpringMongoTemplate폴더로 이동 하여 줍니다. (Java  애플리케이션)
Maven을 이용한 프로젝트로 필요한 패키지는 pom.xml에 등록 되어 있습니다.

실행을 위해 Connection 관련 정보를 입력 하여야 합니다.  
AtlasSpringDriverApplication 클래스에 connectionString을 사용하고 있는 Atlas의 접속 주소로 변경 하여 줍니다.  

````
private String connectionString = "mongodb+srv://<<user>>:<<password>>@<<Atlas>>.mongodb.net";
````

#### Insert Test

MongoDB Atlas 와 연결하여 데이터를 생성 합니다.

실행을 위해 AtlasSpringDriverApplication 클래스에 ApplicationRunner 메서드에 실행할 메서드(insertNewUser())를 지정 하여 합니다.

실제 실행될 함수 확인을 위해 insertNewUser 함수로 이동 합니다.    

```` insertNewUser()
      List<Document> _addresses = new ArrayList<Document> ();
			_addresses.add(new Document("type", "office").append("address","서울시 강남구 삼성동").append("detail", "코엑스 6"));
			_addresses.add(new Document("type", "home").append("address","서울시 강남구 역삼동").append("detail", "역삼 한국 아파트 101동 101호"));
			
			List<String> _hobbies = new ArrayList<String> ();
			_hobbies.add("Martial arts");
			
			Document _user = new Document();
			_user.append("ssn", "123-456-7890")
			.append("name", "Gildong Hong")
			.append("email", "gildong@email.com")
			.append("DateOfBirth", "25th Dec")
			.append("Hobbies", _hobbies)
			.append("Addresses", _addresses);

````

AtlasSpringDriverApplication을 실행 하여 줍니다.  

<img src="/02.Provision and CRUD/images/image51.png" width="90%" height="90%">   

Query로 데이터를 확인 합니다.

````
Atlas handson> db.Userss.find()
[
  {
    _id: ObjectId("64bbe8460f740053372d75e3"),
    ssn: '123-456-7890',
    name: 'Gildong Hong',
    email: 'gildong@email.com',
    DateOfBirth: '25th Dec',
    Hobbies: [ 'Martial arts' ],
    Addresses: [
      { type: 'office', address: '서울시 강남구 삼성동', detail: '코엑스 6' },
      {
        type: 'home',
        address: '서울시 강남구 역삼동',
        detail: '역삼 한국 아파트 101동 101호'
      }
    ]
  }
]
````

#### find Test

MongoDB Atlas 와 연결하여 데이터를 조회 합니다.

실행을 위해 AtlasSpringDriverApplication 클래스에 ApplicationRunner 메서드에 실행할 메서드(findUsers())를 지정 하여 합니다.

````
return args -> {
			//insertNewUser();
			findUsers("123-456-7890");
			//updateUserHobby("123-456-7890","Reading");
			//updateUserAddresses("123-456-7890","home", "06230");
			//deleteUser("123-456-7890");
			
		};
````


실제 실행될 함수 확인을 위해 findUsers 함수로 이동 합니다.   

````
Bson filter = eq("ssn", ssn);
			
List<Document> iterDoc = getMongoDatabase().getCollection("Users").find(filter).projection(fields(excludeId(),include("ssn","name","email"))).into(new ArrayList<Document>());
````

AtlasSpringDriverApplication을 실행 하여 줍니다.  

````
 :: Spring Boot ::                (v3.1.2)

2023-07-22T23:35:20.637+09:00  INFO 14366 --- [           main] c.m.spring.AtlasSpringDriverApplication  : Starting AtlasSpringDriverApplication using Java 17.0.4.1 with PID 14366 (/Users/kyudong.kim/works/spring/AtlasSpringDriver/target/classes started by kyudong.kim in /Users/kyudong.kim/works/spring/AtlasSpringDriver)
...
AtlasSpringDriverApplication  : Started AtlasSpringDriverApplication in 1.113 seconds (process running for 1.594)
...
Success Find Document : {"ssn": "123-456-7890", "name": "Gildong Hong", "email": "gildong@email.com"}

````

#### Update Hobbies Test

MongoDB Atlas 와 연결하여 데이터를 조회 합니다.

실행을 위해 AtlasSpringDriverApplication 클래스에 ApplicationRunner 메서드에 실행할 메서드(updateUserHobby())를 지정 하여 합니다.

Hobbies 필드에 Reading 을 추가 하여 줍니다.   

````
return args -> {
			//insertNewUser();
			//findUsers("123-456-7890");
			updateUserHobby("123-456-7890","Reading");
			//updateUserAddresses("123-456-7890","home", "06230");
			//deleteUser("123-456-7890");
			
		};
````

실제 실행될 함수 확인을 위해 updateUserHobby 함수로 이동 합니다.   

````
Document query = new Document().append("ssn",  ssn);
			
Bson updates = Updates.combine(
              Updates.push("Hobbies", hobby));
      
MongoCollection<Document> _collection = getMongoDatabase().getCollection("Users");
UpdateResult result = _collection.updateOne(query, updates);

````


AtlasSpringDriverApplication을 실행 하여 줍니다.  

````
 :: Spring Boot ::                (v3.1.2)

2023-07-22T23:40:47.408+09:00  INFO 14772 --- [           main] c.m.spring.AtlasSpringDriverApplication  : Starting AtlasSpringDriverApplication using Java 17.0.4.1 with PID 14772 (/Users/kyudong.kim/works/spring/AtlasSpringDriver/target/classes started by kyudong.kim in /Users/kyudong.kim/works/spring/AtlasSpringDriver)
...
2023-07-22T23:40:48.150+09:00  INFO 14772 --- [gex.mongodb.net] org.mongodb.driver.cluster               : SRV resolution completed with hosts: [ac-f8e5gwu-lb.9g4ngex.mongodb.net:27017]
Update Result : AcknowledgedUpdateResult{matchedCount=1, modifiedCount=1, upsertedId=null}

````

Query로 데이터를 확인 합니다.

````
Atlas handson> db.Userss.find()
[
  {
    _id: ObjectId("64bbe8460f740053372d75e3"),
    ssn: '123-456-7890',
    name: 'Gildong Hong',
    email: 'gildong@email.com',
    DateOfBirth: '25th Dec',
    Hobbies: [ 'Martial arts', 'Reading' ],
    Addresses: [
      { type: 'office', address: '서울시 강남구 삼성동', detail: '코엑스 6' },
      {
        type: 'home',
        address: '서울시 강남구 역삼동',
        detail: '역삼 한국 아파트 101동 101호'
      }
    ]
  }
]
````

#### Update Address Test


MongoDB Atlas 와 연결하여 데이터를 조회 합니다.

실행을 위해 AtlasSpringDriverApplication 클래스에 ApplicationRunner 메서드에 실행할 메서드(updateUserAddresses())를 지정 하여 합니다.

Addresses 내용 중 type이 home인 것의 우편번호를 06230으로 수정 합니다.  

````
return args -> {
			//insertNewUser();
			//findUsers("123-456-7890");
			//updateUserHobby("123-456-7890","Reading");
			updateUserAddresses("123-456-7890","home", "06230");
			//deleteUser("123-456-7890");
			
		};
````

실제 실행될 함수 확인을 위해 updateUserAddresses 함수로 이동 합니다.   

````
Document query = new Document().append("ssn",  ssn).append("Addresses.type",  type);
			
Bson updates = Updates.combine(
              Updates.set("Addresses.$.Zip", zipcode)  );
      
MongoCollection<Document> _collection = getMongoDatabase().getCollection("Users");
UpdateResult result = _collection.updateOne(query, updates);

````


AtlasSpringDriverApplication을 실행 하여 줍니다.  

````
 :: Spring Boot ::                (v3.1.2)
...
Update Result : AcknowledgedUpdateResult{matchedCount=1, modifiedCount=1, upsertedId=null}

````

Query로 데이터를 확인 합니다.

````
Atlas handson> db.Userss.find()
[
  {
    _id: ObjectId("64bbe8460f740053372d75e3"),
    ssn: '123-456-7890',
    name: 'Gildong Hong',
    email: 'gildong@email.com',
    DateOfBirth: '25th Dec',
    Hobbies: [ 'Martial arts', 'Reading' ],
    Addresses: [
      { type: 'office', address: '서울시 강남구 삼성동', detail: '코엑스 6' },
      {
        type: 'home',
        address: '서울시 강남구 역삼동',
        detail: '역삼 한국 아파트 101동 101호',
        Zip: '06230'
      }
    ]
  }
]
````

#### Remove Test

MongoDB Atlas 와 연결하여 데이터를 삭제 합니다.

실행을 위해 AtlasSpringDriverApplication 클래스에 ApplicationRunner 메서드에 실행할 메서드(deleteUser())를 지정 하여 합니다.


````
return args -> {
			//insertNewUser();
			//findUsers("123-456-7890");
			//updateUserHobby("123-456-7890","Reading");
			//updateUserAddresses("123-456-7890","home", "06230");
			deleteUser("123-456-7890");
			
		};
````

실제 실행될 함수 확인을 위해 deleteUser 함수로 이동 합니다.   

````
Bson filter = eq("ssn", ssn);
DeleteResult rs =  getMongoDatabase().getCollection("Users").deleteOne(filter);

````


AtlasSpringDriverApplication을 실행 하여 줍니다.  

````
  :: Spring Boot ::                (v3.1.2)
...
Delete Result : AcknowledgedDeleteResult{deletedCount=1}
````

Query로 데이터를 확인 합니다.

````
Atlas handson> db.Userss.find()


````


### Compass

MongoDB Cluster에 접속하여 저장된 데이터 등을 볼 수 있는 개발자용 GUI툴입니다. 이를 이용하여 데이터를 조회 하고 변경 하여 줍니다. 다음 링크에서 다운로드가 가능 합니다.    
Compass :   
https://www.mongodb.com/products/compass

테스트를 위해 다음 방법으로 데이터를 생성 하여 줍니다.
````
% node insertMany.js 
A document was inserted with the _id: 63e32381541c67cc69d78977
A document was inserted with the _id: 63e32381541c67cc69d78978
A document was inserted with the _id: 63e32381541c67cc69d78979
A document was inserted with the _id: 63e32381541c67cc69d7897a
...
````

데이터가 100건이 생성이 되게 됩니다.


#### Connection
MongoDB atlas Console에 접근 주소를 얻어야 합니다. 
접속 주소를 얻기 위해 Console에 로그인합니다.    
데이터베이스 클러스터의 Connect 버튼을 클릭 합니다.

<img src="/02.CRUD and MQL/images/image01.png" width="90%" height="90%">     

접근방법을 선택 하여 주는 단계에서 Connect using MongoDB Compass를 선택 하면 접근 주소를 얻을 수 있습니다.    

<img src="/02.CRUD and MQL/images/image02.png" width="60%" height="60%">     

Connection String을 복사하여 줍니다. 이후 Compass를 실행 하여 줍니다.     
<img src="/02.CRUD and MQL/images/image03.png" width="70%" height="70%">     



복사한 Connection String을 입력하여 줍니다.   

<img src="/02.CRUD and MQL/images/image04.png" width="90%" height="90%">     


#### 데이터 조회
데이터베이스에서 생성한 handson 탭을 클릭 하면 컬렉션 리스트를 볼 수 있습니다. 생성한 user컬렉션을 선택 합니다.    

<img src="/02.CRUD and MQL/images/image05.png" width="90%" height="90%">     

데이터 검색을 위해서 Filter 부분에 검색 조건을 입력 하여 줍니다.
ssn 이 123-456-0001 인 데이터를 찾기 위해 다음과 같이 입력 하여 줍니다.

````
{ssn: "123-456-0001"}
````

<img src="/02.CRUD and MQL/images/image06.png" width="90%" height="90%">     

나이(age)가 10 이상 40이하인 사람을 찾기를 합니다. 조건은 age >= 10 이고 age <=40으로 합니다.

````
{age: {$gte: 10, $lte: 40}}
````

<img src="/02.CRUD and MQL/images/image07.png" width="90%" height="90%">     



### option
생성된 데이터 베이스중 Movie 관련 데이터 컬렉션 (sample_mflix.movies)에서 다음 내용을 Query 합니다.

- 1987 년에 나온 데이터 조회 (Where year = 1987)

- 장르가 Comedy 에 속하는 영화 검색

- 장르가 Comedy 하나 만 있는 데이터 검색

- 장르가 Comedy 혹은 Drama 인 데이터 검색

- imdb 의 평가 점수가 8.0 이상이고 등급이 PG 인 영화 검색

- metacritic의 평점이 존재 하는 영화 검색

- Dr. Strangelove 로 시작하는 영화 검색

해당 쿼리는 다음과 같습니다.
- 1987 년에 나온 데이터 조회 (Where year = 1987)
````
db.movies.find({year:1987})
````
<img src="/02.CRUD and MQL/images/image11.png" width="90%" height="90%">     

- 장르가 Comedy 에 속하는 영화 검색
````
db.movies.find({genres: "Comedy"})

````
<img src="/02.CRUD and MQL/images/image12.png" width="90%" height="90%">     

- 장르가 Comedy 하나 만 있는 데이터 검색
````
db.movies.find({genres:["Comedy"]})

````
<img src="/02.CRUD and MQL/images/image13.png" width="90%" height="90%">     

- 장르가 Comedy 혹은 Drama 인 데이터 검색
````
db.movies.find({genres:{$in:["Comedy", "Drama"]}})

````
<img src="/02.CRUD and MQL/images/image14.png" width="90%" height="90%">     

- imdb 의 평가 점수가 8.0 이상이고 등급이 PG 인 영화 검색
````
db.movies.find({"imdb.rating" : {$gt: 8.0}, rated:"PG"})

````
<img src="/02.CRUD and MQL/images/image15.png" width="90%" height="90%">     

- metacritic의 평점이 존재 하는 영화 검색
````
db.movies.find({metacritic: {$exists: true}})

````
<img src="/02.CRUD and MQL/images/image16.png" width="90%" height="90%">     

- Dr. Strangelove 로 시작하는 영화 검색
````
db.movies.find({title: {$regex: '^Dr. Strangelove'}})

````
<img src="/02.CRUD and MQL/images/image17.png" width="90%" height="90%">     

### Aggregation

Movies 컬렉션에서 장르가 "Comedy" 인 영화 중 포함된 모든 국가를 기준으로 그룹하여 국가별 포함 개수를 "CountriesInComedy" 컬렉션에 데이터를 생성하여 줍니다.  

Aggregation 이 제공하는 Stage 중, Match 를 이용하여 장르가 Comedy인 것을 찾을 수 있으며, 배열로 되어 있는 항목을 개별로 전환은 unwind 를 이용합니다. 국가별로 그룹을 만들기 위해서는 group Stage를 활용 하며 결과 데이터를 컬렉션에 넣기 위해서는 out을 이용 합니다.   

matach
Find와 유사한 형태로 사용 합니다.

````
{$match: 
  {
    genres: 'Comedy',
  }
}
````

unwind
배열을 항목을 지정하면 이를 개별 문서로 전환 하여 줍니다.  

````
{$unwind: 
  {
    path: '$countries',
  }
}
````

group
지정된 필드를 기준으로 그룹하여 줍니다. SQL의 Group by 와 유사 합니다. 그룹에 따른 계산은 그룹별 카운트 한 횟수로 합니다. 

````
{$group: 
  {
    _id: '$countries',
    count: {
      $sum: 1,
    }
  }
}
````

out
입력된 커서를 지정된 컬렉션으로 생성 하여 줍니다.

````
{
    $out: 'countriesByComedy',
}
````

Compass 의 Aggregation에서 Stage를 생성 하여 줍니다.   

match stage 생성 하기   

<img src="/02.CRUD and MQL/images/image25.png" width="90%" height="90%">     

unwind stage 생성 하기    

<img src="/02.CRUD and MQL/images/image26.png" width="90%" height="90%">    

group stage 생성 하기    

<img src="/02.CRUD and MQL/images/image27.png" width="90%" height="90%">     

out stage 생성 하기    

<img src="/02.CRUD and MQL/images/image28.png" width="90%" height="90%">     

생성된 컬렉션을 확인 합니다. out은 컬렉션을 생성하고 데이터를 생성 하여 줌으로 다시 aggregation을 실행 하기 위해서는 생성된 컬렉션을 삭제하고 실행 해줍니다. (실행 후 작성한 aggregation을 저장하여 줍니다.)

<img src="/02.CRUD and MQL/images/image29.png" width="100%" height="100%">     


#### Aggregation Node JS 실행 하기

작성한 Aggregation 코드를 Nodejs에서 실행 하도록 개발 합니다. 
개발용 코드는 자동으로 생성 하여 줌으로 이를 이용 하도록 합니다. Compass에서 개발한 aggregation코드를 오픈하여 줍니다.  
메뉴중 "EXPORT TO LANGUAGE"를 클릭 합니다.

<img src="/02.CRUD and MQL/images/image30.png" width="90%" height="90%">     

개발 언어를 Node를 선택 하여 주고 코드를 복사하여 줍니다.   

<img src="/02.CRUD and MQL/images/image31.png" width="80%" height="80%">     

application 의 aggregation.js 에 복사한 내용을 붙여 주기 합니다.
컬렉션을 만들지 않고 화면을 출력 하기 위해 out stage 는 생략 하고 작성 합니다.

복사한 내용을 pipeline 으로 작성 합니다.

````
        const pipeline = [
            {
              '$match': {
                'genres': 'Comedy'
              }
            }, {
              '$unwind': {
                'path': '$countries'
              }
            }, {
              '$group': {
                '_id': '$countries', 
                'count': {
                  '$sum': 1
                }
              }
            }, {
              '$sort': {
                'count': -1
              }
            }
          ];

````
````
kyle@M-FC637HK1H7 application % node aggregation.js
{ _id: 'USA', count: 3843 }
{ _id: 'France', count: 793 }
{ _id: 'UK', count: 696 }
{ _id: 'Italy', count: 477 }
{ _id: 'Germany', count: 442 }
{ _id: 'Canada', count: 348 }
{ _id: 'India', count: 199 }
{ _id: 'Spain', count: 197 }
{ _id: 'Japan', count: 171 }
{ _id: 'Australia', count: 148 }
{ _id: 'Hong Kong', count: 117 }
{ _id: 'Belgium', count: 112 }
{ _id: 'Finland', count: 104 }
{ _id: 'Sweden', count: 94 }
{ _id: 'Denmark', count: 84 }
{ _id: 'Netherlands', count: 76 }
{ _id: 'Ireland', count: 73 }
{ _id: 'Russia', count: 66 }
{ _id: 'Mexico', count: 62 }
{ _id: 'Norway', count: 56 }
{ _id: 'Argentina', count: 54 }
{ _id: 'China', count: 50 }
{ _id: 'Switzerland', count: 49 }
{ _id: 'South Korea', count: 49 }
{ _id: 'Brazil', count: 48 }
{ _id: 'West Germany', count: 47 }
{ _id: 'Poland', count: 47 }
{ _id: 'Czech Republic', count: 41 }
{ _id: 'Soviet Union', count: 39 }
{ _id: 'Austria', count: 33 }
{ _id: 'Hungary', count: 31 }
{ _id: 'New Zealand', count: 29 }
{ _id: 'Taiwan', count: 28 }
{ _id: 'Israel', count: 26 }
{ _id: 'Romania', count: 25 }
{ _id: 'Greece', count: 24 }
{ _id: 'Turkey', count: 23 }
{ _id: 'Czechoslovakia', count: 21 }
{ _id: 'Luxembourg', count: 20 }
{ _id: 'Thailand', count: 20 }
{ _id: 'Iceland', count: 19 }
{ _id: 'Portugal', count: 14 }
{ _id: 'Iran', count: 13 }
{ _id: 'South Africa', count: 12 }
{ _id: 'Philippines', count: 10 }
{ _id: 'Croatia', count: 10 }
{ _id: 'Serbia', count: 10 }
{ _id: 'Latvia', count: 8 }
{ _id: 'Estonia', count: 8 }
{ _id: 'Federal Republic of Yugoslavia', count: 8 }
{ _id: 'Yugoslavia', count: 8 }
{ _id: 'Cuba', count: 7 }
{ _id: 'Chile', count: 7 }
{ _id: 'Singapore', count: 6 }
{ _id: 'United Arab Emirates', count: 6 }
{ _id: 'Slovakia', count: 6 }
{ _id: 'Ukraine', count: 6 }
{ _id: 'Uruguay', count: 6 }
{ _id: 'Slovenia', count: 6 }
{ _id: 'Lebanon', count: 5 }
{ _id: 'Serbia and Montenegro', count: 5 }
{ _id: 'Bulgaria', count: 4 }
{ _id: 'Jordan', count: 4 }
{ _id: 'Malaysia', count: 4 }
{ _id: 'Egypt', count: 4 }
{ _id: 'Republic of Macedonia', count: 4 }
{ _id: 'Senegal', count: 3 }
{ _id: 'Puerto Rico', count: 3 }
{ _id: 'Tunisia', count: 3 }
{ _id: 'Georgia', count: 3 }
{ _id: 'Colombia', count: 2 }
{ _id: 'Lithuania', count: 2 }
{ _id: 'Botswana', count: 2 }
{ _id: 'Uzbekistan', count: 2 }
{ _id: 'Indonesia', count: 2 }
{ _id: 'Bosnia and Herzegovina', count: 2 }
{ _id: 'Armenia', count: 2 }
{ _id: 'Iraq', count: 1 }
{ _id: 'Angola', count: 1 }
{ _id: 'Kyrgyzstan', count: 1 }
{ _id: 'Saudi Arabia', count: 1 }
{ _id: 'Nigeria', count: 1 }
{ _id: 'Panama', count: 1 }
{ _id: 'Tajikistan', count: 1 }
{ _id: 'Zaire', count: 1 }
{ _id: 'Liechtenstein', count: 1 }
{ _id: 'Bhutan', count: 1 }
{ _id: 'Papua New Guinea', count: 1 }
{ _id: 'Qatar', count: 1 }
{ _id: 'Algeria', count: 1 }
{ _id: "Cète d'Ivoire", count: 1 }
{ _id: 'Faroe Islands', count: 1 }
{ _id: 'Malta', count: 1 }
{ _id: 'Cameroon', count: 1 }
{ _id: 'Peru', count: 1 }
{ _id: 'Kazakhstan', count: 1 }
{ _id: 'Rwanda', count: 1 }
{ _id: 'East Germany', count: 1 }
{ _id: 'Albania', count: 1 }
{ _id: 'Monaco', count: 1 }
{ _id: 'Greenland', count: 1 }
{ _id: 'Montenegro', count: 1 }
{ _id: 'Bolivia', count: 1 }
{ _id: 'North Korea', count: 1 }
{ _id: 'Palestine', count: 1 }

````

### Lookup

sample_mflix.comments 와 sample_mflix.users 를 결합하여 데이터를 조회 합니다.    
users의 데이터 중 이름이 "Mercedes Tyler"인 사람을 찾아 그가 게시한 Comments 를 찾습니다.   

해당 데이터를 검색 하면 다음과 같습니다.    
users    
````
{
  "_id": {
    "$oid": "59b99dedcfa9a34dcd78862d"
  },
  "name": "Mercedes Tyler",
  "email": "mercedes_tyler@fakegmail.com",
  "password": "$2b$12$ONDwIwR9NKF1Tp5GjGI12e8OFMxPELoFrk4x4Q3riJGWY6jl/UZAa"
}
````

comments 의 경우 다음과 같습니다.
````
[{
  "_id": {
    "$oid": "5a9427648b0beebeb69579e7"
  },
  "name": "Mercedes Tyler",
  "email": "mercedes_tyler@fakegmail.com",
  "movie_id": {
    "$oid": "573a1390f29313caabcd4323"
  },
  "text": "Eius veritatis vero facilis quaerat fuga temporibus. Praesentium expedita sequi repellat id. Corporis minima enim ex. Provident fugit nisi dignissimos nulla nam ipsum aliquam.",
  "date": {
    "$date": {
      "$numberLong": "1029646567000"
    }
  }
},
...
]
````

Lookup으로 조인을 하여 데이터를 볼 때는 전체 데이터를 조인 하는 것 보다 Match를 이용하여 Join 할 범위를 좁힌 후에 하는 것이 필요 합니다.   

Aggregation을 작성하기 위해 Compass에서 sample_mflix.users를 선택 합니다.  
Aggregation 탭에서 먼저 match 스테이지를 작성 합니다.

Match
````
{$match:
  {
    name:"Mercedes Tyler"
  }
}
````

Lookup 스테이지를 추가하여 줍니다.    
Lookup
````
{$lookup:
  {
    from: "comments",
    localField: "name",
    foreignField: "name",
    as: "Comments"
  }
}
````

<img src="/02.CRUD and MQL/images/image32.png" width="80%" height="80%">     

결과로 다음과 같이 Comments를 포함한 결과가 보여 집니다.


````
db.users.aggregate(
[
  {
    $match: {
      name: "Mercedes Tyler",
    },
  },
  {
    $lookup: {
      from: "comments",
      localField: "name",
      foreignField: "name",
      as: "Comments",
    },
  },
]);


{
  _id: ObjectId("59b99dedcfa9a34dcd78862d"),
  name: 'Mercedes Tyler',
  email: 'mercedes_tyler@fakegmail.com',
  password: '$2b$12$ONDwIwR9NKF1Tp5GjGI12e8OFMxPELoFrk4x4Q3riJGWY6jl/UZAa',
  Comments: [
    {
      _id: ObjectId("5a9427648b0beebeb69579e7"),
      name: 'Mercedes Tyler',
      email: 'mercedes_tyler@fakegmail.com',
      movie_id: ObjectId("573a1390f29313caabcd4323"),
      text: 'Eius veritatis vero facilis quaerat fuga temporibus. Praesentium expedita sequi repellat id. Corporis minima enim ex. Provident fugit nisi dignissimos nulla nam ipsum aliquam.',
      date: 2002-08-18T04:56:07.000Z
    },
    {
      _id: ObjectId("5a9427648b0beebeb6958131"),
      name: 'Mercedes Tyler',
      email: 'mercedes_tyler@fakegmail.com',
      movie_id: ObjectId("573a1392f29313caabcdb8ac"),
      text: 'Dolores nulla laborum doloribus tempore harum officiis. Rerum blanditiis aperiam nemo dignissimos a magni natus. Tenetur suscipit cumque sint dignissimos. Accusantium eveniet consequuntur officia ea.',
      date: 2007-09-21T08:52:00.000Z
    },
    {
      _id: ObjectId("5a9427648b0beebeb69582cb"),
      name: 'Mercedes Tyler',
      email: 'mercedes_tyler@fakegmail.com',
      movie_id: ObjectId("573a1393f29313caabcdbe7c"),
      text: 'Voluptatem ad enim corrupti esse consectetur. Explicabo voluptates quo aperiam deleniti reiciendis. Temporibus aliquid delectus recusandae commodi.',
      date: 2008-05-17T22:55:39.000Z
    },
    {
      _id: ObjectId("5a9427648b0beebeb69582cc"),
      name: 'Mercedes Tyler',
      email: 'mercedes_tyler@fakegmail.com',
      movie_id: ObjectId("573a1393f29313caabcdbe7c"),
      text: 'Fuga nihil dolor veniam repudiandae. Rem debitis ex porro dolorem maxime laborum. Esse molestias accusamus provident unde. Sint cupiditate cumque corporis nulla explicabo fuga.',
      date: 2011-03-01T12:06:42.000Z
    },
    {
      _id: ObjectId("5a9427648b0beebeb69588e6"),
      name: 'Mercedes Tyler',
      email: 'mercedes_tyler@fakegmail.com',
      movie_id: ObjectId("573a1393f29313caabcde00c"),
      text: 'Et quas doloribus ipsum sapiente amet enim optio. Magni odio pariatur quos. Voluptatum error ipsum nemo similique error vel.',
      date: 1971-05-13T02:38:19.000Z
    },
    {
      _id: ObjectId("5a9427648b0beebeb69589a1"),
      name: 'Mercedes Tyler',
      email: 'mercedes_tyler@fakegmail.com',
      movie_id: ObjectId("573a1393f29313caabcde4a8"),
      text: 'Ipsam quos magnam ipsum odio aspernatur voluptas nihil nesciunt. Deserunt magni corporis aperiam. Delectus blanditiis eius molestiae modi velit illo veritatis.',
      date: 2015-12-10T21:26:15.000Z
    },
    {
      _id: ObjectId("5a9427648b0beebeb6958aeb"),
      name: 'Mercedes Tyler',
      email: 'mercedes_tyler@fakegmail.com',
      movie_id: ObjectId("573a1394f29313caabcde63e"),
      text: 'Magnam repudiandae ipsam perspiciatis. Tenetur commodi tenetur dolorem tempora. Quas a quos laboriosam.',
      date: 2007-09-19T02:17:40.000Z
    },
    ...
  ]
}
````



### option
#### Aggregation Group 
다음과 같은 과일 판매 데이터가 있을 때 일자별로 판매된 과일과 총 판매 금액을 계산 합니다.

````
db.sales.insertMany([
{ "_id" : 1, "item" : "apple", "price" : 10, "quantity" : 2, "date" : ISODate("2023-01-01T08:00:00Z") },
{ "_id" : 2, "item" : "grape", "price" : 20, "quantity" : 1, "date" : ISODate("2023-02-03T09:00:00Z") },
{ "_id" : 3, "item" : "melon", "price" : 5, "quantity" : 5, "date" : ISODate("2023-02-03T09:05:00Z") },
{ "_id" : 4, "item" : "apple", "price" : 10, "quantity" : 10, "date" : ISODate("2023-02-15T08:00:00Z") },
{ "_id" : 5, "item" : "melon", "price" : 5, "quantity" : 10, "date" : ISODate("2023-02-15T09:12:00Z") }
])

````

일자 데이터를 기준으로 그룹을 생성하고 accumulation 으로 addToSet, sum 을 이용합니다.

````
db.sales.aggregate(
   [
     {
       $group:
         {
           _id: { day: { $dayOfYear: "$date"}, year: { $year: "$date" } },
           itemsSold: { $addToSet: "$item" },
           total: {$sum: "$quantity"}
         }
     }
   ]
);

{
  _id: {
    day: 34,
    year: 2023
  },
  itemsSold: [
    'grape',
    'melon'
  ],
  total_price: 25
}
{
  _id: {
    day: 46,
    year: 2023
  },
  itemsSold: [
    'melon',
    'apple'
  ],
  total_price: 15
}
{
  _id: {
    day: 1,
    year: 2023
  },
  itemsSold: [
    'apple'
  ],
  total_price: 10
}
````
#### Aggregation Bucket
화가의 프로파일 정보에서 태어난 년도를 기준으로 하여 그룹을 생성 합니다. 년도는 10년을 기준으로 집계 합니다. 즉 1840 ~1850 년으로 집계 합니다.

````
db.artists.insertMany([
  { "_id" : 1, "last_name" : "Bernard", "first_name" : "Emil", "year_born" : 1868, "year_died" : 1941, "nationality" : "France" },
  { "_id" : 2, "last_name" : "Rippl-Ronai", "first_name" : "Joszef", "year_born" : 1861, "year_died" : 1927, "nationality" : "Hungary" },
  { "_id" : 3, "last_name" : "Ostroumova", "first_name" : "Anna", "year_born" : 1871, "year_died" : 1955, "nationality" : "Russia" },
  { "_id" : 4, "last_name" : "Van Gogh", "first_name" : "Vincent", "year_born" : 1853, "year_died" : 1890, "nationality" : "Holland" },
  { "_id" : 5, "last_name" : "Maurer", "first_name" : "Alfred", "year_born" : 1868, "year_died" : 1932, "nationality" : "USA" },
  { "_id" : 6, "last_name" : "Munch", "first_name" : "Edvard", "year_born" : 1863, "year_died" : 1944, "nationality" : "Norway" },
  { "_id" : 7, "last_name" : "Redon", "first_name" : "Odilon", "year_born" : 1840, "year_died" : 1916, "nationality" : "France" },
  { "_id" : 8, "last_name" : "Diriks", "first_name" : "Edvard", "year_born" : 1855, "year_died" : 1930, "nationality" : "Norway" }
]);
````

태어난 년도를 기준으로 하여 집계를 위해서 bucket을 이용하여 groupBy 항목으로 year_born을 하여 줍니다. 태어난 년도의 집계는 10년을 기준으로 category화는 boundaries레 작성 기준을 작성하여 줍니다. 

````
db.artists.aggregate( [
  {
    $bucket: {
      groupBy: "$year_born",                        // Field to group by
      boundaries: [ 1840, 1850, 1860, 1870, 1880 ], // Boundaries for the buckets
      default: "Other",                             // Bucket ID for documents which do not fall into a bucket
      output: {                                     // Output for each bucket
        "count": { $sum: 1 },
        "artists" :
          {
            $push: {
              "name": { $concat: [ "$first_name", " ", "$last_name"] },
              "year_born": "$year_born"
            }
          }
      }
    }
  }
] );


{
  _id: 1840,
  count: 1,
  artists: [
    {
      name: 'Odilon Redon',
      year_born: 1840
    }
  ]
}
{
  _id: 1850,
  count: 2,
  artists: [
    {
      name: 'Vincent Van Gogh',
      year_born: 1853
    },
    {
      name: 'Edvard Diriks',
      year_born: 1855
    }
  ]
}
{
  _id: 1860,
  count: 4,
  artists: [
    {
      name: 'Emil Bernard',
      year_born: 1868
    },
    {
      name: 'Joszef Rippl-Ronai',
      year_born: 1861
    },
    {
      name: 'Alfred Maurer',
      year_born: 1868
    },
    {
      name: 'Edvard Munch',
      year_born: 1863
    }
  ]
}
{
  _id: 1870,
  count: 1,
  artists: [
    {
      name: 'Anna Ostroumova',
      year_born: 1871
    }
  ]
}

````

#### Aggregation Unwind
다음과 같은 의류 정보가 있을 때 의류를 기준으로 가능한 사이즈 정보가 배열화 되어 있습니다. 각 사이즈를 구분하여 문서화를 합니다.    
사이즈가 없는 의류들은 이를 포함 함니다.

````
db.clothing.insertMany([
  { "_id" : 1, "item" : "Shirt", "sizes": [ "S", "M", "L"] },
  { "_id" : 2, "item" : "Shorts", "sizes" : [ ] },
  { "_id" : 3, "item" : "Hat", "sizes": "M" },
  { "_id" : 4, "item" : "Gloves" },
  { "_id" : 5, "item" : "Scarf", "sizes" : null }
]);
````
배열로 되어 있는 값을 하나의 문서로 만들어 주기 위해 unwind를 사용합니다. 기본적으로 지정된 array (size)에 값이 없는 경우 연산에서 제외 합니다. 이를 포함하도록 하는 옵션은 preserveAndEmptyArrays입니다.


````

db.clothing.aggregate( [
   { $unwind: { path: "$sizes", preserveNullAndEmptyArrays: true } }
] );

{
  _id: 1,
  item: 'Shirt',
  sizes: 'S'
}
{
  _id: 1,
  item: 'Shirt',
  sizes: 'M'
}
{
  _id: 1,
  item: 'Shirt',
  sizes: 'L'
}
{
  _id: 2,
  item: 'Shorts'
}
{
  _id: 3,
  item: 'Hat',
  sizes: 'M'
}
{
  _id: 4,
  item: 'Gloves'
}
{
  _id: 5,
  item: 'Scarf',
  sizes: null
}
````


#### 좌표 정보 검색
sample_airbnb.listingsAndReviews 컬렉션에는 숙박 시설 정보를 가진 문서이며 해당 숙박시설의 지리 정보가 좌표로 입력 되어 있습니다. (address.location)  마드리드 공항을 기준으로 가장 가까운 숙박 시설을 검색 합니다.  마드리드 공항의 좌표 정보는 -3.56744, 40.49845 이며 검색하려는 숙박 시설을 Hotel 과 Apartments 입니다. 보는 데이터는 숙박 시설의 이름과 주소, 떨어진 거리, 금액으로 합니다. (name, property_type, summary, address, price)

검색은 geoNear 스테이지를 이용하여 검색 하며 전체 데이터중 보고자 하는 필드만을 제한 하기 위해 project 스테이지를 사용 합니다.


````
db.listingsAndReviews.aggregate( [
   { $geoNear: {
  near: { type: 'Point', coordinates: [ -3.56744, 40.49845]},
  distanceField:"distance",
  key:"address.location",
  query: {property_type: {$in: ["Hotel","Apartment"]}},
  spherical: true
} },
{ $project: {name:1, property_type:1, 
summary:1, address:1, 
price:1, distance:1}
}
] );

{
  _id: '18426634',
  name: 'Private room',
  summary: 'Intermarche',
  property_type: 'Apartment',
  price: Decimal128("78.00"),
  address: {
    street: 'Porto, Porto, Portugal',
    suburb: '',
    government_area: 'Canedo, Vale e Vila Maior',
    market: 'Porto',
    country: 'Portugal',
    country_code: 'PT',
    location: {
      type: 'Point',
      coordinates: [
        -8.4022,
        41.00962
      ],
      is_location_exact: false
    }
  },
  distance: 411593.1181197846
}
{
  _id: '21883829',
  name: 'Terraço',
  summary: `La maison dispose de 4 chambres et un canapé-lit, est bon pour le repos et est situé dans un quartier calme et magnifique. Il est proche de la plage d'Espinho et si vous préférez visiter une zone naturelle, nous avons les "Passadiços de Arouca", c'est une zone très belle et relaxante. Ici, dans ce village, nous avons aussi un excellent restaurant qu'ils dépensent des repas économiques et très bons.`,
  property_type: 'Apartment',
  price: Decimal128("40.00"),
  address: {
    street: 'Aveiro, Aveiro, Portugal',
    suburb: '',
    government_area: 'Lobão, Gião, Louredo e Guisande',
    market: 'Porto',
    country: 'Portugal',
    country_code: 'PT',
    location: {
      type: 'Point',
      coordinates: [
        -8.45777,
        40.98082
      ],
      is_location_exact: true
    }
  },
  distance: 415895.69466630125
}
{
  _id: '23391765',
  name: 'Cozy Flat, São João da Madeira',
  summary: 'Um apartamento com quarto de cama de casal, wc’s privativos, sala e cozinha, equipado com tudo que precisa. Uma excelente opção para amantes de caminhadas, cultura e lazer. Se o seu motivo de visita for meramente profissional vai sentir-se em casa. Situa-se junto dos serviços e comércios necessários e a área é servida por transportes públicos.  Para além de São João da Madeira, poderá visitar Santa Maria da Feira e Estarreja em poucos minutos. Localiza-se a cerca de 45 km do Porto e Aveiro.',
  property_type: 'Apartment',
  price: Decimal128("45.00"),
  address: {
    street: 'São João da Madeira, Aveiro, Portugal',
    suburb: '',
    government_area: 'São João da Madeira',
    market: 'Porto',
    country: 'Portugal',
    country_code: 'PT',
    location: {
      type: 'Point',
      coordinates: [
        -8.48714,
        40.895
      ],
      is_location_exact: true
    }
  },
  distance: 417500.0627241467
}
{
  _id: '30341193',
  name: 'Recanto agua',
  summary: 'No meio da cidade, da para andar sempre a pé.',
  property_type: 'Apartment',
  price: Decimal128("25.00"),
  address: {
    street: 'São João da Madeira, Aveiro, Portugal',
    suburb: '',
    government_area: 'São João da Madeira',
    market: 'Porto',
    country: 'Portugal',
    country_code: 'PT',
    location: {
      type: 'Point',
      coordinates: [
        -8.49682,
        40.89102
      ],
      is_location_exact: true
    }
  },
  distance: 418278.04115931864
}
````


[0]: https://github.com/MongoDBAtlas/LGUPrompthon/tree/main/02.CRUD%20and%20MQL/AtlasSpringDriver/

[1]: https://github.com/MongoDBAtlas/LGUPrompthon/tree/main/02.CRUD%20and%20MQL/AtlasSpringMongoTemplate/

[2]: https://github.com/MongoDBAtlas/LGUPrompthon/tree/main/02.CRUD%20and%20MQL/AtlasSpringJPA/