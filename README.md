# Charter Enterprise MOTD Sample Project
A small project to help assess candidate experience with web services and our technology stack.

## Instructions
We have provided a web service that provides a "message of the day", similar to what you might see logging into a 
Unix system. Unfortunately, at Charter things don't always go as planned and we need to change the message.

A message history is kept for auditing purposes.

Iterative requests for the MOTD returns the new message, if it has been changed.

### Usage
* To compile:
```mvn clean package```

* To run:
```mvn spring-boot:run```

* Or run (after ```mvn install```):
```java -jar target\motd-code-sample-1.0-SNAPSHOT.jar```

* To see the message:
```curl localhost:8080```

* To see the history:
```curl localhost:8080/history```

* To see the history formatted for a browser:
```localhost:8080/browserHistory```

* To see the history in reverse formatted for a browser:
```localhost:8080/browserHistoryReverse```

### Prerequisites
* Java 1.8
* Maven
* cURL
