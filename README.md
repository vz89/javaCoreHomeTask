# javaCoreHomeTask

Консольное CRUD приложение, которое имеет следующие сущности:

Developer
Skill
Account
AccountStatus (enum ACTIVE, BANNED, DELETED)

Developer -> Set<Skill> skills + Account account
Account -> AccountStatus
  
Пользователь в консоли имеет возможность создания, получения, редактирования и удаления данных.

В качестве хранилищ данных могут использоваться
- текстовые файлы
- файлы, хранящие информацию в формате JSON (для работы с JSON используется библиотека GSON)
- база данных (реализация как на чистом JDBC так и с использованием Hibernate)

Архитектура приложения построена с использованием подхода MVC.
Для сборки проекта и импорта библиотек используется MAVEN.
Для миграции баз данных используется Liquibase.
Для тестирования используется JUnit и Mockito.
Для автоматизации и сборки используется Travis.

Также в приложении реализован REST API с использованием Servlet. Рабочее приложением развернуто на heroku.com 

Используемые технологии: Java, StreamAPI, JDBC, IO/NIO, Hibernate, Servlet, Maven, Liquibase, Travis, Heroku. 
