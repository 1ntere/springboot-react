import React from "react";
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import ChickenDetail from "./component/ChickenDetail";
import MainRouter from "./MainRouter";
import SearchResult from "./component/SearchResult";
function App () {

    return (
        <Router>
            <Routes>
                <Route path="/" element={<MainRouter />} />
                {/*
                Routes 안에는 Route로 설정된 태그만 들어올 수 있음
                <MainRouter/>
                */}
                <Route path="/chicken-detail/:id" element={<ChickenDetail />} />
                <Route path="/search" element={<SearchResult />} />
            </Routes>
        </Router>
    )
}

export default App;