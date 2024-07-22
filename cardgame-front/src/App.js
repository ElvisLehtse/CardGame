import { useState } from 'react';
import { useGranularEffect } from 'granular-hooks';
import './App.css';

function App() {
  const [userChoice, setUserChoice] = useState("");
  const [cardAndResult, setCardAndResult] = useState("");
  const [startGameButtonStatus, setStartGameButtonStatus] = useState(false);
  const [userChoiceButtonStatus, setUserChoiceButtonStatus] = useState(true);
  const [lives, setLives] = useState(3);
  const [responseMsg, setResponseMsg] = useState(null);
  const [count, setCount] = useState(10);
  const [intervalId, setIntervalId] = useState(null);
  const [score, setScore] = useState(0);

  function startGame() {
    setScore(0);
    setLives(3);
    setCount(10);
    setResponseMsg(null);
    setStartGameButtonStatus(true);
    setUserChoiceButtonStatus(false);
    fetch("http://localhost:8080/startGame")
    .then(res => res.json())
    .then(body => setCardAndResult(body));
  };

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
    setIntervalId(interval);
    return () => clearInterval(interval);
  };

  const stopCountdown = () => {
    clearInterval(intervalId);
    setIntervalId(null);
  }

  function sendUserChoice(event) {
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

  useGranularEffect(() => {
    if (lives === 0) {
      setStartGameButtonStatus(false);
      setUserChoiceButtonStatus(true);
      stopCountdown();
    }
  }, [lives], [stopCountdown]);

  return (
    <div className="App">
      <div className="container mt">
        <div>
          <button onClick={() => {startGame(); startCountdown()}} type="submit" className="btn btn-primary mt-3" disabled={startGameButtonStatus}>Start the game</button><br/>
          Time remaining: {count >= 0 ? count : "Time's up!"}<br/>
          Your score: {score}
        </div><br/>
        <span>Card name: | Card rank:</span><br/>
        {cardAndResult.length !== 0 &&
          <>{cardAndResult.card.name} | {cardAndResult.card.rank}</>
        }
        <br/><span>What will the next card be?</span><br/>
        <form onSubmit={sendUserChoice}>
          <button onClick={() => setUserChoice("lower")} type="submit" className="btn btn-primary mt-3" disabled={userChoiceButtonStatus}>Lower</button>
          <button onClick={() => setUserChoice("equal")}  type="submit" className="btn btn-primary mt-3" disabled={userChoiceButtonStatus}>Equal</button>
          <button onClick={() => setUserChoice("higher")}  type="submit" className="btn btn-primary mt-3" disabled={userChoiceButtonStatus}>Higher</button>
        </form><br/>
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
