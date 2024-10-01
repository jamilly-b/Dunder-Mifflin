document.addEventListener('DOMContentLoaded', () => {
    const isAuthenticated = sessionStorage.getItem('authToken');

    if (isAuthenticated) {
        const botao = document.querySelector('.botao-adm');
        if(botao){
            botao.style.display = "block";
        }    

        fetch('header.html')
            .then(response => response.text())
            .then(headerHtml => {
                document.body.insertAdjacentHTML('afterbegin', headerHtml);
            })
            .catch(error => console.error('Erro ao carregar header:', error));
    }
});