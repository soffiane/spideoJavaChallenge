# Spideo Java Challenge 

The goal of this project is to develop an API that manage an auction house

In this project, you can create, delete and list auction house
You can also create,list and delete auctions attached to an auction house
And you can also create Users and Bids on selected auction running
At the end of an auction, you will now the userName who made the best bid

## Getting Started

To get started :
- check out or clone the project on GitHub
- generate the .jar with the maven command : mvn package
- launch the generated jar with command : java -jar auction-0.0.1-SNAPSHOT.jar

## Running the tests

You can test the API with Postman

I have joined a file with all my http request made in Postman : testSpideoApi.postman_collection.json

This file can be imported into your Postman to test all features

The database is a H2 in-memory database you can access at : http://localhost:8080/h2-console/

## Built With

* [SpringBoot](https://spring.io/projects/spring-boot) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [H2](https://www.h2database.com/html/main.html) - In-memory database

## Versioning

For the versions available, see my github(https://github.com/soffiane/spideoJavaChallenge). 

## Authors

* **Soffiane Boudissa** - *Initial work* - https://github.com/soffiane)