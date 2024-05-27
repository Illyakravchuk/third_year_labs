<?php
require_once("mydb.php");


$order_dir = isset($_GET['order_dir']) ? $_GET['order_dir'] : 'ASC'; 

$order_by = isset($_GET['order_by']) ? $_GET['order_by'] : 'idFirm';

$sql = "SELECT * FROM firms ORDER BY $order_by $order_dir";

$result = $connection->query($sql);

if ($result->num_rows > 0) {
    echo "<table border='1' style='border-collapse: collapse; width: 550px; height: 300px; text-align: center;'>";
    echo "<tr>";
    echo "<th style='background-color: #f2f2f2; color: #333; font-weight: bold;'><a href='?order_by=idFirm&order_dir=";
    echo $order_by == 'idFirm' ? ($order_dir == 'ASC' ? 'DESC' : 'ASC') : 'ASC';
    echo "'>ID</a></th>";
    echo "<th style='background-color: #f2f2f2; color: #333; font-weight: bold;'>Назва Компанії</th>";
    echo "<th style='background-color: #f2f2f2; color: #333; font-weight: bold;'><a href='?order_by=numberWorkers&order_dir=";
    echo $order_by == 'numberWorkers' ? ($order_dir == 'ASC' ? 'DESC' : 'ASC') : 'ASC';
    echo "'>Кількість працівників</a></th>";
    echo "<th style='background-color: #f2f2f2; color: #333; font-weight: bold;'>Контракти</th>";
    echo "</tr>";
    
    while($row = $result->fetch_assoc()) {
        echo "<tr>";
        echo "<td>".$row["idFirm"]."</td>";
        echo "<td>".$row["companyName"]."</td>";
        echo "<td>".$row["numberWorkers"]."</td>";
        echo "<td><a href='firm_contracts.php?idFirm=" . $row["idFirm"] . "'>Деталі про контракти " . $row["companyName"] . "</a></td>";
        echo "</tr>";
    }
    echo "</table>";
} else {
    echo "Фірми відсутні.";
}
    echo "<h3><a href='main.php'>Повернутись на головну сторінку сайту</a></h3>";

$connection->close();
?>
