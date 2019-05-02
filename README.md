# stw-service

Веб-сервис, разработанный в ходе прохождения курса "Разработка веб-сервиса на Java" от Mail.ru на платформе Stepik.org.

## Часть 1

### home work №1

Написать сервлет, который будет обрабатывать запросы на /mirror
При получении GET запроса с параметром key=value сервлет должен вернуть в response строку содержащую value.
Например, при GET запросе /mirror?key=hello сервер должен вернуть страницу, на которой есть слово "hello".

### home work №2

Написать сервер с двумя сервлетами:
SignUpServlet для обработки запросов на signup и
SignInServlet для обработки запросов на signin

Сервлеты должны слушать POST запросы с параметрами
login
password

При получении POST запроса на signup сервлет SignUpServlet должн запомнить логин и пароль в AccountService.
После этого польователь с таким логином считается зарегистрированным.
При получении POST запроса на signin, после регистрации, SignInServlet проверяет,
логин/пароль пользователя. Если пользователь уже зарегистрирован, север отвечает

Status code (200)
и текст страницы:
Authorized: login

если нет:
Status code (401)
текст страницы:
Unauthorized

### home work №3

Для запоминания пользователя AccountService должен использовать базу данных.
Для теста используйте базу H2 над файлом в той же директории, что и src

            String url = "jdbc:h2:./h2db";
            String name = "test";
            String pass = "test";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

Для хранения данных пользователя используйте таблицу users:
create table if not exists users (id bigint auto_increment, login varchar(256), password varchar(256), primary key (id));
Сервер должен создавать таблицу при старте если она не существует.

При получении запроса на signup сервлет должен обратиться к DBService и записать логин и пароль в таблицу.

### home work №4

Написать вебсокет, который будет обрабатывать запросы на /chat
При получении сообщения сокет должен отправить это сообщение обратно.

## Часть 2

### home work №5

Написать AccountServer, который будет содержать переменную "разрешенное количество пользователей на сервере" (usersLimit).
Задать значение этой переменной по умолчанию -- 10.

Написать сервлет, который будет обрабатывать запросы на /admin
При получении GET запроса возвращать значение usersLimit.
То есть, сразу после старта сервер на GET запрос на /admin вернет страницу:
10

Вывести значение usersLimit в JMX с именем:
Admin:type=AccountServerController.usersLimit
Сделать значение этой переменной изменяемым.

### home work №6

Добавить в сервер класс resources.TestResource

Написать ResourceServer, который будет содержать ссылку на TestResource.
Вывести ResourceServer в JMX с именем:
Admin:type=ResourceServerController
Сделать переменные "name" и "age" доступными для чтения из jmx клиента.

Написать сервлет, который будет обрабатывать запросы на /resources
При получении POST запроса с параметром path=path_to_resource
прочитает ресурс TestResource из указанного в параметре файла и сохранит ссылку в ResourceService

После чтения, значения name и age должны быть доступны по JMX.
