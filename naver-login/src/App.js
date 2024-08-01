import {Routes, Route} from 'react-router-dom';
import Login from './Login';
import UserInfo from './UserInfo';
import './App.css';
/*
html 파일이 1개 밖에 없는 React에서는
Router를 이용해서 각 js 파일의 경로를 설정
BrowserRouter  : Router 웹의 전체적인 경로모음
Routes         : 경로들
Route          : 경로
*/

function App() {
  return (
      <Routes>
        <Route path="/" element={<Login/>} />
        <Route path="/userinfo" element={<UserInfo/>} />
      </Routes>
  );
}

export default App;
