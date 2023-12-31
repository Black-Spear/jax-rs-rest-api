# Student and Learner Management API

This repository contains a simple Java EE (J2EE) REST API for managing learners and students in a MySQL database. The API allows you to perform basic operations such as adding, deleting, modifying, and retrieving all students from the database.

## Getting Started

Follow the instructions below to get started with this API.

### Prerequisites

- Java EE (J2EE) development environment
- MySQL database
- Maven for dependency management

### Installation

1. Clone this repository.
2. Build the project using Maven.
3. Deploy the WAR file to your Java EE server.
4. Update the database configuration in `src/main/resources/application.properties`.
5. Start the server and access the API.

## API Endpoints

The following example is in HTTPie

![Api endpoints example](api.png "API endpoints illustrated")

## Usage

You can use a tool like `curl` or HTTPie to interact with the API. For example, to get all students:

```bash
curl -X GET http://localhost:8080/Learner_Etudiant_API/RestApi/learners
```

Have fun ☕