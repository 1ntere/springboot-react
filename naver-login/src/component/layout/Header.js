import React, {useContext} from "react";
import AuthContext from "./AuthContext";
import {Link} from "react-router-dom";
//로그인 성공하면 보여줄 헤더
//로그인 성공 시 00님 환영합니다!
//새로고침을 해도 로그인이 풀리지 않게 끔
//비밀번호를 DB에 저장시 암호화해서 저장

//비밀번호 찾기 -> 기존 비밀번호 x, 새로운 비밀번호 확인 후 저장
const Header = () => {
    const {loginMember, setLoginMember} = useContext(AuthContext);
    /*
    - [] : 변수를 새로 설정
        const [loginMember, setLoginMember] = useContext(AuthContext);
    - {} : 외부에서 작성된 변수를 가져와서 사용할 때 설정
        const {loginMember, setLoginMember} = useContext(AuthContext);
    */

    const handleLogout = () => {
        setLoginMember(null);
        localStorage.removeItem('loginMember');
    }
    //localStorage : 고객 컴퓨터 웹사이트에 데이터를 영구적으로 저장
    //localStorage에 저장된 데이터는 브라우저를 닫거나 컴퓨터를 껐다 켜도 유지
    //사용자가 타이머를 맞춰서 삭제하거나, 로그아웃을 하거나, 캐시를 지우지 않는 한 유지
    //대표적으로 구글 크롬 로그인

    return(
        <header>
            <h1>헤더부분</h1>
            <nav>
                {/*loginMember가 ? (                     존재한다면                   ) : (존재하지않는다면)*/}
                {/*loginMember   ? (<span>환영합니다. {loginMember.dto변수명} 님</span>) : ()*/}
                {loginMember     ? (
                    <div>
                        <span>환영합니다. {loginMember.name} 님</span>
                        <button onClick={handleLogout}>로그아웃</button>
                    </div>
                    ) : (
                    <div>
                        <Link to="/login">로그인하기</Link>
                        <Link to="/api/naver">네이버로 회원가입하기</Link>
                    </div>
                    )}
                                     {/* {loginMember.name} : Java에서 NaverUser.java DTO에서
                                                              유저 이름을 private String name;으로 지정했기 때문에 */}
            </nav>
        </header>
    )
}
export default Header;