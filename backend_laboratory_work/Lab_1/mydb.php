<?php
$servername = "localhost"; 
$username = "admin"; 
$password = "admin";
$db = "mydb";

$showDebugMessages = false; 

// Підключення до MySQL сервера
$link = mysqli_connect($servername, $username, $password);

// Перевірка з'єднання
if (!$link) {
    die("Неможливо встановити з'єднання: " . mysqli_connect_error());
} else {
    if ($showDebugMessages) {
        echo "З'єднання з сервером MySQL встановлено.", "<br>";
    }
}

// Створення бази даних, якщо її не існує
$query = "CREATE DATABASE IF NOT EXISTS $db";
if (mysqli_query($link, $query)) {
    if ($showDebugMessages) {
        echo "База даних $db успішно створена.", "<br>";
    }
} else {
    die("Помилка при створенні бази даних: " . mysqli_error($link));
}

// Підключення до бази даних
$connection = new mysqli($servername, $username, $password, $db);

// Перевірка з'єднання з базою даних
if ($connection->connect_error) {
    die("Помилка з'єднання з базою даних: " . $connection->connect_error);
} else {
    if ($showDebugMessages) {
        echo "З'єднання з базою даних $db успішно встановлено.", "<br>";
    }
}
?>
