<?php
require_once("mydb.php");

if (isset($_GET['idFirm'])) {
    $idFirm = $_GET['idFirm'];
    $sql = "SELECT * FROM contracts WHERE idFirm = $idFirm";

    $result = $connection->query($sql);

    if ($result->num_rows > 0) {
        echo "<table border='1' style='border-collapse: collapse; width: 450px; height: 180px; text-align: center;'>";
        echo "<tr>";
        echo "<th style='background-color: #f2f2f2; color: #333; font-weight: bold; '>ID</th>";
        echo "<th style='background-color: #f2f2f2; color: #333; font-weight: bold;'>Контракти</th>";
        echo "<th style='background-color: #f2f2f2; color: #333; font-weight: bold;'>Початок контракту</th>";
        echo "<th style='background-color: #f2f2f2; color: #333; font-weight: bold;'>Кінець контракту</th>";
        echo "</tr>";
       
   while($row = $result->fetch_assoc()) {
    echo "<tr>";
    echo "<td>".$row["idContract"]."</td>";
    echo "<td>".$row["contractName"]."</td>";
    echo "<td>".$row["startDate"]."</td>";
    echo "<td>".$row["endDate"]."</td>";
    echo "</tr>";
   }
   echo "</table>";
    } else {
        echo "У фірми немає контрактів.";
    }
}  

 $connection->close();
 ?>