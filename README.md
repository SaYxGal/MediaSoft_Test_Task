# Инструкция по запуску приложения (локально)

1. В файле application-local.properties надо указать информацию о PostgreSQL
   (пустая бд с указанным в этом файле названием должна быть создана вручную).
   Путь к файлу: /src/main/resources
2. Находясь в cmd в папке проекта(productWarehouse) ввести gradlew build
3. cd build/libs
4. java -Dspring.profiles.active=local -jar productWarehouse-0.0.1-SNAPSHOT.jar

# Запуск с помощью Docker
1. Предварительно надо собрать проект аналогично пункту 2 при запуске приложения локально.
2. Находясь в корневой папке, содержащей файл docker-compose.yml, написать через консоль команду docker-compose up.

# Путь к Swagger - http://localhost:8080/swagger-ui/index.html