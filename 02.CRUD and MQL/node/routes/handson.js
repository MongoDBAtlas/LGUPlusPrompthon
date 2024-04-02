const express = require('express');
const MongoClient = require('mongodb').MongoClient;
const dotenv = require('dotenv');
const router = express.Router();

dotenv.config();
const mongodb = process.env.MONGODB;
const databasename = process.env.DATABASE;
const collection = process.env.COLLECTION;
const connectionString = mongodb;
      

const client = new MongoClient(connectionString);

const database = client.db(databasename);
const handson = database.collection(collection);


router.route('/').get( async(req, res, next) => {
    try{
        
        await client.connect();
        const query = {};
        const cursor = await handson.find(query);
        
        const results = await cursor.toArray();
        let outcomes = '';
        if (results.length > 0) {
            results.forEach((result, i) => {
                outcomes += JSON.stringify(result);
                console.log(result);
            });
        } else {
            console.log('No Data');
        }

        console.log("Outcomes : "+outcomes);
        res.status(200).json(results);

    } catch(e)
    {
        console.log("Error");
        console.error(e);
        res.status(404).json({});

    }  
})
.post(async (req, res, next) => {
    console.log("Request:"+ JSON.stringify(req.body));
    try{
        await client.connect();
        const exampleDocument = req.body;
       
        await handson.updateOne(
            { ssn: exampleDocument["ssn"] },
            { $set: exampleDocument },
            { upsert: true }
          );

        console.log("POST log");
        res.status(201).json(exampleDocument);
    }catch (err)
    {
        console.error(err);
        next(err);
    } 
}).delete(async (req, res, next) => {
    try{
        await client.connect();
        const deleteSSN = req.query.ssn;
       
        const result = await handson.deleteOne(
            { ssn: deleteSSN }
          );

        console.log("Delete log"+deleteSSN);
        res.status(201).json(result);
    }catch (err)
    {
        console.error(err);
        next(err);
    } 
});

module.exports = router;