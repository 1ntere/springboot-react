import './App.css';
import {useState, useEffect} from 'react';
import axios from 'axios';
import UserTable from './component/UserTable';
import UserForm from './component/UserForm';

//select, insert component 추가 작성
function App() {
  const [users, setUsers] = useState([]); //유저 목록이 담긴 빈 배열 생성
  //useEffect는 버튼이나 특정 값을 클릭하지 않아도 자동실행되나, 
  //딱 1번만 실행되느냐, 주기적으로 실행되느냐의 차이
  //App.js가 실행되면 적응할 효과, 만약 특정 변수명이 없다면 기능을 최초 1회만 실행하고 실행되지 않음
  //useEffect(() => {기능}, [특정변수명]);
  //특정 변수명이 존재한다면 특정변수명에 변화가 있을 때마다 기능이 실행
  //useEffect(() => {기능}, [특정변수명]);

  useEffect(() => {
    모든유저보기();//홈페이지에 들어오면 최초 1회로 유저들이 보이고,
  }, []);
   //[] : [] 배열이 비어있기 때문에 홈페이지가 보일 때 딱 1번만 실행

  /*
  const 모든유저보기 = () => {
    //axios를 이용해서 모든 유저를 보겠다.
    axios.get("/users")//controller의 GetMapping에서 /users 라는 주소를 바라보기 때문에 users를 적어준 것
    /***** 응답을 무사히 가져왔을 때 ***** /
    .then(응답 => {//Java에서 DB 값에 있는 내용을 가져와서 고객에게 가져온 내용에 대한 응답을 알려주는 것
      setUsers(응답.data);//응답결과데이터로 users를 변경하겠다.
    })
    /***** 응답을 가져오지 못했을 때, 문제가 발생했을 때 ***** /
    .catch(err => {
      alert("가져오지 못했습니다.")
    //alert : 주로 alert보다 console.log로 작성해서 개발자가 에러를 볼 수 있게 함
    })
  }
  */

/*
  //1. axios로 성공과 실패에 대한 결과를 처리하는 버전
  const 모든유저보기 = () => {
    axios.get("/users")
    .then(응답 => {
      setUsers(응답.data);
    })
    .catch(err => {
      alert("가져오지 못했습니다.")
    })
  }

  //2. axios로 성공에 대한 결과만 보여주는 버전 (async와 await 사용)
  //async : 기능을 실행해보자
  //await : 일단 기다려, 내가 값을 가져와볼게
  const 모든유저보기 = async () => {
    const 응답 = await axios.get("/users"); //controller에 있는 users 주소에 방문해서 데이터를 가져올게
    //가져오기를 성공하면
    setUsers(응답.data); //가져오는데 성공하면 가져온 데이터로 유저목록을 만들어주는 것
  }
  
  1번과 2번 방법은 모두 동일한 기능을 수행
*/

  //async와 await 버전을 사용, res = 응답
  const 모든유저보기 = async () => {
    const res = await axios.get("/users");
    setUsers(res.data);
  };

  //async와 await를 사용해서 유저 추가하기
  //addUser에서 가져온 user 한 명을 넣어주기
  const addUser = async (user) => {
    const res = await axios.post('/users', user);
      //controller에서 PostMapping으로 전달하는 유저 정보
    setUsers([...users], res.data);
           //[...users] : 기존에 작성한 유저목록
      //기존에 작성한 유저목록에 유저 데이터 하나를 추가
  }

  return (
    <div className="App">
      <h1>유저 관리하기</h1>
      <UserForm addUser={addUser} />
      <UserTable users={users} />
    </div>
  );
}

export default App;
