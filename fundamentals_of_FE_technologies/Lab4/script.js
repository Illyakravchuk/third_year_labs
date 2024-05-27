const university = document.getElementById('university');
const hobby = document.querySelector('#hobby');
const image = document.querySelector('#image');

function generateRandomColor () {
    const randomColor = Math.floor(Math.random() * 16777215).toString(16).padStart(6, '0');
    return `#${randomColor}`;
  };

function getContrastingTextColor (hexColor) {
  const cleanedHex = hexColor.replace('#', '');
  const [red, green, blue] = cleanedHex.match(/.{2}/g).map(channel => parseInt(channel, 16));
  const yiq = (red * 299 + green * 587 + blue * 114) / 1000;
  return yiq >= 128 ? 'black' : 'white';
};

function handleColorChange (element) {
  const newBackgroundColor = generateRandomColor();
  element.style.backgroundColor = newBackgroundColor;
  element.style.color = getContrastingTextColor(newBackgroundColor);
};

university.addEventListener('click', () => handleColorChange(university));
hobby.addEventListener('click', () => handleColorChange(hobby));


function createImage() {
    const img = new Image();
    img.src = 'https://i.ibb.co/d5jL3y5/image.jpg';
    img.alt = 'clone';
    image.appendChild(img);
  }
  
  function enlargeLastImage() {
    const images = document.querySelectorAll('img');
    const lastImage = images[images.length - 1];
    
    if (lastImage) {
      const currentWidth = parseFloat(getComputedStyle(lastImage).width);
      lastImage.style.width = currentWidth * 1.05 + 'px';
    }
  }
  
  function shrinkLastImage() {
    const images = document.querySelectorAll('img');
    const lastImage = images[images.length - 1];
    
    if (lastImage) {
      const currentWidth = parseFloat(getComputedStyle(lastImage).width);
      lastImage.style.width = currentWidth * 0.95 + 'px';
    }
  }
  
  function removeLastImage() {
    const images = document.querySelectorAll('img');
    const lastImage = images[images.length - 1];
    
    if (lastImage) {
      lastImage.remove();
    }
  }
 