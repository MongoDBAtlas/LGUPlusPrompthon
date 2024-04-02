const express = require('express');
const MongoClient = require('mongodb').MongoClient;
const axios = require('axios')

const dotenv = require('dotenv');
const router = express.Router();

dotenv.config();
const mongodb = process.env.MONGODB;
const databasename = process.env.DATABASE;
const collection = process.env.COLLECTION;
const connectionString = mongodb;
const APIKEY=process.env.OPENAPI;
      

const client = new MongoClient(connectionString);

const database = client.db(databasename);
const movieCollection = database.collection(collection);


router.route('/')
.post(async (req, res, next) => {
    console.log("Request:"+ JSON.stringify(req.body));
    try{

        const exampleDocument = req.body;
        const search_text = exampleDocument.text;


        const axios = require('axios');
        let data = JSON.stringify({
          "input": search_text,
          "model": "text-embedding-ada-002"
        });
        
        let config = {
          method: 'post',
          maxBodyLength: Infinity,
          url: 'https://api.openai.com/v1/embeddings',
          headers: { 
            'Content-Type': 'application/json', 
            'Authorization': 'Bearer '+APIKEY, 
          },
          data : data
        };
        
        let vector;
        await axios.request(config)
        .then((response) => {
          //console.log(JSON.stringify(response.data));
          vector = response.data.data[0].embedding;
        })
        .catch((error) => {
          console.log(error);
        });
        
        //console.log(vector)
        await client.connect();

        let search = { "$vectorSearch": { "index": "vector_index", "path": "plot_embedding", "queryVector": vector, "numCandidates": 200, "limit": 10 } };
        let project = { "$project": { "_id": 0, "title": 1, "genres": 1, "plot": 1, "released": 1, "score": { $meta: "vectorSearchScore" } } }
        const cursor = await movieCollection.aggregate([ search, project ] );

        const results = await cursor.toArray();

        let outcomes = '';
        if (results.length > 0) {
            results.forEach((result, i) => {
                outcomes += JSON.stringify(result);
                //console.log(result);
            });
        } else {
            console.log('No Data');
        }

        res.status(200).json(results);
    }catch (err)
    {
        console.error(err);
        next(err);
    } 
});

module.exports = router;