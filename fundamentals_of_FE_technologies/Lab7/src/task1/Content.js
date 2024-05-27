import React from 'react';
import Image from './Image';
import styles from './Content.module.css';

const ImageList = ({ images }) => (
  <div className={styles.imageLink}>
    {images.map((img) => (
      <Image key={img.id} style={{ width: img.width, margin: '10px' }} />
    ))}
  </div>
);

class Content extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      university: {},
      hobby: {},
      images: [{ id: Date.now(), width: 550 }],
    };
  }

  createImage = () => {
    const newImage = { id: Date.now(), width: 550 };
    this.setState((state) => ({
      images: [...state.images, newImage],
    }));
  };

  updateImageSize = (factor) => {
    this.setState((state) => {
      const lastImageIndex = state.images.length - 1;
      const lastImage = state.images[lastImageIndex];
      const updatedImage = { ...lastImage, width: lastImage.width * factor };

      return {
        images: [...state.images.slice(0, lastImageIndex), updatedImage],
      };
    });
  };

  enlargeLastImage = () => {
    this.updateImageSize(1.1);
  };

  shrinkLastImage = () => {
    this.updateImageSize(0.9);
  };

  removeLastImage = () => {
    this.setState((state) => ({
      images: state.images.slice(0, -1),
    }));
  };

  generateRandomColor = () => {
    const randomColor = Math.floor(Math.random() * 16777215).toString(16).padStart(6, '0');
    return `#${randomColor}`;
  };
  getContrastingTextColor = hexColor => {
    
      const cleanedHex = hexColor.replace('#', '');
      const [red, green, blue] = cleanedHex.match(/.{2}/g).map(channel => parseInt(channel, 16));
      const yiq = (red * 299 + green * 587 + blue * 114) / 1000;
      return yiq >= 128 ? 'black' : 'white';
  }

  changeColor = element => {
     const newBackgroundColor = this.generateRandomColor()
     const newContrastColor = this.getContrastingTextColor(newBackgroundColor)
    this.setState({
      [element]: { backgroundColor: newBackgroundColor, color: newContrastColor }
    })
  }

  render () {
    return (
      <div>      
        <p className={styles.info} id="university" 
        style={{
          backgroundColor: this.state.university.backgroundColor,
          color: this.state.university.color
        }}
        onClick={() => this.changeColor('university')}
        > КПІ імені Ігоря  Сікорського</p>
        
        <p className={styles.info} id="hobby" style={{
          backgroundColor: this.state.hobby.backgroundColor,
          color: this.state.hobby.color
        }}
        onClick={() => this.changeColor('hobby')}
         >Хоббі:</p>
      <ul>
        <li>Риболовля</li>
        <li>Футбол</li>
        <li>Настільний теніс</li>
        <li>Мафія</li>
      </ul>
      <p className={styles.info}>Улюблені фільми і серіали:</p>
      <ol>
        <li>Паперовий будинок, 2017 р.</li>
        <li>Перевізник 2, 2005 р.</li>
        <li>Ходячі мертвеці, 2010 р.</li>
        <li>Форсаж 10, 2023 р.</li>
      </ol>
      <p className={styles.info}>
        Ко́рець — місто в Україні, центр Корецької громади Рівненського району Рівненської області. 
        До 2020 року центр Корецького району розташоване на річці Корчик, за 63 км від обласного центру міста Рівного;
        населення — 7 428 осіб; перша згадка — 1150 рік (як Корчеськ; Київський літопис).
        Місто Корець є населеним пунктом, занесеним до Списку історичних міст України, 
        затвердженого постановою Кабінету Міністрів України від 26.07.2001 № 878. 
      </p>
      <ImageList images={this.state.images} />
        <div>
          <button className={styles.button} onClick={this.createImage}>
            Додати
          </button>
          <button className={styles.button} onClick={this.enlargeLastImage}>
            Збільшити
          </button>
          <button className={styles.button} onClick={this.shrinkLastImage}>
            Зменшити
          </button>
          <button className={styles.button} onClick={this.removeLastImage}>
            Видалити
          </button>
        </div>
      </div>
    );
  }
}

export default Content