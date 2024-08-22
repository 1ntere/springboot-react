import './MainRouter.css';
import ChickenList from './component/ChickenList';
import ChickenForm from './component/ChickenForm';
import Modal from './component/Modal';
import { useState } from 'react';

function MainRouter() {
  const [isModalOpen, setIsModalOpen] = useState(false);
    //useState(false) : 사용자가 보고 싶을 때 볼 수 있도록 처음에는 보이지 않음 설정
    //open => true, close => false

  const openModal = () => {
    setIsModalOpen(true);
  }
  const closeModal = () => {
    setIsModalOpen(false);
  }
  
  return (
    <div className="app-container">
      <h1>치킨 가게 메뉴 관리</h1>

      <button onClick={openModal} className='chicken-register-button'>메뉴등록하기</button>
      <Modal isOpen={isModalOpen} onClose={closeModal}>
        <ChickenForm />
      </Modal>

      <ChickenList />
    </div>
  );
}

export default MainRouter;