import logo from './logo.svg';
import './App.css';
import AirPollutionData from './AirPollutionData';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>공기오염 조회서비스</h1>
        <AirPollutionData/>
      </header>
    </div>
  );
}

export default App;
