
const express = require('express');
const assert = require('assert');

const dbName = 'test';

const MongoClient = require('mongodb').MongoClient;
//mongodb://admin:Rbehd1768@10.0.0.6/test?retryWrites=true&w=majority
const client = new MongoClient('mongodb://admin:****@mongo.myjson.site:27001,mongo.myjson.site:27002,mongo.myjson.site:27003/test?authSource=admin&directConnection=true&retryWrites=true&w=majority&ReadPreference=primary', {
  tlsCAFile: 'tls/example-ca-pub.crt',
  tlsCertificateKeyFile: 'tls/example-client.pem',
  tlsAllowInvalidCertificates: true
});

const connect = () => {
    client.connect(function(err) {
        assert.equal(null, err);
        console.log("Connected successfully to server");
    
        const db = client.db(dbName);
    });
}

module.exports = connect;
