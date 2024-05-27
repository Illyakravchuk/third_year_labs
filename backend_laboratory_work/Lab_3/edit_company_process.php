<?php
require_once("mydb.php");

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $companyId = $_POST["companyId"];
    $companyName = $_POST["companyName"];
    $numberWorkers = $_POST["numberWorkers"];
    
    $sql = "UPDATE firms SET companyName = '$companyName', numberWorkers = '$numberWorkers' WHERE idFirm = '$companyId'";
    
    if ($connection->query($sql) === TRUE) {
        echo "Інформація про компанію успішно оновлена!";
    } else {
        echo "Помилка: " . $sql . "<br>" . $connection->error;
    }
    echo "<h3><a href='main.php'>Повернутись на головну сторінку сайту</a></h3>";
    $connection->close();
}
?>
