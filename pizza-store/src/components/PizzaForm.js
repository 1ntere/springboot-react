import { useState } from "react";
import axios from "axios";

const PizzaForm = () => {
    const [pizzaName, setPizzaName] = useState('');
    const [description, setDescription] = useState('');
    const [price, setPrice] = useState('');

    const 전달데이터 = {
        name :pizzaName,
        description,
        price
    }
    // 스프링부트 연결 후 input에 작성한 데이터 전달
    const handleRegister = () => {
        axios.post('http://localhost:9093/api/pizza', 전달데이터)
        .then((response) => {
            alert("메뉴가 성공적으로 등록되었습니다.")   
        })   
        .catch((e) => {
            alert("메뉴 등록에 실패했습니다.")
        });
    }
    return (
        <div className="pizzaform-container">
            <label>
                메뉴 이름 : 
                <input value={pizzaName} onChange={(e) =>setPizzaName(e.target.value)}/>
            </label>
            <label>
                메뉴 설명 : 
                <input  value={description} onChange={(e) => setDescription(e.target.value)}/>
            </label>
            <label>
                메뉴 가격 : 
                <input  value={price} onChange={(e) => setPrice(e.target.value)}/>
            </label>
            <button onClick={handleRegister}>등록하기</button>
            
        </div>
    )
}

export default PizzaForm;