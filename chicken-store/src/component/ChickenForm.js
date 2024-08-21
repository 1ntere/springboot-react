import React, {useState, useEffect} from 'react';
import axios from 'axios';
import '../css/ChickenForm.css';

const ChickenForm = () => {
    const [chickenName, setChickenName] = useState("");
    const [description, setDescription] = useState("");
    const [price, setPrice] = useState("");

    const 전달데이터 = {
        chickenName,
        description,
        price
    }

    const 제출버튼 = () => {
        axios.post("http://localhost:9091/api/chicken", 전달데이터)
        .then((response) => {
            //데이터가 무사히 전달됐을 경우
            alert("메뉴가 성공적으로 등록되었습니다.");
        })
        .catch((e) => {
            alert("메뉴 등록에 실패했습니다.");
        });
    }
    return (
        <div className='chickenform-container'>
            <label>메뉴 이름 : 
                <input type='text'
                       value={chickenName}
                       onChange={(e) => setChickenName(e.target.value)}/>
            </label>
            <label>메뉴 설명 : 
                <textarea value={description}
                          onChange={(e) => setDescription(e.target.value)}/>
            </label>
            <label>가격 : 
                <input type='number'
                       value={price}
                       onChange={(e) => setPrice(e.target.value)}/>
            </label>
            <button onClick={제출버튼}>등록하기</button>
            <button>메인으로 돌아가기</button>
        </div>
    )
}

export default ChickenForm;