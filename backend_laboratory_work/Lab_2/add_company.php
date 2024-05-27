<?php
require_once("mydb.php");

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $companyName = $_POST["companyName"];
    $numberWorkers = $_POST["numberWorkers"]; 
    
    $sql = "INSERT INTO firms (companyName, numberWorkers) VALUES ('$companyName', '$numberWorkers')";
    
    if ($connection->query($sql) === TRUE) {
        echo "Нова компанія успішно додана!";
    } else {
        echo "Помилка: " . $sql . "<br>" . $connection->error;
    }
    echo "<h3><a href='main.php'>Повернутись на головну сторінку сайту</a></h3>";
    $connection->close();
}
?>
