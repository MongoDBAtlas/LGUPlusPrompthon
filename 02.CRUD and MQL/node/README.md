<img src="https://companieslogo.com/img/orig/MDB_BIG-ad812c6c.png?t=1648915248" width="50%" title="Github_Logo"/> <br>

# MongoDB Atlas Training for LGU Prompthon

#### Env File
.env 파일 생성

MongoDB 접근 정보 및 사용할 Database 지정    
````
MONGODB=mongodb+srv://USER_ID:PASSWORD@cluster1.5qjlg.mongodb.net/
DATABASE=DATABASENAME
COLLECTION=handson
````

#### Local Node test 

`````
$ npm install
$ npm install -D nodemon
$ npm start
`````

#### GET
````
curl --location --request GET 'http://localhost:3000/handson' \
--header 'Content-Type: application/json'
````

#### POST
`````
curl --location --request POST 'http://localhost:3000/handson' \
--header 'Content-Type: application/json' \
--data-raw '{
        "ssn": "123-001-0000",
        "address": {
            "street": "Seoul Jongro-gu, Sejon-ru ",
            "city": "Seoul",
            "zip": 123142
        },
        "name": {
            "firstName":"Jon",
            "lastName" : "Doe"
        },
        "phone": "010-2345-9999"
    }'
`````


-