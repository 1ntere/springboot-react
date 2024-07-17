import React, {useState} from "react";

const Signup = () => {
    const [id, setId] = useState('');
    const [pw, setPw] = useState('');
    const [pwCheck, setPwCheck] = useState('');
    const [name, setName] = useState('');
    const [result, setResult] = useState('');

    const [idValidation, setIdValidation] = useState(false);

    const 아이디중복검사 = (eventId) => {
        setId(eventId);

        if (eventId.trim().length < 4) {
            setIdValidation(false);
            return;
        }

        fetch("/idCheck?id=" + eventId)
            .then (response => response.text())
            .then (result => {
                if (Number(result) === 0) {
                    setIdValidation(true);
                } else {
                    setIdValidation(false);
                }
            })
    }

    const 회원가입버튼 = () => {
        if(!idValidation) {
            alert('아이디가 유효하지 않습니다.');
            return;
        }

        if (pw !== pwCheck) {
            alert('비밀번호가 일치하지 않습니다.');
            return;
        }

        const input값들 = {};
        input값들.id = id;
        input값들.pw = pw;
        input값들.name = name;

        fetch("/signup", {
            method: "POST",
            headers: {"Content-Type" : "application/json"}, 
            body : JSON.stringify(input값들)
        })

        .then(response => response.text())
        .then(result => {
            if (Number(result) > 0) {
                setResult('회원가입성공!~');
                setId('');
                setPw('');
                setPwCheck('');
                setName('');
            } else {
                setResult('회원가입 실패~!');
            }
        })
    }

    return (
        <div className="signup-container">
            <label>  ID :  
                <input type="text" 
                    onChange={e => 아이디중복검사(e.target.value)}
                    value={id}
                    className={idValidation ? '' : 'id-err'}/>
            </label>

            <label> PW : 
                <input type="password" 
                       onChange={e => setPw(e.target.value)}
                       value={pw}/>
            </label>

            <label> PW CHECK : 
                <input type="password" 
                       onChange={e => setPwCheck(e.target.value)}
                       value={pwCheck}/>
            </label>

            <label> NAME : 
                <input type="text" 
                       onChange={e => setName(e.target.value)}
                       value={name}/>
            </label>

            <button onClick={회원가입버튼}>가입하기</button>
            <hr/>

            {/* 회원가입이 무사히 완료됐는지 결과 표시 */}
            <h3>{result}</h3>
        </div>
    ) 
}

export default Signup;