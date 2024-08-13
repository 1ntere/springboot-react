import React, {useEffect, useState} from 'react';
import "../css/Profile.css";
import axios from 'axios';

const Profile = () => {
    const watchAPI = "http://localhost:9008/profile/watching";
    const uploadAPI = "http://localhost:9008/profile/upload";

    const [files, setFiles] = useState([]);
    const [username, setUsername] = useState('');
    const [profile, setProfile] = useState([]);
    const [userId, setUserId] = useState(null);

    //const로 변수명을 설정하거나 기능명 설정
    const 파일변경기능 = (e) => {
        //파일을 변경했을 때 프로필 썸네일에 이미지들 주소가 넘어갈 수 있도록 설정
        const 선택한파일들 = Array.from(e.target.files);
        console.log("선택한파일들", 선택한파일들);
        setFiles(선택한파일들);
    }

    const 유저네임변경기능 = (e) => {
        setUsername(e.target.value);
    }

    //1. fetch 버전 : 설치가 필요없음, 리액트에서 제공하는 Java 백엔드와 통신하는 기능
    const 이미지업로드1 = () => {
        const formData = new FormData(); //files 이미지 파일이 여러개이기 때문에 묶어서 보내려고
        Array.from(files).forEach((file) => {
            formData.append("files", file);
        });
        formData.append("username", username);

        fetch(uploadAPI, {
      //fetch(백엔드주소                             , {
      // /profile/upload : 이렇게만 작성하면 localhost 3000 번인지 9010 번인지 혼동될 수가 있다.
            method: "POST", //DB에 값을 저장하기위해 POST 사용
            headers: {'Content-Type' : 'multipart/form-data'}, //데이터에 파일(이미지)이 포함됨을 자바에 알려줌
            body:formData
        })
        //MySQL DB에 값 넣기를 성공했다면! 성공 후 수행할 작업
        .then(response => {
            //응답에 대한 결과를 json형식으로 받음
            return response.json();
        })
        .then((data) => {
            //DB에 저장된 프로필 사진과 닉네임을 보여주기
            //업로드하고 사용자들이 눈치 못체게 새로고침하기
            게시물가져오기();
        });
    };
    //2. axios async await 버전 : 3번의 업그레이드 버전, try / catch를 사용해서 오류 처리
    //async () : 이 기능에는 잠시 대기해야할 코드가 적혀있다.
    const 이미지업로드2 = async () => {
        const formData = new FormData();
        Array.from(files).forEach((file) => {
            formData.append("files", file);
        });
        formData.append("username", username);
  
        await axios.post(uploadAPI, formData,  {
      //await : formData를 가져오기 전까지 잠시 대기
            headers: {/*'Content-Type' : 'multipart/form-data'*/},
        })
        .then((response) => {
/*
            return response.json();
        })
        .then((data) => {
*/
        const data = response.data;
            게시물가져오기();
        });
    };
    //3. axios then 버전
    const 이미지업로드3 = () => {
        const formData = new FormData();
        Array.from(files).forEach((file) => {
            formData.append("files", file);
        });
        formData.append("username", username);

        //삼항연산자를 이용해서 수정기능을 위한 url과 프로필을 저장할 url 설정

        axios.post(uploadAPI, formData,  {
            headers: {'Content-Type' : 'multipart/form-data'},
        })
        .then((response) => {
/*
fetch에서는 아래의 기능이 필요
            return response.json();
        })
        .then((data) => {
axios에서는 불필요
*/
        const data = response.data;
            게시물가져오기();
        });
    };

    //페이지 새로고침해서 0.00001초 전에 업로드한 파일 사용자 눈에 보여주기
    useEffect (() => {
        게시물가져오기();
    }, []);

    const 게시물가져오기 = () => {
        axios.get(watchAPI).then((response) => {
            setProfile(response.data);
            console.log("프로필 가져오기 : " + response.data);
        })
    };

    //닉네임 수정하기
    const 수정기능 = (p) => {
        setUserId(p.userId);//수정할 사용자 ID 설정
        setUsername(p.username);
    }

    return (
        <div>
            <h1>프로필 이미지 업로드</h1>
            <div className='profile-thumbnail'>
                {files.length > 0 && 
                    files.map ((file, index) => (
                    <div key={index}>
                        <img src={URL.createObjectURL(file)} alt='프로필이미지썸네일'/>
                    </div>
                ))}
                
            </div>

            <input type="file" 
                   /*multiple*/
                   onChange={파일변경기능} />
            {/* 
            1. <input type="file" multiple onChange={(e) => setFiles(e.target.files)} />
            2. <input type="file" multiple onChange={파일변경기능} />
            1번과 2번 코드는 동일한 기능을 작동하지만
            const를 이용해서 기능을 구분짓는 것과 기능을 한번에 작성해주는 차이가 존재
            const를 이용해서 기능을 변수명으로 처리하면 하나의 onChange에 여러 기능을 넣을 수 있음
             */}
               {/* onChange={(e) => setFiles(e.target.files)} : 대신에 onChange={파일변경기능} 을 사용해도 됨 */}
            <input type='text' 
                   placeholder='닉네임을 입력하세요'
                   value={username}
                   onChange={유저네임변경기능}/>
                   {/* onChange={유저네임변경기능} : 대신에 onChange={(e) => setUsername(e.target.value)} 을 사용해도 됨 */}
            <button onClick={이미지업로드3}>프로필저장하기</button>
            <hr/>
            <h3>프로필 상세페이지</h3>
            <div>
                {profile.length > 0 &&
                profile.map((p) => (
                      //map : 프로필 하나만 볼 때는 map이 필요 없음
                    <div key={p.userId}>
                        <p>{p.username}</p>
                        <p>{p.createdAt}</p>
                        {p.profileImageUrl &&
                        p.profileImageUrl.split(',').map((image) => (
                            <img key={image}
                                 src={`http://localhost:9008/images/${image}`}
                                 alt='프로필이미지' />
                        ))}
                        <button onClick={수정기능}>프로필 이미지, 닉네임 변경하기</button>
                    </div>
                ))}
            </div>
        </div>
    )
}

export default Profile;