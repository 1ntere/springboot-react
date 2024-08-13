import { useState } from 'react';
import '../../css/Login.css';
import axios from 'axios';

const Login = () => {
    const [id, setId] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');

    const 로그인기능액시오스 = () => {
        axios.post('http://localhost:9010/login', null, {
            params:{
                id:id, 
                password:password
            }
        })
        .then (response => {
            if (response.status === 200) {//로그인 성공시 200 주소가 보임
                setMessage("로그인 성공");
            } else {
                setMessage("로그인 실패");
            }
        })
        .catch(e => {
            setMessage("로그인 중 문제가 발생했습니다.");
        })

    }
/*
    const 로그인기능페치 = () => {
        fetch('http://localhost:9010/login', {
            method: "POST",
            headers: {'Content-Type':'application/json',
            },
            body: JSON.stringfy({id, password}),
        })
        .then(response => {
            if (response.ok) {
                return response.text();
            }
        })
        .then(result => {
            setMessage(result); //자바에서 설정한 로그인 실패 / 성공에 대한 메세지를 그대로 사용
        })
        .catch();
    }
*/

    return (
        <div className='login-container'>
            <h3>로그인하기</h3>
            <div>
                <label>
                    아이디 : 
                    <input type="text"
                           placeholder="아이디를 입력하세요."
                           value={id} 
                           onChange={(e) => setId(e.target.value)}/>
                </label>
                <label>
                    비밀번호 : 
                    <input type="password"
                           placeholder="비밀번호를 입력하세요."
                           value={password}
                           onChange={(e) => setPassword(e.target.value)}/>
                </label>
                <button onClick={로그인기능액시오스}>로그인하기</button>
                {message &&
                    <p>{message}</p>
                }
                <div className='find-sign-buttons'>
                    <button>아이디찾기</button>
                    <button>비밀번호찾기</button>
                    <button>회원가입하기</button>
                </div>
            </div>
            <label>
                SNS로 로그인하기 : 
                <img src="./naver_img/btnG_icon_round.png" 
                    className='naver-logo-img'
                    style={{cursor:'pointer'}}/>
            </label>
            {/*
            <button className='naver-login-button'>
                <img src="./naver_img/btnD_icon_round.png"/>
                네이버로 회원가입하기
            </button>
            */}
        </div>
    )
}
export default Login;
//<img src="./naver_img/btnD_icon_round.png"/>
//이미지는 일반적으로 영어로 넣는게 좋다
//한글로 넣으면 깨질 수 있음