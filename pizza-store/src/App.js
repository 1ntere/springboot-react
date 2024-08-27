import './App.css';
import{BrowserRouter as Router, Route, Routes} from'react-router-dom';
import PizzaList from './components/PizzaList';
import PizzaRouter from './components/PizzaRouter';
import PizzaResult from './components/PizzaResult';
function App() {
  return (
  <Router>
    <PizzaRouter />
    <Routes>
      <Route path='/' element={<PizzaList />} />
    {/* <Route path='/pizza-detail' element={<PizzaList />} /> */}
     <Route path='/search'       element={<PizzaResult />} /> 
    </Routes>
  </Router>
  );
}

export default App;
