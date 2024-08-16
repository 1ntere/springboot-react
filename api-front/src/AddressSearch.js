import { useEffect, useState } from "react";

const AddressSearch = () => {
  const [address, setAddress] = useState("");
  const [추가주소, set추가주소] = useState("");
  const [최종주소, set최종주소] = useState("");

  //백엔드 API url 주소를 /api/addUser로 Restful 연결을 하려한다.
  //Restful 연결 : java 컨트롤러로 연결해서 DB에 값을 넣는다.
  //1. fetch 버전, then과 catch 사용 / async와 await는 않 씀
  const saveFetch  = () => {
    fetch("http://localhost:9013/api/addUser", {
        method: "POST",
        headers: {
            'Content-Type':'application/json',
        },
        //Java에서 파라미터 값도 address로 설정해줘야 함
        body:JSON.stringify({address:최종주소}),
    })
    //데이터 넣기 성공했을 때 보여줄 것
    .then (response => response.json())
  }

  //2. axios 버전
  const saveAxios  = () => {
    axios.post("http://localhost:9013/api/addUser", {address:최종주소})
    //데이터 넣기 성공했을 때 보여줄 것
    .then (response => alert("데이터 넣기 성공!"))
  }



  //주소검색을 완료하고 사용자가 검색한 데이터를 가져와서 기능 실행하기
  const handleComplete = (data) => {
    let fullAddress = data.address;//서울 강남구 테헤란로14길 6 (역삼동, 남도빌딩)
    //사용자가 선택한 주소를 fullAddress에 저장
    let extraAddress = '';//남도빌딩을 선택했을 때 몇 층, 몇 호

    //R : Road, 도로명주소, J : Jibeon, 지번주소
    //따라서 아래 코드는 도로명주소
    if (data.addressType === 'R') {//주소 타입이 도로명 주소일 경우
      if (data.bname !== '') {
        //data.bname !== '' : 특정 동이 존재하면 추가 - 역삼동
        extraAddress += data.bname;
      }
      if (data.buildingName !== '') {
        //data.buildingName !== '' : 특정 빌딩 이름이 존재하면 추가 - 남도빌딩
        extraAddress +=
          extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName;
      }
      fullAddress += extraAddress !== '' ? ` (${extraAddress})` : '';
    //fullAddress : 모든 주소 합쳐서 정리하기
    }

    //완성된 주소
    setAddress(fullAddress);
  };

  //주소검색 버튼을 누를 경우 openPostCode 기능을 실행
  const openPostcode = () => {
    new window.daum.Postcode({
  //new window.daum.Postcode : 새로운 윈도우창에서.daum.우편번호서비스를실행
      oncomplete: handleComplete,
    //oncomplete: handleComplete : 사용자가 주소 검색을 완료했을 때 호출하는 함수 지정
    //            handleComplete : 호출하는 함수, 주소검색을 완료하고 나서 실행할 기능 선택
    //oncomplete : 다음에서 제공
                //handlecomplete : 개발자가 만든 기능
    }).open();
    //.open() : 실행하기
  };

  //useEffect 활용해서 최종주소 추가
  useEffect (() => {
    set최종주소(`${address} ${추가주소}`);
  }, [address, 추가주소]);
    //address : 선택한 주소
             //추가주소 : 소비자가 입력한 추가 주소
  
  return (
    <div>
      <button onClick={openPostcode}>주소 검색</button>
      {address &&
        <div>
            <input type='text'
                   placeholder='추가 주소 입력 (예 : 아파트 동 / 호수)'
                   value={추가주소}
                   onChange={(e) => set추가주소(e.target.value)}
            />
            <div>
                선택한 주소: {address}
            </div>
        </div>
      }
      {address && 추가주소 && (
        <>
            <p>최종 추가주소</p>
            <h5>{최종주소}</h5>
            <input type="hidden"
                   value={최종주소}/>
        {/* input type="hidden" : <h5>{최종주소}</h5> 형식으로 보여줘도 되지만
                                  나중에 value 값으로 최종주소를 DB에 넣어야 할 때 사용
                                  주로 최종 input 값은 type="hidden"으로 해서
                                  소비자의 눈에 보이지 않게 해놓고 DB에 넣어줌 */}
        </>
      )}
    </div>
  );
};

export default AddressSearch;