import { useEffect, useState } from 'react';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import NaverApi from './component/NaverApi';
import NaverSignup from './component/NaverSignup';
import './App.css';
import Header from './component/layout/Header';
import AuthContext from './component/layout/AuthContext';
import Login from './component/layout/Login';
/*
html 파일이 1개 밖에 없는 React에서는
Router를 이용해서 각 js 파일의 경로를 설정
BrowserRouter  : Router 웹의 전체적인 경로모음
Routes         : 경로들
Route          : 경로
*/

function App() {
  //로그인 정보를 받고 전달
  const [loginMember, setLoginMember] = useState(null);

  //만약에 로그인한 정보가 있다면 localStorage에 저장
  useEffect(() => {
    if (loginMember) {
      localStorage.setItem("loginMember", JSON.stringify(loginMember));
    }
  }, [loginMember]);
   //[loginMember] : 로그인한 멤버가 변경될 때마다 새로 저장
  
  //로그인이 저장된 정보를 전달
  useEffect(() => {
    const savedMember = localStorage.getItem("loginMember");
    //만약에 loginMember 변수에 저장된 회원정보가 존재한다면 setLoginMember에 넣어주겠다.
    if (savedMember) {
      setLoginMember(JSON.parse(savedMember));//데이터 손실이 있을 수 있으니 JSON 형식으로 변환(=parse)
    }
  })

  return (
    <AuthContext.Provider value={{loginMember, setLoginMember}}>
    <Router>
      <Header/>
      <Routes>
        <Route path="/login" element={<Login/>} />
        <Route path="/api/naver" element={<NaverApi/>} />
        <Route path="/signup/naver" element={<NaverSignup/>} />
      </Routes>
    </Router>
    </AuthContext.Provider>
  );
}

export default App;
