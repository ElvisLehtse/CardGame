import React from 'react'

function UserChoiceForm(props) {
  return (
    <form onSubmit={props.submit}>
      <button onClick={() => props.choose("lower")} type="submit" className="btn btn-primary mt-3" disabled={props.status}>Lower</button>
      <button onClick={() => props.choose("equal")}  type="submit" className="btn btn-primary mt-3" disabled={props.status}>Equal</button>
      <button onClick={() => props.choose("higher")}  type="submit" className="btn btn-primary mt-3" disabled={props.status}>Higher</button>
    </form>
  )
}

export default UserChoiceForm;