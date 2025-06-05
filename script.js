// const API_URL = 'http://localhost:8080'; // URL da API local
const API_URL = 'https://downmidias.onrender.com'; // URL da API hospedada

const qualityOption = document.getElementById('quality');  //select que receberá os options
const url = document.getElementById('txturl');
let arrQualityMp3 = [128, 192, 256, 320, 0]
let arrQualityMp4 = [360, 480, 720, 1080, 0]


const updateOption = (valueId) => {
    // Limpa opções anteriores
    qualityOption.innerHTML = '';
    
    //se a função for chamada pelo radio mp3 ou mp4
    if (valueId == 'mp3'){
        arrQualityMp3.forEach(element => {
            let option = document.createElement('option');
            option.setAttribute('id',`${element}kbps`); //atribuição de id
            option.setAttribute('value',`${element}kbps`); //atribuição de valor
            option.textContent = `${element} kbps`; //atribuição de texto no option
            qualityOption.appendChild(option); //adicionando o option no select.
        });
        //se nao for mp3 então é mp4
    }else{
        arrQualityMp4.forEach(element => {
            let opt = document.createElement('option');
            opt.setAttribute('id',`${element}p`); //atribuição de id
            opt.setAttribute('value',`${element}p`); //atribuição de valor
            opt.textContent = `${element}p`; //atribuição de texto no option
            qualityOption.appendChild(opt); //adicionando o option no select.
        })
    }

}

//função para fazer o download
const downloadMidia = () => {
    let format = document.querySelectorAll('input[name=mpx]:checked'); // mp3 ou mp4 checked

    //teste de valores para requisição
    
    console.log(url.value); //selecionar valor da url
    console.log(qualityOption.value); //selecionar valor da qualidade
    console.log(format[0].value); //selecionar valor do formato

    try{
        //requisição para download
        fetch(`${API_URL}/converter?url=${url.value}&format=${format[0].value}&quality=${qualityOption.value}`,{
            method: 'POST' //metodo da requisição
        })
        .then(res => {
            if(!res.ok) throw new Error('Erro no Download');
            return res.json();
        })
    }catch(e) {
        console.error(`Erro na requisição: ${e}`)
    }
}

const loadingAPI = () => {
    fetch(`${API_URL}/`)
    //requisição de teste, irá chamar a API assim que entrar no site
    //evitando alguns segundos de espera
}

//quando iniciar o html, carregará as opções de qualidade e chamará a requisição de teste
updateOption();
loadingAPI();
