1. Запустите проект
2. Напишите боту TestTaskBot или @dias_turusbekov_telegram_bot
3. по адресу POST localhost:8080/register body json = {"login": "test_login", "password": "test_password", "name": "test_name"}
4. если чат будет открыт он вам вернет chat_id
5. по дефолду вы можете отправить сообщения на адрес localhost:8080/defaultMessage?message=test_test и увидите на канале ТестовыйКанал или @diasturusbekov
6. localhost:8080sendMessage?chatId= {ваш chat_id} &message=test_test
