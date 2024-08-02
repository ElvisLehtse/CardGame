import { useState } from 'react';

function Scoreboard() {

  const [filter, setFilert] = useState("score");
  const [results, setResults] = useState([]);
  const [player, setPlayer] = useState("");
  const [playerResults, setPlayerResults] = useState([]);

  //Returns the result of players filtered either by score or gametime
  const getResultList = (e) => {
    e.preventDefault();
    setPlayerResults([]);
    fetch("http://localhost:8080/getResultList?filter=" + filter)
    .then(res => res.json())
    .then(body => setResults(body));
  };

  //Returns all the results of a single player. The order or results is determined by score or gametime
  const getPlayerResults = (e) => {
    e.preventDefault();
    setResults([]);
    fetch("http://localhost:8080/getPlayerResults?player=" + player + "&filter=" + filter)
    .then(res => res.json())
    .then(body => setPlayerResults(body));
  };

  return (
    <>
      <form onSubmit={getResultList}>
        <h6>Scoreboard</h6>
        <div className="form-group">
          <div className="col-sm-5">
            <label htmlFor="filter" className="form-label">Sort by</label>
            <select value={filter} onChange={(e) => {setFilert(e.target.value)}} className="form-select" id="filter">
              <option value="score">Score</option>
              <option value="gametime">Gametime</option>
            </select>
          </div>
          <button type="submit" className="btn btn-primary mt-3">Submit</button>
        </div>
      </form>
      <br/>
      <form onSubmit={getPlayerResults}>
        {results.map(result => <div key={result.id}>{
          <button value={player} onClick={(e) => {setPlayer(result.player.id)}} type="submit" className="btn btn-secondary mt-1">{result.player.name} | Score: {result.score} | Gametime: {result.playTime} sec</button>
        }<br/></div>)}<br/>
      </form>
      {playerResults.length > 0 &&
        <div>
          <div>All results for {playerResults[0].player.name}:</div>
          {playerResults.map(result => <div key={result.id}>Score: {result.score} | Gametime: {result.playTime} sec</div>)}
        </div>
      }
    </>
  )
}

export default Scoreboard