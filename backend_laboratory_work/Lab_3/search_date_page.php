<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Пошук за діапазоном дат</title>
</head>
<body>
    <h2>Пошук співпадінь за діапазоном дат</h2>
    <form action="search_date.php" method="POST">
        <label for="start_date">Початкова дата (рік-місяць-день):</label>
        <input type="text" id="start_date" name="start_date" pattern="\d{4}-\d{2}-\d{2}" required>
        <br>
        <label for="end_date">Кінцева дата (рік-місяць-день):</label>
        <input type="text" id="end_date" name="end_date" pattern="\d{4}-\d{2}-\d{2}" required>
        <br>
        <button type="submit" name="search">Пошук</button>
    </form>
</body>
</html>
