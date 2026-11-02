package com.example.journalingapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@Slf4j
public class JournalingAppApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JournalingAppApplication.class, args);
        log.info("GIVEN APPLICATION HAS STARTED AT : {}  is auto frozen or not ?  {}", context.getClass().getNestHost(), context.getBeanFactory().isConfigurationFrozen());
    }

}
/*
    With the help of MongoDatabaseFactory :-behind the seen we're make a databases connection
    and sessions i.e. Transactional Controller
    Interface                  implementation class
            |                              |
PlatformTransactionManager --> MongoTRansactionManager --> Own Implementation interface / class.
due to we will be access the properties over that

@Transactional :-annotations to doing all or none approach.
is used in the future applying the property ATOMICITY ,ISOLATION ,
internally it will e make a transaction contex and so on
transactions is to make successfully...
@EnableTransactionManagement --> @Transaction :- whole or not ?

Logging -->
Java Util Logging --> JUL
is one of the simplest technique to implement a logging.

Log4j2 -->
flexible to used advance features.

LogBack --> by-default configuration.
is embaded by spring container.
logback.xml -->using file formatt.
to implement it into the src/main/resources directory.

Logging lavels :- is categorized log based
TRACE
DEBUG
default logging is enables for following lavels.
i.e
INFO
WARN
ERROR

@Slf4j & @Log4j2  --> you can used to automatically
inject logging instance in our application.

SonarQube :- used to check code quality.
SonarCube , SonarLint , SonarClaude , GitHub.
to deploy.

 */
