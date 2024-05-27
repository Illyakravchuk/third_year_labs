<?php
require_once("mydb.php");

if(isset($_POST['search'])) {
    $start_date = $_POST['start_date'];
    $end_date = $_POST['end_date'];
    
    // SQL-запит для пошуку співпадінь за діапазоном дат та сортуванням по ID
    $sql = "SELECT firms.idFirm, firms.companyName, firms.numberWorkers, contracts.contractName, contracts.startDate, contracts.endDate
            FROM firms 
            LEFT JOIN contracts ON firms.idFirm = contracts.idFirm 
            WHERE DATE(contracts.startDate) >= '$start_date' AND DATE(contracts.endDate) <= '$end_date'
            ORDER BY firms.idFirm ASC";
    
    $result = $connection->query($sql);
    
    if ($result->num_rows > 0) {
        echo "<table border='1' style='border-collapse: collapse; width: 650px; text-align: center;'>";
        echo "<tr>";
        echo "<th style='background-color: #f2f2f2; color: #333; font-weight: bold;'>ID</th>";
        echo "<th style='background-color: #f2f2f2; color: #333; font-weight: bold;'>Назва Компанії</th>";
        echo "<th style='background-color: #f2f2f2; color: #333; font-weight: bold;'>Кількість працівників</th>";
        echo "<th style='background-color: #f2f2f2; color: #333; font-weight: bold;'>Контракти</th>";
        echo "<th style='background-color: #f2f2f2; color: #333; font-weight: bold;'>Дата початку контракту</th>";
        echo "<th style='background-color: #f2f2f2; color: #333; font-weight: bold;'>Дата закінчення контракту</th>";
        echo "</tr>";

        while($row = $result->fetch_assoc()) {
            echo "<tr>";
            echo "<td>".$row["idFirm"]."</td>";
            echo "<td>".$row["companyName"]."</td>";
            echo "<td>".$row["numberWorkers"]."</td>";
            echo "<td>".$row["contractName"]."</td>";
            echo "<td>".$row["startDate"]."</td>";
            echo "<td>".$row["endDate"]."</td>";
            echo "</tr>";
        }
        echo "</table>";
    } else {
        echo "Немає результатів для введеного діапазону дат.";
    }
}
echo "<h3><a href='search_date.php'>Повернутись назад до вводу діапазону дат</a></h3>";
echo "<h3><a href='main.php'>Повернутись на головну сторінку сайту</a></h3>";
?>
