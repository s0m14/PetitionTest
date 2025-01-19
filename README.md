# Пошаговая инструкция для запуска проекта

## Технологический стек
- **Java** 17 или выше
- **Spring Boot**
- **Spring Data JPA**
- **MySQL**
- Tool for testing APIs : **Postman**
---

## Установка и запуск

1. Убедитесь, что установлен MySQL и создайте схему бд (например, `newschema`).
2. Настройте файл `application.properties` с параметрами для подключения к базе данных. Пример конфигурации:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/newschema
spring.datasource.username=springstudent
spring.datasource.password=springstudent
security.jwt.secret-key=3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
server.port=8080
```
3. Запустите через ваш IDE или по команде
```bash
./mvnw spring-boot:run
```
4. Сервер будет доступен по адресу: 
- http://localhost:8080
---

## Контроллер Auth
- `POST /auth/sign-up`: Регистрация нового пользователя.Нужно отправить JSON-запрос, с полями username,email,password. Затем вы получите JWT токен. В Postman надо выбрать тип авторизации Bearer Token и вставить туда сгенерированный токен.

- `POST /auth/sign-in` : Вход в систему.Отправьте логин и пароль в JSON-запросе.При успешной авторизации вы получите JWT токен для дальнейшей работы с API.
-----

## Контроллер Home
- `GET /home/petitions` : Получить список всех доступных петиций.
- `GET /home/vote/{petition_id}`: Проголосовать за петицию под определенным Id.
`Примечание: Пользователь может проголосовать только один раз за каждую петицию. В противном случае будет выброшена ошибка Runtime exception.`
- `GET /home/devote/{petition_id}` : Пользователь может убрать голос только один раз. Если он не голосовал, будет выброшена ошибка Runtime exception.

-------
## Пример API response

`POST http://localhost:8080/auth/sign-in`

- Тело запроса
```JSON
{
"username":"s0m",
"password":"qwerty"
}
```

- Ответ
```JSON
{
"token":"eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwiaWQiOjEwMiwidXNlcm5hbWUiOiJzMG0iLCJzdWIiOiJzMG0iLCJpYXQiOjE3MzczMjc1MTIsImV4cCI6MTczNzQ3MTUxMn0.Ap7gsN99v8azLQ-xbW_eLGJePQGH0U1AkYokPljiWHs"
}
```

----
Примечание: Поскольку в проекте нет административной роли для добавления и изменения петиций, они будут добавляться вручную. По дефолту есть 2 петиции.Вы можете добавить их через файл `data.sql`.
