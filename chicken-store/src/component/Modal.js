import '../css/Modal.css';
//버튼을 열거나 닫을 때 동작

const Modal = ({isOpen, onClose, children}) => {
    //isOpen이 false이면 아래 return()을 보지 않고 함수를 종료시킴

    if (!isOpen) {
        return null;//아래 return()에 있는 html 태그를 보여주지 않고 돌려보냄
    }

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <button className="modal-close" onClick={onClose}>
                    &times;
                </button>
                {children}
            </div>
        </div>
    )
}

export default Modal;