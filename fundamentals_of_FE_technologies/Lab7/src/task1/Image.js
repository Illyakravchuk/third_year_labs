import React from "react";
import styles from "./Image.module.css";

function Image({ style }) {
  return (
    <a href="https://koretska-gromada.gov.ua/">
      <img className={styles.image} style={style} src="https://i.ibb.co/d5jL3y5/image.jpg" alt="Korets" />
    </a>
  );
}

export default Image;





