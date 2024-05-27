const submitButton = document.querySelector('button[type="button"]')

submitButton.addEventListener('click', (event) => {
  const form = document.querySelector('.edit-form');
  
  const notationId = form.querySelector('#notationId').value;
  const companyName = form.querySelector('#companyName').value;
  const numberWorkers = form.querySelector('#numberWorkers').value;
  const contractName = form.querySelector('#contractName').value;
  const startDate = form.querySelector('#startDate').value;
  const endDate = form.querySelector('#endDate').value;

  const updatedData = {
    companyName,
    numberWorkers,
    contractName,
    startDate,
    endDate
  };

  fetch(`/edit/${notationId}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(updatedData)
  })
  .then(res => res.json())
  .then(res => {
    if(res.status === 0) {
      window.location.href = `/notes/${notationId}`
    } else {
      console.log(res.error)
    }
  })
})
