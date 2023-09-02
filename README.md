# kafkaSimpleRegistration
Test project for study (first steps to message broker kafka)

This test task contains two apps:
- registration (this app)
- [email sender](https://github.com/pakskiy/kafkaEmailsSender)

This app is only need for simple user registration and putting message into Kafka message broker.

# Testing

1. Run _docker-compose.yaml_ from **docker** folder.
2. Run Email sender application with **.env** file
3. Run this application [**Simple Registration**]
4. Send POST request with valid email

For registration send POST request with parameters:

```
curl -X POST "http://localhost:8087/api/v1/public/register" 
     --header "Content-Type: application/json" 
     --data-raw "{\"first_name\":\"Java\", \"last_name\":\"Kafka\", \"email\":\"javakafka@gmail.com\"}"
```
If everything is successful, you will receive a 200OK response. Otherwise - error code

If successful, you will receive an email.

PS: For this test I used Yandex email service