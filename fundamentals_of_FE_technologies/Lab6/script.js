const container = document.querySelector('.container-user');
const updateUserButton = document.querySelector('.updateUser');

updateUserButton.addEventListener('click', fetchUserData);

function logError(message, error) {
  console.error(`${message}:`, error);
}

function displayTitle(titleText) {
  let titleDiv = document.querySelector('.title');

  if (!titleDiv) {
    titleDiv = document.createElement('div');
    titleDiv.className = 'title';
    container.prepend(titleDiv);
  }

  titleDiv.textContent = titleText;
}

function updateUserInformation(user) {
  const { picture, phone, location: { coordinates, postcode, country } } = user;

  const userData = {
    picture: picture.large,
    phone,
    coordinates: `${coordinates.latitude} ${coordinates.longitude}`,
    postcode,
    country
  };

  const userDiv = document.createElement('div');
  userDiv.classList.add('userInfo');
  userDiv.innerHTML = `
    <img src="${userData.picture}">
    <p>Phone: ${userData.phone}</p>
    <p>Coordinates: ${userData.coordinates}</p>
    <p>ZIP Code: ${userData.postcode}</p>
    <p>Country: ${userData.country}</p>
  `;

  container.appendChild(userDiv);
}

async function fetchUserData() {
  try {
    const response = await fetch('https://randomuser.me/api/');

    if (!response.ok) {
      throw new Error('The request could not be completed.');
    }

    const data = await response.json();
    updateUserInformation(data.results[0]);

    displayTitle('Success!');
  } catch (error) {
    logError('There was a problem executing the request:', error);
    displayTitle('Fail!');
  }
}
