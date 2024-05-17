let petName = "";
let hunger = 100;
let play = 100;
let health = 100;
let thirst = 100;
let timerInterval;
let count = 0;
const hungerDisplay = document.getElementById('hunger');
const thirstDisplay = document.getElementById('thirst');
const playDisplay = document.getElementById('play');
const healthDisplay = document.getElementById('health');
const petNameDisplay = document.getElementById('petNameDisplay');
const nameInput = document.getElementById('nameInput');
const gameContainer = document.getElementById('gameContainer');
const restartButton = document.getElementById('restartButton');
const deathText = document.getElementById('deathText');
const petNameDeathCounter = document.getElementById('petNameDeathCounter');
const counter = document.getElementById('counter');

function startGame() {
    petName = document.getElementById('petName').value.trim();
    if (petName === "") {
        alert("Please enter a name for your pet.");
        return;
    }

    nameInput.style.display = 'none';
    gameContainer.style.display = 'block';
    petNameDisplay.textContent = petName;
    petNameDeathCounter.innerHTML = "Times " + petName + " has died:";
    startTimer();
}

function updateStats() {
    hunger = Math.max(0, hunger - 2);
    play = Math.max(0, play - 2);
    thirst = Math.max(0, thirst - 2);
    if(hunger<50 || play< 50 || thirst<50){
        health = Math.max(0, health - 2);
    }
    if((hunger>90 || play> 90)&& health<=98){
        health += 2;
    }
    

    hungerDisplay.textContent = hunger;
    thirstDisplay.textContent = thirst;
    playDisplay.textContent = play;
    healthDisplay.textContent = health;

    if (health <= 0) {
        clearInterval(timerInterval);
        showDeathScreen();
    }
}

function startTimer() {
    timerInterval = setInterval(updateStats, 1000);
}

function feedPet() {
    hunger = Math.min(100, hunger + 20);
}

function playWithPet() {
    play = Math.min(100, play + 20);
}

function waterPet() {
    thirst = Math.min(100, thirst + 20);
}

function showDeathScreen() {
    document.getElementById('petStatus').style.display = 'none';
    document.querySelectorAll('button').forEach(button => button.style.display = 'none');
    restartButton.style.display = 'block';
    deathText.style.display = 'block';
    count++;
    counter.innerHTML = count;
}

function restart() {
    hunger = 100;
    play = 100;
    health = 100;
    thirst = 100;
    updateStats();
    document.getElementById('petStatus').style.display = 'block';
    document.querySelectorAll('button').forEach(button => button.style.display = 'inline-block');
    restartButton.style.display = 'none';
    deathText.style.display = 'none';
    petNameDeathCounter.style.display = 'block';
    counter.style.display = 'block';
    startTimer();
}