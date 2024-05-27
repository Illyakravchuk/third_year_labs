const getRandomHexColor = () => {
  const randomColor = Math.floor(Math.random() * 16777215).toString(16);
  return `#${randomColor}`;
};;

function changeCellColor(cell, color) {
  if (color === undefined || color === null) {
    color = getRandomHexColor();
  }
  cell.style.backgroundColor = color;
}

const gridTable = document.querySelector('.table');
const createGrid = (newSize) => {
  for (let i = 0; i < newSize; i++) {
    const row = document.createElement('tr');

    for (let k = 0; k < newSize; k++) {
      const cell = document.createElement('td');
      const cellId = newSize * i + k + 1;
      cell.setAttribute('data-cell-id', cellId); 
      cell.textContent = cellId;
      row.appendChild(cell);
    }

    gridTable.appendChild(row);
  }
};

createGrid(6);

const myVariant = 3;
const selectedCell = document.querySelector(`td[data-cell-id="${myVariant}"]`);


selectedCell.addEventListener('mouseover', () => changeCellColor(selectedCell));

let currentColor = 'rgb(255, 240, 101)';
selectedCell.addEventListener('click', () => changeCellColor(selectedCell, currentColor));

selectedCell.addEventListener('dblclick', () => {
  for (let i = 0; i < Math.min(gridTable.rows.length, gridTable.rows[0].cells.length); i++) {
    const cell = gridTable.rows[i].cells[i];
    changeCellColor(cell, currentColor);
  }
});

const picker = document.querySelector('.colorPicker');

picker.addEventListener('input', () => {
  currentColor = picker.value;
});
