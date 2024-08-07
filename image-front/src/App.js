import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [files, setFiles] = useState([]);
  const [posts, setPosts] = useState([]);

  const Java에업로드 = () => {
    //formData : return에서 form을 사용하진 않았지만 특정 값을 가져와서 넘겨줄 때 사용하는 객체
    //files에서 파일이 하나가 아니라 여러 개 이기 때문에 여러 개를 담을 배열 설정
    const formData = new FormData();
    Array.from(files).forEach(file => {
      formData.append("files", file);
      //return formData.append("files", file);
    });
    formData.append("title", title);
    formData.append("content", content);

    //자바 컨트롤러에 데이터 전송! POST
    axios.post("/gallery/upload", formData, {
      headers: {
        //전송할 데이터에 글자가 아닌 파일이 함께 전송된다 머릿말로 알려주기
        'Content-Type': 'multipart/form-data'
      }
    });
    alert("자바로 이미지 전송했습니다.");
    //DB에 이미지 업로드를 하고 나서 업로드 된 이미지를 불러오기
    게시물가져오기();
  }

  //const : 기능을 작성해놓고 필요할 때 기능을 사용하기 위해 설정
  const 게시물가져오기 = () => {
    axios.get("http://localhost:9008/posts")//자바 컨트롤러 api와 url에서 데이터 가져오기
    .then(response => {
      setPosts(response.data);
      console.log(response.data);
    })
  }

  //맨 처음 사이트에 들어왔을 때 게시물을 바로 가져오게 하고 싶어
  useEffect(() => {
    게시물가져오기 ();
  }, []);
    

  return (
    <div className="App">
      <div>
          <label>제목:</label>
          <input type='text' value={title} onChange={(e) => setTitle(e.target.value)}/>
      </div>
      <div>
          <label>내용:</label>
          <textarea value={content} onChange={(e) => setContent(e.target.value)}/>
      </div>
      <div>
          <label htmlFor="a">이미지선택:</label>
          <input multiple
                  type="file"
                  className='img-input'
                  id="a"
                  accept="image.*" 
                  onChange={(e) => setFiles(e.target.files)}/>
      </div>
      <button onClick={Java에업로드}>Submit</button>
      <hr/>
      <div>
        {posts.map(post => (
          <div key={post.id}  >
            <p>번호 : {post.id}</p>
            <h2>제목 : {post.title}</h2>
            <p>내용 : {post.content}</p>
            {/* 
            {post.files && post.files.map(file => (
              <img key={file} src={file} alt='이미지'/>
            ))}

          //post.files && : post.files 이 존재해야만 && 뒤의 코드가 실행
            //Array에 대한 배열이 제대로 나오지 않으면 에러가 발생할 가능성이 높음
            //"," 를 통해 구분을 따로 설정
            */}

            {/*
            DataBase에 ","를 사용해서 이미지를 여러 장 저장했기 때문에
            "," 기준으로 이미지를 가져와야 함
            */}
            {post.imageUrl.split(',').map(image => 
              <img key={image} src={`http://localhost:9008/images/${image}`} />
            )}
            <p>작성일자 : {post.createdAt}</p>
            <p>-----------------------------------------------------------------------------</p>

          </div>
        ))}
      </div>
    </div>
  );
}

export default App;