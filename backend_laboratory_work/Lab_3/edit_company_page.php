<!DOCTYPE html>
<html>
<head>
    <title>Редагувати компанію</title>
</head>
<body>
    <h2>Редагувати компанію</h2>
    <?php
    require_once("mydb.php");
    
    $sql = "SELECT * FROM firms";
    $result = $connection->query($sql);
    
    if ($result->num_rows > 0) {
        echo "<table border='1' style='border-collapse: collapse; width: 550px; height: 300px; text-align: center;'>";
        echo "<tr>";
        echo "<th style='background-color: #f2f2f2; color: #333; font-weight: bold;'>ID</th>";
        echo "<th style='background-color: #f2f2f2; color: #333; font-weight: bold;'>Назва Компанії</th>";
        echo "<th style='background-color: #f2f2f2; color: #333; font-weight: bold;'>Кількість працівників</th>";
        echo "<th style='background-color: #f2f2f2; color: #333; font-weight: bold;'>Опції</th>"; 
        echo "</tr>";
        
        while($row = $result->fetch_assoc()) {
            echo "<tr>";
            echo "<td>".$row["idFirm"]."</td>";
            echo "<td>".$row["companyName"]."</td>";
            echo "<td>".$row["numberWorkers"]."</td>";
            echo "<td><a href='edit_company.php?id=" . $row["idFirm"] . "'>Редагувати</a></td>"; 
            echo "</tr>";
        }
        
        echo "</table>";
    } else {
        echo "Фірми відсутні.";
    }
        echo "<h3><a href='main.php'>Повернутись на головну сторінку сайту</a></h3>";    

    $connection->close();
    ?>
</body>
</html>
