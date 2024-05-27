import styles from "./Header.module.css";

function Header() {
  return (
    <div>
        <h2 className={styles.headerTitle}>Кравчук Ілля Володимирович</h2>
        <p className={styles.info}>Місце народження: 18 серпня, 2004 року, м.Корець</p>
        <p className={styles.info}>Освіта: Корецький НВК "Школа-I-II ст.-Ліцей"</p>  
    </div>
  );
}

export default Header;