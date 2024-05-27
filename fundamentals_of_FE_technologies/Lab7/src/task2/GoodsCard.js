import React from 'react';
import styles from "./GoodsCard.module.css";


const GoodsItem = ({ image, title, price }) => (
  <>
    <div className={styles.imgContainer}>
      <img className={styles.img} src={image} alt="" />
    </div>
    <div className={styles.name}>
      <h2>{title}</h2>
      <h2>Ціна {price} грн/кг</h2>
    </div>
  </>
);

const GoodsCard = ({ children }) => (
  <div className={styles.card}>{children}</div>
);

export { GoodsItem, GoodsCard };