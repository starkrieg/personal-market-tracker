## Market Data Server

A Java service that serves stored market data. It gives access to the data extracted and loaded by the DataETL project.

Currently only support Postgres as a match to DataETL project.

Uses Java 21, Spring 4.0.3 and Gradle 8.14

Note: Mainly meant for local use, no authentication methods have been added!

### API Endpoints

MarketController serves multiple endpoints for obtaining the list of available Tickets and their data.

Put your own client app in front of it and display the data as you want.

### Client-less option

Thymeleaf is used to serve a "index" page so data can be viewed even without pretty web clients.

It allows for ticket selection and shows a simple line graph based on Chart.js. A simple caching layer has been added with JS to facilitate use.

### Deploy

Can be built/run locally on port 8080 or run with docker.

Check the folder `docker` to see docker build and docker-compose commands.

### Pending Features

- Addition of unit tests
- Addition of paginations and controls on endpoints
- Implementation of optional caching with Redis for third-party clients
- TBD