import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import '../css/ChickenDetail.css';

const ChickenDetail = () => {
    const {id} = useParams();
    //{id} : {} = 특정 값을 받아오는 것, [] = 변수명을 설정하는 것
    console.log("id : ", id);
    const [chicken, setChicken] = useState(null);

    useEffect (() => {
        axios.get(`http://localhost:9091/api/chicken/${id}`)
        .then(response => {
            setChicken(response.data);
        })
        .catch(err => 
            alert("불러오는데 문제가 발생했습니다.")
        );
    }, [])

    //만약에 치킨 데이터가 없으면 로딩중
    if(!chicken) {
        return (
            <div>
                로딩중 ...
            </div>
        )
    }

    return (
        <div className="chicken-detail-container">
            <h1>{chicken.chickenName}</h1>
            <p>{chicken.description}</p>
            <p>{chicken.price}원</p>
        </div>
    )
}

export default ChickenDetail;