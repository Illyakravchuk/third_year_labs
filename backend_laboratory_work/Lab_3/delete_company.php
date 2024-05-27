<?php
require_once("mydb.php");

if ($_SERVER["REQUEST_METHOD"] == "GET" && isset($_GET["id"])) {
    $companyId = $_GET["id"];
    
    $sql = "DELETE FROM firms WHERE idFirm = '$companyId'";
    
    if ($connection->query($sql) === TRUE) {
        echo "Компанія успішно видалена!";
    } else {
        echo "Помилка: " . $sql . "<br>" . $connection->error;
    }
    
    $connection->close();
} else {
    echo "Помилка: Неправильний запит.";
}
echo "<h3><a href='main.php'>Повернутись на головну сторінку сайту</a></h3>";
?>
