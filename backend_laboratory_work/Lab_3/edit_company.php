<?php
require_once("mydb.php");

if ($_SERVER["REQUEST_METHOD"] == "GET" && isset($_GET["id"])) {
    $companyId = $_GET["id"];
    
    $sql = "SELECT * FROM firms WHERE idFirm = '$companyId'";
    $result = $connection->query($sql);
    
    if ($result->num_rows == 1) {
        $row = $result->fetch_assoc();
?>
<!DOCTYPE html>
<html>
<head>
    <title>Редагувати компанію</title>
</head>
<body>
    <h2>Редагувати компанію</h2>
    <form action="edit_company_process.php" method="post">
        <input type="hidden" name="companyId" value="<?php echo $row['idFirm']; ?>">
        Назва компанії: <input type="text" name="companyName" value="<?php echo htmlspecialchars($row['companyName']); ?>"><br>
        Кількість працівників: <input type="number" name="numberWorkers" value="<?php echo $row['numberWorkers']; ?>"><br>
        <input type="submit" value="Зберегти зміни">
    </form>
</body>
</html>
<?php
    } else {
        echo "Компанію не знайдено або вказано неправильний ідентифікатор.";
    }
    $connection->close();
} 
echo "<h3><a href='main.php'>Повернутись на головну сторінку сайту</a></h3>";
?>
