import React, {useState} from 'react';
import LoginContext from './components/LoginContext';
import Signup from './components/SignUp';
import Login from './components/Login';
//import TodoList from './components/TodoList';
import './App.css';

function App() {
  const [signUpView, setSignUpView] = useState(false);
  const [loginMember, setLoginMember] = useState(null);

  return (
    <LoginContext.Provider value={ {loginMember, setLoginMember} }>
      <button onClick={() => {setSignUpView(!signUpView)}}>
        {signUpView ? ('회원 가입 닫기') : ('회원 가입 열기')}
      </button>

      <div className="signup-wrapper">
        {signUpView === true && (<Signup/>)}
      </div>

      <h1>Todo List</h1>
      <Login/>
      <hr/>


    </LoginContext.Provider>

  );
}

export default App;
