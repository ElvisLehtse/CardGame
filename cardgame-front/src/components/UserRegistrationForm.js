import React from 'react'
import { useState } from 'react';

function UserRegistrationForm(props) {

  const [name, setName] = useState("");

  const playerRegistration = (event) => {
    event.preventDefault();
    fetch("http://localhost:8080/playerRegistration?name=" + name + "&score=" + props.score, {method: "POST"});
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