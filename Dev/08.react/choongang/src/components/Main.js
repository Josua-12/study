import React from 'react'
import Dog from '../Dog';
import Cat from '../Cat';

function Main() {
  const style2 = {
    width: '100%',
    height: '100%',
    backgroundColor: 'skyblue',
    color: '#fff'
  }

  return (
    <div style={style2}>
      메인
      <Dog></Dog>
      <Cat></Cat>
    </div>
  )
}

export default Main;
