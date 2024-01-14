// Postavite URL zahteva prema vašem gateway-u
const gatewayUrl = 'http://localhost:8084/korisnickiservis/api/admin/login';

// Postavite podatke koje želite poslati (ako je potrebno)
const requestData = {
    username: 'yourUsername',
    password: 'yourPassword'
};

// Postavite opcije zahteva, uključujući metodu i opcije CORS
const requestOptions = {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
        'Origin': 'http://localhost:8000' // Postavite svoj origin
    },
    mode: 'cors',
    body: JSON.stringify(requestData)
};

// Koristite fetch funkciju da baci zahtev
fetch(gatewayUrl, requestOptions)
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
    })
    .then(data => {
        // Obradite odgovor od servera
        console.log('Odgovor:', data);
    })
    .catch(error => {
        // Uhvatite eventualne greške
        console.error('Greška:', error);
    });