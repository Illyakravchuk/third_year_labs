const formFields = ['name', 'phone', 'id-card', 'faculty', 'date-of-birth'];
const  setOfLetters = '[A-ZА-ЩЬЮЯҐЄІЇа-щьюяґєії]';

const fieldRegex  = {
    name: new RegExp(`^${setOfLetters}+ ${setOfLetters}\\.${setOfLetters}\\.$`),
    phone: /^\(\d{3}\)-\d{3}-\d{2}-\d{2}$/,
    'id-card': new RegExp(`^${setOfLetters}{2} №\\d{6}$`),
    faculty: new RegExp(`^${setOfLetters}{4}$`),
    'date-of-birth': /^\d{2}\.\d{2}\.\d{4}$/
};

const correctForm = () => {
  const inputs = {};

  function clearError (element) {

    const tipElement = document.querySelector(`.${element}-hint`);
  
    tipElement.classList.remove('error');
  };
    formFields.forEach(fieldName => {
        clearError(fieldName);

        const inputElement = document.querySelector(`.${fieldName}`);
        const inputValue = inputElement.value;

        const updateInputs = value => {
            inputs[fieldName] = value;
        };

        const handleInvalidInput = () => {
            inputElement.classList.add('error');
            document.querySelector(`.${fieldName}-hint`).classList.add('error');
        };

        updateInputs(inputValue);

        if (!fieldRegex[fieldName].test(inputValue)) {
            handleInvalidInput();
        }
    });

    const isValid = formFields.every(fieldName => fieldRegex[fieldName].test(inputs[fieldName]));

    if (isValid) {
        const { name, phone, 'id-card': idCard, faculty, 'date-of-birth': dob } = inputs;

        const formData = `
            Інформація про вас 
            ------------------------
            ПІБ: ${name}
            Телефон: ${phone}
            ID-card: ${idCard}
            Факультет: ${faculty}
            Дата народження: ${dob}
        `;

        alert(formData);
    }
};
