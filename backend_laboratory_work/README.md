#  Проектування бази даних про договори

### Специфікація:
- Назва фірми-клієнта
- Вид договору
- Термін дії

## Лабораторні роботи

### Лабораторна робота №1: PHP & MySQL

**Тема:** Установка набору дистрибутивів XAMPP/Denwer. Створення бази даних MySQL. Просте виведення даних. Обмін даними між сторінками, використовуючи гіперпосилання.

**Завдання:**
1. Виконати установку набору дистрибутивів XAMPP.
2. Засобами мови PHP створити структуру бази даних у відповідності зі своїм варіантом.
3. Додати відповідні таблиці засобами PHP.
4. Додати користувачів БД.
5. За допомогою утиліти phpMyAdmin виконати введення інформації в таблицю бази даних і встановити зв'язки між таблицями.
6. Засобами PHP і SQL виконати виведення даних з таблиці бази даних на WEB-сторінку.
7. Створити ще одну сторінку сайту і здійснити обмін даними між сторінками, використовуючи гіперпосилання.

### Лабораторна робота №2: PHP & MySQL

**Тема:** Введення, редагування та видалення даних з БД. Сортування даних.

**Завдання:**
1. Створити HTML-сторінку, яка містить форму для додавання запису в базу даних.
2. Створити HTML-сторінку, що виконує редагування запису в таблиці.
3. Створити HTML-сторінку, що виконує видалення запису з таблиці бази даних.
4. Створити HTML-сторінку, що виконує сортування записів в таблиці за зростанням.

### Лабораторна робота №3: PHP & MySQL

**Тема:** Створення сторінки статистики сайту. Пошук інформації по сайту.

**Завдання:**
1. Визначення кількості записів у таблиці.
2. Визначити кількість записів, доданих за останній місяць.
3. Визначення останнього доданого запису.
4. Пошук інформації по сайту.

### Лабораторна робота №4: NodeJS

**Тема:** Створення серверу за допомогою Express. Обробка маршрутів. Шаблонізація.

**Завдання:**
1. Створення серверу за допомогою Express.
2. Обробка маршрутів.
3. Шаблонізація.

### Лабораторна робота №5: NodeJS

**Тема:** Робота з БД MongoDB. Додаток для реалізації CRUD операцій в БД.

**Завдання:**
1. Робота з БД MongoDB.
2. Додаток для реалізації CRUD операцій в БД.

### Лабораторна робота №6: GraphQL

**Тема:** Створення Schema GraphQL та Resolvers. Створення Query та Mutation.

**Завдання:**
1. Створення Schema GraphQL та Resolvers.
2. Створення Query та Mutation.
3. Дослідження Query та Mutation в Postman.

## Як запустити PHP код з базою даних MySQL у XAMPP (лабораторні 1, 2, 3)

Щоб запустити PHP код, який працює з базою даних MySQL, виконайте наступні кроки:

- Завантажте та встановіть XAMPP з [офіційного сайту](https://www.apachefriends.org/index.html).
- Запустіть XAMPP та активуйте Apache і MySQL.

### Налаштування бази даних

- Відкрийте phpMyAdmin, перейшовши за адресою [http://localhost/phpmyadmin](http://localhost/phpmyadmin).
- Створіть нову базу даних для вашого проєкту.
- Імпортуйте структуру та дані бази даних, використовуючи файл .sql, який ви підготували.

### Налаштування проєкту

- Скопіюйте ваш PHP код у директорію `htdocs` в папці встановленого XAMPP. Наприклад, `C:\xampp\htdocs\Lab_3`.
- Відредагуйте файл конфігурації бази даних у вашому PHP коді, вказавши правильні параметри для підключення (хост, ім'я бази даних, користувач, пароль).

### Запуск проєкту

- Відкрийте браузер та перейдіть за адресою [http://localhost/Lab_3](http://localhost/Lab_3) (наприклад, для третьої лабораторної роботи).

## Як запустити сервер у Node.js (лабораторні 4, 5, 6)

Щоб запустити сервер, виконайте наступні кроки:

- Переконайтеся, що у вас встановлений Node.js на вашому комп'ютері.
- Відкрийте термінал і перейдіть до директорії проєкту.
- Виконайте наступну команду:

    ```bash
    npm run dev
    ```

  Якщо сервер успішно запущений, то можна перейти до наступного кроку.

- Після запуску сервера, перейдіть у браузері за адресою [http://localhost:5000](http://localhost:5000) для доступу до нього.