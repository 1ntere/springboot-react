import React, {useState, useEffect} from "react";

const Login = () => {
    const [userInfo, setUserInfo] = useState(null);

    //useEffect를 활용해서 데이터를 가져오기
    //get을 이용해서 특정 유저 정보를 가져오는 주소 설정
    useEffect(() => {
        const 유저정보 = () => {
            fetch('http://localhost:9010/userInfo')//http method(GET, POST, PUT, DELETE)
            .then(가져온응답결과 => {//then : fetch가 java controller에서 값을 가져왔을 때 실행할 구문
                return 가져온응답결과.json();
            })
            .then( data => {//위 then 실행한 구문을 바탕으로 데이터를 userInfo에 넣어주기
                setUserInfo(data);
            })
            .catch(err => {//위 2가지 then 모두 문제가 생겼을 때 문제를 catch할 구문
                console.error("Error user INFO : ", err);
            })
        };
        
        //유저정보 기능을 실행하는 구문
        유저정보();
    }, [])//최초 실행인지 주기적으로 계속 실행하는 효과인지 설정

    return (
        <>
            {/* 만약에 userInfo 정보가 있으면 로그인 완료이고 userInfo 정보가 없으면 로그인하기 화면 보여줌 */}
            {/* 삼항 연산자 이용하기 */}
            {/*userInfo ? ('존재한다면 보여줄 코드') : ('존재하지 않다면 보여줄 코드')*/}
            {userInfo ? (
                /* 2. userInfo 정보가 있을 때 */
                <div>
                    <h1>로그인 완료!</h1>
                    <div>{ JSON.stringify( userInfo, null, 2 ) }</div>
                </div>
            )
            : (
                /* 1. userInfo 정보가 없을 때 */
                <a href="http://localhost:9010/api/naverLogin">
                    <img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG" />
                </a>
            )}
        </>
    )
}

export default Login;
/*
{       JSON.stringify        (      userInfo     ,     null    ,      2     )  }
{ 백엔드에서 가져온 값 문자열 처리( 네이버에서 가져온 값, 특정값을 변환, 2칸 들여쓰기)}
{ JSON.stringify( userInfo, null, 2 ) }
//JSON.stringify = 자바 백엔드에서 가져온 값을 문자열로 변환
                   자바에서 가져오는 데이터가 숫자인지, 문자인지 알 수 없는 상태이기 때문에
                   안전하게 한 번 더 문자열로 가져오겠다 설정
                //userInfo : 가져온 값에서 특정 값을 변경하거나 필터링할 것인가?
                          //null : 변경 x, null
                             만약에 고객이 전화번호를 010.2345.6789, 010 3333 3333, 01012345678 처럼
                             내가 원하는 방식이 아닌 다른 방식으로 값을 입력했을 때
                             (phone, {'.'을 '-'로 변경, ''을 '-'로 변경 등등 변경할 방법}, n칸 들여쓰기)
==================================================================================
★{       JSON.stringify        (      유저정보     ,가운데 사용하는 예제 작성,      2     )  }
const 유저정보 = {
    id: "123456",
    name: "홍길동",
    phone: "010-9876-5432"
  //(key): (value)
}
DB에 유저가 작성한 핸드폰 번호 중 '-'를 제거하고 01098765432로 저장하길 원함

const 번호변경하기 = (key, value) =>  {
    //만약에 key 이름이 phone라면
    if(key == 'phone') {
        return value.replace("-", "");//'-'를 제거(""를 사용해서 값을 없애줌)
    }
}
{ JSON.stringify( 유저정보, 번호변경하기, 2 ) }

번호변경하기 기능을 거친 유저정보는 아래와 같이 보임
const 유저정보 = {
    id: "123456",
    name: "홍길동",
    phone: "01098765432"
}

만약에 번호를 변경하지 않고, 기존에 가져온 유저정보를 그대로 사용할 경우에는
{ JSON.stringify( 유저정보, null, 2 ) }
 null : 변경사항 없이 그대로 사용
*/