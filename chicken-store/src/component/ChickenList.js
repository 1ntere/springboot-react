import React, {useState, useEffect} from 'react';
import axios from 'axios';

const ChickenList = () => {
    const [chickens, setChickens] = useState([]);

    //최초 1회 실행하는 useEffect를 이용해서
    //처음에 ChickenList.js를 시작하자마자 DB에 저장된 치킨 메뉴들 가져오기
    //useEffect(() => {기능설정}, [언제 다시 기능을 동작시킬 것인가?]);
    useEffect(() => {
        axios.get("http://localhost:9091/api/chicken")
        .then(response => setChickens(response.data))//가져온 데이터를 chickens 변수에 저장하는 과정
        .catch(e => alert("불러오는데 문제가 발생했습니다."));
    }, []);

    return (
        <div className='chicken-container'>
            <h1>치킨 메뉴</h1>
            <ul>
                {chickens.map(chicken => (
                    <li key={chicken.id}>
                        {chicken.chickenName} = {chicken.description} = ₩{chicken.price}원
                    </li>
                ))}
            </ul>
        </div>
    )
}

export default ChickenList;