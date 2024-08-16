import {useState} from 'react';
import './App.css';

function App() {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handle가입하기 = () => {
    //fetch와 formData 활용해서 회원가입 작성
    const formData = new FormData();
    formData.append("username", username);
    formData.append("email", email);
    formData.append("password", password);

    const user = {
      username : username,
      email : email,
      password : password
    }

    fetch('http://localhost:9011/api/register', {
      method: "POST", 
      headers: {
        'Content-Type' : 'application/json',
      },
      body: JSON.stringify(user)
      /*
      body: formData

      와

      body: {
        "username" : username
        "email" : email
        "password" : password
      }

      는 동일

      body: formData => multipart 이미지 등을 보낸다
      body: JSON.stringify() => json의 형식으로 보낸다
      */
    })
    .then(response => response.text())
    //데이터가 무사히 저장되었다고 사용자한테 알려줌
    .then(data => {
      alert('회원가입이 완료되었습니다.');
    })
    //데이터 저장 실패했다고 사용자한테 알려줌
    .catch(error => {
      alert('회원가입에 실패하였습니다.');
    })
  }

  return (
    <div className="App">
      <h2>회원가입</h2>

      <label>닉네임 : </label>
      <input type='text'
             placeholder='닉네임 작성하기'
             value={username}
             onChange={(e) => setUsername(e.target.value)}/>

      <label>이메일 : </label>
      <input type='text'
             placeholder='이메일 작성하기'
             value={email}
             onChange={(e) => setEmail(e.target.value)}/>

      <label>비밀번호 : </label>
      <input type='text'
             placeholder='비밀번호 작성하기'
             value={password}
             onChange={(e) => setPassword(e.target.value)}/>

      <button onClick={handle가입하기}>가입하기</button>
    </div>
  );
}

export default App;
