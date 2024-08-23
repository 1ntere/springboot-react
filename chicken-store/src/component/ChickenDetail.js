import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import '../css/ChickenDetail.css';

const ChickenDetail = () => {
    const {id} = useParams();
    //{id} : {} = 특정 값을 받아오는 것, [] = 변수명을 설정하는 것
    console.log("id : ", id);
    const [chicken, setChicken] = useState(null);

    //수정된 데이터를 저장하는 공간, 초기에는 수정한 내용이 없기 때문에 "" 빈 공간으로 설정
    const [editData, setEditData] = useState({
        chickenName: "",
        description: "",
        price: ""
    })

    //수정하기 버튼을 눌렀는지 안 눌렀는지 체크 boolean
    const [isEditing, setIsEditing] = useState(false);

    //페이지를 이동하기 위한 navigate 함수
    const navigate = useNavigate();
        //navigate : 기능 작성에서 이동할 때 주로 사용
        //           이동하는 동작이 소비자들 눈에 직접적으로 보이지 않음, 개발자가 암묵적으로 이동시킴
        //Link : 태그에서 직접적으로 주소 이동을 작성해줄 때 사용

    useEffect (() => {
        axios.get(`http://localhost:9091/api/chicken/${id}`)
        .then(response => {
            setChicken(response.data);
            setEditData({
                chickenName: response.data.chickenName, 
                description : response.data.description,
                price : response.data.price
            })
        })
        .catch(e=> alert("불러오는데 문제가 발생했습니다."));
    }, [])

    //만약에 치킨 데이터가 없으면 로딩중
    if(!chicken) {
        return (
            <div>
                로딩중 ...
            </div>
        )
    }

    /*
                //내용 수정하기 setEditData
            setEditData({
                chickenName: response.data.chickenName,
                description: response.data.description,
                price: response.data.price
    */
    const handle수정한내용저장 = () => {
        axios.put(`http://localhost:9091/api/chicken/${id}`, editData)
        .then(response => {
            setChicken(response.data);//기존의 DB에 저장된 내용 가져오기
            setIsEditing(false);
        })
        .catch(error => {
            console.error("수정하는데 문제가 발생했습니다.", error);
        })
    }

    const handle수정하기 = () => {
        setIsEditing(true);
    }

    const handle돌아가기 = () => {
        setIsEditing(false);
    }

    const handle삭제하기 = () => {
        axios.delete(`http://localhost:9091/api/chicken/${id}`)
        .then(() => {
            alert("삭제되었습니다.");
            navigate("/");//삭제하고 메인으로 이동하기
        })
        .catch(error => {
            console.log("삭제하는데 문제가 발생했습니다.")
        })
    }

    return (
        <div className="chicken-detail-container">
            {/*수정하기버튼을 누르면? 수정하기 기능 나오기 : 안누르면 작성된 내용 보여주기*/}
            { isEditing ? (
                <div>
                    <label>치킨 이름 : 
                        <input type="text"
                               name="chickenName"
                               value={editData.chickenName}
                               onChange={(e) => setEditData({...editData, chickenName:e.target.value})}/>
                    </label><br/>
                    <label>설명 : 
                        <textarea name="description"
                                  value={editData.description}
                                  onChange={(e) => setEditData({...editData, description:e.target.value})}/>
                    </label><br/>
                    <label>가격 : 
                        <input type="number"
                               name="price"
                               value={editData.price}
                               onChange={(e) => setEditData({...editData, price:e.target.value})}/>
                    </label><br/>
                    <button onClick={handle수정한내용저장}>수정완료</button><br/>
                    <button onClick={handle돌아가기}>돌아가기</button>
                </div>
            ) : (
                <div>
                    <h1>{chicken.chickenName}</h1>
                    <p>{chicken.description}</p>
                    <p>{chicken.price}원</p>
                    <button onClick={handle수정하기}>수정하기</button>
                    <button onClick={handle삭제하기}>삭제하기</button>
                </div>
            )
            }
        </div>
    )
}

export default ChickenDetail;