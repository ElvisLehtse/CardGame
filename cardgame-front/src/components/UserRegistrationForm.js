import React from 'react'
import { useState } from 'react';

function UserRegistrationForm(props) {

  const [name, setName] = useState("");

  //Sends a user to back-end to be registered to the database
  const playerRegistration = (event) => {
    event.preventDefault();
    props.changeStatus(true);
    //fetch("http://localhost:8080/playerRegistration?name=" + name + "&score=" + props.score, {method: "POST"});
    const nameAndScore = {
      name: name,
      score: props.score
    };
    fetch("http://localhost:8080/playerRegistration", {method: "POST", body: JSON.stringify(nameAndScore), headers: {"Content-Type": "application/json"}});
  };

  return (
    <form onSubmit={playerRegistration}>
      <div className="form-group">
        <label htmlFor="name">Register your score after game:</label>
        <div className="col-sm-5">
          <input value={name} onChange={(e) => setName(e.target.value)} type="text" id="name" placeholder="Account's name" className="form-control" readOnly={props.status}></input>
        </div>
        <button type="submit" className="btn btn-primary mt-3" disabled={props.status}>Submit</button>
      </div>
    </form>
  )
}

export default UserRegistrationForm