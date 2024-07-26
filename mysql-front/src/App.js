import './App.css';
import {useState, useEffect} from 'react';
import axios from 'axios';
import UserTable from './component/UserTable';
import UserForm from './component/UserForm';
import EditUserForm from './component/EditUserForm';

//select, insert component 추가 작성
function App() {
  const [users, setUsers] = useState([]); //유저 목록이 담긴 빈 배열 생성

  //수정한 유저 정보를 잠시 담고 있을 변수 생성
  const [userToEdit, setUserToEdit] = useState(null);

  //useEffect는 버튼이나 특정 값을 클릭하지 않아도 자동실행되나, 
  //딱 1번만 실행되느냐, 주기적으로 실행되느냐의 차이
  //App.js가 실행되면 적응할 효과, 만약 특정 변수명이 없다면 기능을 최초 1회만 실행하고 실행되지 않음
  //useEffect(() => {기능}, [특정변수명]);
  //특정 변수명이 존재한다면 특정변수명에 변화가 있을 때마다 기능이 실행
  //useEffect(() => {기능}, [특정변수명]);

  /***** useEffect 최초 1회만 실행 *****/
  /*
  useEffect(() => {
    모든유저보기();//홈페이지에 들어오면 최초 1회로 유저들이 보이고,
  }, []);
   //[] : [] 배열이 비어있기 때문에 홈페이지가 보일 때 딱 1번만 실행
   //     따라서 유저 추가를 하고 새로고침 버튼을 눌러야 함
  */
  
  /***** useEffect users를 넣어서 유저목록에 변화가 생기면 모두 불러오기 기능을 다시 실행 *****/
  useEffect(() => {
    모든유저보기();//홈페이지에 들어오면 최초 1회로 유저들이 보이고,
  }, [users]);
   //[users] : []안에 users가 들어있기 때문에 유저목록에 유저가 추가되거나 삭제될 경우 유저목록을 다시 불러옴

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
  //★ 1. axios로 성공과 실패에 대한 결과를 처리하는 버전
  const 모든유저보기 = () => {
    axios.get("/users")
    .then(응답 => {
      setUsers(응답.data);
    })
    .catch(err => {
      alert("가져오지 못했습니다.")
    })
  }

  //★ 2. axios로 성공에 대한 결과만 보여주는 버전 (async와 await 사용)
  //async : 기능을 실행해보자
  //await : 일단 기다려, 내가 값을 가져와볼게
  const 모든유저보기 = async () => {
    const 응답 = await axios.get("/users"); //controller에 있는 users 주소에 방문해서 데이터를 가져올게
    //가져오기를 성공하면
    setUsers(응답.data); //가져오는데 성공하면 가져온 데이터로 유저목록을 만들어주는 것
  }
  
  1번과 2번 방법은 모두 동일한 기능을 수행
*/

  /***** 모든 유저 보기 *****/
  //async와 await 버전을 사용, res = 응답
  const 모든유저보기 = async () => {
    const res = await axios.get("/users");
    setUsers(res.data);
  };

  /***** 유저 추가 기능 *****/
  //async와 await를 사용해서 유저 추가하기
  //addUser에서 가져온 user 한 명을 넣어주기
  const addUser = async (user) => {
    const res = await axios.post('/users', user);
      //controller에서 PostMapping으로 전달하는 유저 정보
    setUsers([...users], res.data);
           //[...users] : 기존에 작성한 유저목록
      //기존에 작성한 유저목록에 유저 데이터 하나를 추가
  }

  /***** 유저 삭제 기능 *****/
  const deleteUser = async (id) => {
    await axios.delete(`/users?id=${id}`);
                    /* `` */
    /*
    "" : 글자 취급
    '' : 글자 취급
         예시) http://localhost:3000/users?id=${id}

    `` : 글자 안에 특정 값을 변수명으로 취급해야할 때 사용
         ★ 따라서 위의 코드에서 "" 나 '' 를 사용하면 안 됨
         `` 안에서 변수명으로 처리해야 하는 값은 ${}로 처리함
         ${융통성있게변경되어야하는변수명}
         예시) http://localhost:3000/users?id=3
    */
    /*
    ★ 자바 컨트롤러에서 @DeleteMapping("/{id}") 로 작성한 경우
    매개변수 = 파라미터에 (@PathVariable int id)가 작성되어 있으면
    리액트 axios에서 id는 ${id}이다.
    나중에 주소값에 id 대신 삭제할 번호가 들어갈 수 있도록 설정

    ★ 자바 컨트롤러에서 @DeleteMapping 에 특정 id 값을 설정하지 않은 경우
    매개변수 = 파라미터에 (@RequestParam("id") int id)가 작성되어 있으면
                                    //("id") : 프론트엔드에서 가져온 id값
    params : {id}
    await axios.delete( `/users`, ( params: {id} ) );
    */
    setUsers(users.filter(user => user.id !== id));
          /* users : 현재 저장되어있는 유저들 리스트 */
                /* filter : 조건 */
    
    /* user.id != id : 유저아이디와 id(삭제하고자 하는 유저 아이디)가 일치하지 않으면
                       setUsers 리스트에 포함시키고 */
                        
    /* d(삭제하고자 하는 유저아이디)와 user.id가 일치하는 아이디는 삭제한 다음
       setUsers(새로운 유저 목록)을 완성시킨다. */   
  }

  /***** 유저 정보 수정 기능 *****/
  const updateUser = async (user) => {
    await axios.put('/users', user); //@PutMapping으로 /users로 주소값이 설정된, 수정하는 주소 연결
    setUsers( users.map( u => (u.id === user.id ? user : u) ) );
      //수정한 유저의 id값이 일치하는지 확인하고,
      //id값이 일치하지 않다면 기존에 있던 유저 정보로 전달
  }

  /***** 유저 정보 수정을 완료하면 유저 목록에 수정된 유저를 전달하는 기능 *****/
  const editUser = (user) => {
    setUserToEdit(user);
  }

  /***** 수정하기 버튼이 있다면 수정 취소하기 확인 *****/
  const cancelEdit = () => {
    setUserToEdit(null);//유저정보 수정 취소할 때 null 빈 칸으로 변경하는 트릭
  }



  return (
    <div className="App">
      <h1>유저 관리하기</h1>
      <UserForm addUser={addUser} />
      <UserTable users={users} deleteUser={deleteUser} editUser={editUser} />
      {userToEdit && (
        <EditUserForm
          userToEdit={userToEdit}
          updateUser={updateUser}
          cancelEdit={cancelEdit}
        />
      )}
    </div>
  );
}

export default App;
