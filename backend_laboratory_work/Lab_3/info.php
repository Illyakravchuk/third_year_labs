<?php
require_once("mydb.php");

// Підрахунок загальної кількості фірм
$count_sql = "SELECT COUNT(*) AS total_records FROM firms";
$count_result = $connection->query($count_sql);
$row = $count_result->fetch_assoc();
$total_records = $row['total_records'];

// Підрахунок загальної кількості контрактів
$count_sql = "SELECT COUNT(*) AS total_contracts FROM contracts";
$count_result = $connection->query($count_sql);
$row = $count_result->fetch_assoc();
$total_contracts = $row['total_contracts'];

$current_date = date('Y-m-d');
$current_month = date('Y-m');

// Підрахунок кількості контрактів, які почалися за поточний місяць
$count_started_sql = "SELECT COUNT(*) AS started_contracts FROM contracts WHERE startDate >= '{$current_month}-01' AND startDate <= '{$current_date}'";
$count_started_result = $connection->query($count_started_sql);
$row_started = $count_started_result->fetch_assoc();
$started_contracts = $row_started['started_contracts'];

// Підрахунок кількості контрактів, які завершилися за поточний місяць
$count_ended_sql = "SELECT COUNT(*) AS ended_contracts FROM contracts WHERE endDate >= '{$current_month}-01' AND endDate <= '{$current_date}'";
$count_ended_result = $connection->query($count_ended_sql);
$row_ended = $count_ended_result->fetch_assoc();
$ended_contracts = $row_ended['ended_contracts'];


// Отримання останнього запису з таблиці фірм
$last_entry_sql = "SELECT * FROM firms ORDER BY idFirm DESC LIMIT 1";
$last_entry_result = $connection->query($last_entry_sql);
$last_entry_row = $last_entry_result->fetch_assoc();

// Отримання фірми з найбільшою кількістю контрактів
$best_firm_sql = "SELECT firms.idFirm, firms.companyName, COUNT(contracts.idContract) AS contractCount
        FROM firms
        LEFT JOIN contracts ON firms.idFirm = contracts.idFirm
        GROUP BY firms.idFirm, firms.companyName
        ORDER BY contractCount DESC
        LIMIT 1";
$best_firm_result = $connection->query($best_firm_sql);
$best_firm_row = $best_firm_result->fetch_assoc();

echo "<p>Загальна кількість фірм: " . $total_records . "</p>";
echo "<p>Загальна кількість контрактів: " . $total_contracts . "</p>";
echo "<p>Кількість контрактів, які почались в поточному місяці: " . $started_contracts . "</p>";
echo "<p>Кількість контрактів, які завершились в поточному місяці: " . $ended_contracts . "</p>";
echo "<p>Останній запис в таблиці фірм: <a href='last_entry.php?idFirm=" . $last_entry_row['idFirm'] . "'>" . $last_entry_row['companyName'] . "</a></p>";
echo "<p>Фірма з найбільшою кількістю контрактів: <a href='best_firm.php?idFirm=" . $best_firm_row['idFirm'] . "'>" . $best_firm_row['companyName'] . "</a>, Кількість контрактів: " . $best_firm_row['contractCount'] . "</p>";



echo "<h3><a href='main.php'>Повернутись на головну сторінку сайту</a></h3>";

$connection->close();
?>
