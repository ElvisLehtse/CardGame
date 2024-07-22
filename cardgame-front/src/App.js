import { useCallback, useEffect, useState } from 'react';
import UserChoiceForms from './components/UserChoiceForms';
import './App.css';

function App() {
  const [userChoice, setUserChoice] = useState("");
  const [cardAndResult, setCardAndResult] = useState("");
  const [startGameButtonStatus, setStartGameButtonStatus] = useState(false);
  const [userChoiceButtonStatus, setUserChoiceButtonStatus] = useState(true);
  const [lives, setLives] = useState(3);
  const [responseMsg, setResponseMsg] = useState(null);
  const [count, setCount] = useState(10);
  const [interval, setCountdownInterval] = useState(null);
  const [score, setScore] = useState(0);

  const startGame = () => {
    initialize();
    startCountdown();
  };

  const initialize = () => {
    setScore(0);
    setLives(3);
    setCount(10);
    setResponseMsg(null);
    setStartGameButtonStatus(true);
    setUserChoiceButtonStatus(false);
    fetch("http://localhost:8080/startGame")
    .then(res => res.json())
    .then(body => setCardAndResult(body));
  }

  const startCountdown = () => {
    const interval = setInterval(() => {
      setCount(prevCount => {
        if (prevCount === 0) {
          clearInterval(interval);
          setStartGameButtonStatus(false);
          setUserChoiceButtonStatus(true);
        }
        return prevCount - 1;
      });
    }, 1000);
    setCountdownInterval(interval);
  };

  const stopCountdown = useCallback(() => {
    clearInterval(interval);
    setCountdownInterval(null);
  }, [interval]);

  const sendUserChoice = (event) => {
    event.preventDefault();
    setCount(10);
    fetch("http://localhost:8080/userGuess?value=" + userChoice)
    .then(res => res.json())
    .then(body => {
      setCardAndResult(body)
      if (body.result === false) {
        setLives(lives - 1);
        setResponseMsg("Incorrect!");
      } else if (body.result === true) {
        setResponseMsg("Correct!");
        setScore(score + 1);
      }
    });
  };

  useEffect(() => {
    if (lives === 0) {
      setStartGameButtonStatus(false);
      setUserChoiceButtonStatus(true);
      stopCountdown();
    }
  }, [lives, stopCountdown]);

  return (
    <div className="App">
      <div className="container mt">
        <div>
          <button onClick={() => startGame()} type="submit" className="btn btn-primary mt-3" disabled={startGameButtonStatus}>Start the game</button><br/>
          Time remaining: {count >= 0 ? count : "Time's up!"}<br/>
          Your score: {score}
        </div><br/>
        <span>Card name: | Card rank:</span><br/>
        {cardAndResult.length !== 0 &&
          <>{cardAndResult.card.name} | {cardAndResult.card.rank}</>
        }
        <br/><span>What will the next card be?</span><br/>
        <UserChoiceForms submit={sendUserChoice} choose={setUserChoice} status={userChoiceButtonStatus}/><br/>
        <span> Lives left: {lives}</span>
        {responseMsg && <h6>{responseMsg}</h6>}
        {cardAndResult.lives === 0 &&
        <h6>Defeat!</h6>
        }
        
      </div>
    </div>
  );
}

export default App;
