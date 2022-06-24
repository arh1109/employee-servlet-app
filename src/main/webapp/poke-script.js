// grab all the elemnts from the page to work with
const pokeId = document.getElementById('poke-id');
const respId = document.getElementById('resp-id');
const pokeName = document.getElementById('resp-name');
const pokeImg = document.getElementById('resp-sprite');
const button = document.querySelector('button');

// create a function to fetch a poke object
function fetchPokemon() {

    // capture the input from the document
    let idNum = pokeId.value;    // capturing the value of the input element
    
    // send a fetch call to the pokeAPI and concatenate the value of the pokemon we watn
    fetch(`https://pokeapi.co/api/v2/pokemon/${idNum}`)
        .then(res => res.json())
        .then(renderPokemon);

    // chain functions to our primoise -> parse the JSON into an object, then call a function on the object


}

// create a function to RENDER the resoponse
function renderPokemon(data) {
    // set all the elements that we captured above, EQUAL TO the
    // properties that we pull from the data
    pokeName.innerHTML = `Name: ${data.name}`;
    respId.innerHTML = `Id: ${data.id}`;

    pokeImg.setAttribute('src', data.sprites.front_default);
    pokeImg.setAttribute('height', 300);
}

// add the EventListener to the button
button.addEventListener('click', fetchPokemon);
