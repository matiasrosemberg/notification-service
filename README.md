# Rate-Limited Notification Service

### Overview

This is a simple service that will let you send a notification without exceeding the rate limit of the preconfigured
rates.
It has been designed to be used with a redis running server.

### Usage

In order to deploy the server, you need to have a redis server running. You can use the docker-compose file provided in
the repository to build the entire application, and run it with a working redis server in your localhost.

### Configuration

The configuration is done through `application.yml`. The following variables are available:

- `spring.redis.host`: The host of the redis server. Defaults to `localhost`.
- `spring.redis.port`: The port of the redis server. Defaults to `6379`.
- `spring.redis.password`: The password of the redis server. Defaults to `""`.
- `message-rates.max-per-type`: The rates to be used. In a map format.
- `message-rates.window-per-type`: The windows of time to be used for that kind of message. In a map format.

### Run

You can run the application with the following command (you must have started a redis before, with 172.0.0.1:6379)

```bash
spring-boot:run
```

### Run with docker

A docker-compose file is provided in the repository. You can run it with the following command:

```bash
  docker-compose up --build
```

You have to have docker and docker-compose installed and running in your machine.