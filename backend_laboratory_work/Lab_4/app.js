const express = require('express');
const hbs = require('hbs');
const axios = require('axios');

const app = express();
const port = 3000;

// Налаштування шаблонізатора Handlebars
app.set('view engine', 'hbs');
hbs.registerPartials(__dirname + '/views/partials');

// Опис маршрутів
app.get('/', (req, res) => {
    res.render('home');
});

// Обробка маршруту /weather/:city
app.get('/weather/:city', async (req, res) => {
    try {
        const city = req.params.city;
        const apiKey = '96d53ee3baadceb124dd7185ee6498d4';
        const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}&units=metric`;
        
        const response = await axios.get(apiUrl);
        const weatherData = response.data;

        if (weatherData.weather && weatherData.weather.length > 0) {
            const weatherIcon = weatherData.weather[0].icon;
            weatherData.weatherIconUrl = `http://openweathermap.org/img/wn/${weatherIcon}.png`;
        }

        res.render('weather', { city, weatherData });
    } catch (error) {
        if (error.response && error.response.status === 404) {
            res.status(404).send('City not found');
        } else {
            res.status(500).send('Internal Server Error');
        }
    }
});

// Обробка маршруту /weather/
app.get('/weather/', async (req, res) => {
    try {
        // Отримання IP адреси користувача з запиту
        const dataip = await axios.get('https://httpbin.org/ip'); 
        const ip = dataip.data.origin;

        const apiKeyg = "at_Sn2PZmOV99biVQvqO5aKDBDDv4nGB";
        const geoUrl = `https://geo.ipify.org/api/v1?apiKey=${apiKeyg}&ipAddress=${ip}`;

        // Отримання геоданих за IP-адресою
        const geoResponse = await axios.get(geoUrl);
        const { city, country, lat, lng } = geoResponse.data.location;

        // Отримання погодних даних за містом користувача
        const apiKey = '96d53ee3baadceb124dd7185ee6498d4';
        const weatherUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}&units=metric`;
        
        const weatherResponse = await axios.get(weatherUrl);
        const weatherData = weatherResponse.data;

        // Перевірка наявності погодних даних
        if (weatherData.weather && weatherData.weather.length > 0) {
            const weatherIcon = weatherData.weather[0].icon;
            weatherData.weatherIconUrl = `http://openweathermap.org/img/wn/${weatherIcon}.png`;
        }

        // Відображення сторінки з погодними даними
        res.render('weather', { city, weatherData });
    } catch (error) {
        console.error("Помилка при отриманні даних:", error);
        res.status(500).send("Помилка при отриманні погодних даних");
    }
});

// Старт сервера
app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
});
