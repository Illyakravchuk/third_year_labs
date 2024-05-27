import React from "react";
import { GoodsItem, GoodsCard } from "./GoodsCard";
import styles from "./Footer.module.css";

const Footer  = () => {
  const list = [
    {
      id: 1,
      image: 'https://st2.depositphotos.com/3047599/5831/i/380/depositphotos_58314223-stock-photo-bananas.jpg',
      title: 'Банани',
      price: 64.85,
    },
    {
      id: 2,
      image: 'https://st2.depositphotos.com/4047305/7338/i/380/depositphotos_73387761-stock-photo-sweet-fresh-and-juicy-oranges.jpg',
      title: 'Апельсини',
      price: 54.95,
    },
    {
      id: 3,
      image: 'https://st2.depositphotos.com/3102107/7164/i/380/depositphotos_71645701-stock-photo-apples-red-background.jpg',
      title: 'Яблука',
      price: 19.95,
    },
    {
      id: 4,
      image: 'https://static8.depositphotos.com/1354257/828/i/380/depositphotos_8288313-stock-photo-green-pears.jpg',
      title: 'Груші',
      price: 42.95,
    },
    {
      id: 5,
      image: 'https://st2.depositphotos.com/4754361/8331/i/380/depositphotos_83314948-stock-photo-screensaver-from-heap-of-green.jpg',
      title: 'Кавуни',
      price: 225,
    },
    {
      id: 6,
      image: 'https://st3.depositphotos.com/1352771/31974/i/380/depositphotos_319740154-stock-photo-stack-avocado-fruits-on-a.jpg',
      title: 'Авокадо',
      price: 169.95,
    },
    {
      id: 7,
      image: 'https://st5.depositphotos.com/10614052/68835/i/380/depositphotos_688356932-stock-photo-texture-sweet-mandarins-leaves-closeup.jpg',
      title: 'Мандарини',
      price: 59.95,
    },
    {
      id: 8,
      image: 'https://st.depositphotos.com/1010305/4120/i/380/depositphotos_41207367-stock-photo-fresh-pineapple-for-sale.jpg',
      title: 'Ананаси',
      price: 91.95,
    },
    {
      id: 9,
      image: 'https://static8.depositphotos.com/1005629/876/i/380/depositphotos_8763854-stock-photo-tomato-background.jpg',
      title: 'Помідори',
      price: 82.89,
    },
    {
      id: 10,
      image: 'https://st2.depositphotos.com/1011382/8088/i/380/depositphotos_80881128-stock-photo-cucumber-background-close-up.jpg',
      title: 'Огірки',
      price: 104.95,
    },
    {
      id: 11,
      image: 'https://st.depositphotos.com/1982775/2866/i/380/depositphotos_28664635-stock-photo-broccoli.jpg',
      title: 'Брокколі',
      price: 162.25,
    },

    {
      id: 12,
      image: 'https://st.depositphotos.com/1609126/3302/i/380/depositphotos_33023855-stock-photo-fresh-carrots.jpg',
      title: 'Морква',
      price: 9.89,
    },
  ];
  return (
    <div className={styles.cards}>
      {list.map((element) => (
        <GoodsCard key={element.id}>
          <GoodsItem key={element.id} {...element} />
        </GoodsCard>
      ))}
    </div>
  );
};

export default Footer ;