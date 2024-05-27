const hiddenInput = document.querySelector('input[type="hidden"][name="id"]')
const confirmButton = document.querySelector('input[type="button"][value="Так"]');

const notationId = hiddenInput.value

confirmButton.addEventListener('click', (event) => {
  fetch(`/delete/${notationId}`, {
    method: 'DELETE',
  })
  .then(res => res.json())
  .then(res => {
    if(res.status === 0) {
      window.location.href = '/notes'
    } else {
      console.log(res.error)
    }
  })
})
