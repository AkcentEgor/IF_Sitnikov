# language: ru

Функция: Счетчик заданий

  Сценарий: Проверка счетчика заданий
    Когда Пользователь вводит логин и пароль
    Когда Пользователь переходит на страницу проекта TEST
    Дано Подсчитано количество задач 1
    Когда Пользователь создает новую задачу
    И Пользователь заполняет тему и нажимает кнопку Создать
    И Система переходит в открытые задачи и обновляет страницу
    Дано Подсчитано количество задач 2
    Тогда Система проверяет изменилось ли количество задач