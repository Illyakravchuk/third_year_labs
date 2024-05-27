<?php
require_once("mydb.php");

// Перевірка наявності таблиці контрактів
$checkTable = "SHOW TABLES LIKE 'contracts'";
$tableExists = $connection->query($checkTable);

if ($tableExists->num_rows == 0) {
    // Створення таблиці контрактів, якщо її не існує
    $createContractsTable = "CREATE TABLE contracts (
        idContract INT(100) AUTO_INCREMENT PRIMARY KEY,
        idFirm INT,
        contractName VARCHAR(100),
        startDate DATE,
        endDate DATE,
        FOREIGN KEY (idFirm) REFERENCES Firms(idFirm)
    )";

    if ($connection->query($createContractsTable)) {
        echo "Таблиця контрактів успішно створена.";
    } else {
        echo "Помилка при створенні таблиці контрактів " . $connection->error;
    } 
}

// Перевірка наявності контрактів в таблиці
$checkContracts = "SELECT COUNT(*) as count FROM contracts";
$result = $connection->query($checkContracts);
$row = $result->fetch_assoc();
$countContracts = $row['count'];

if ($countContracts == 0) {
    $insertData = "INSERT INTO contracts (idFirm, contractName, startDate, endDate)
    VALUES
        (1, 'Доставка товарів', '2024-02-01', '2024-06-30'),
        (2, 'Послуги з обслуговування', '2024-03-15', '2024-09-15'),
        (4, 'Консультаційні послуги', '2024-04-01', '2024-08-31'),
        (3, 'Послуги ремонту', '2024-05-10', '2024-10-10'),
        (4, 'Розробка ПЗ', '2024-06-20', '2024-11-20'),
        (5, 'Послуги з доставки меблів', '2024-07-01', '2024-12-31'),
        (2, 'Аудиторські послуги', '2024-08-15', '2025-01-15'),
        (5, 'Технічна підтримка', '2024-09-01', '2025-02-28'),
        (2, 'Рекламні послуги', '2024-10-10', '2025-03-31'),
        (4, 'Навчання персоналу', '2024-11-15', '2025-04-30')";

    if ($connection->query($insertData)) {
        echo "Контракти додані.";   
    } else {
        echo "Помилка при додаванні контрактів " . $connection->error;
    }
} else {
    echo "Таблиця контрактів вже існує.";
} 

$connection->close();
?>
