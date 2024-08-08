import {Link} from "react-router-dom";
import '../../css/Header.css';

const Header = () => {
    return (
        <nav className="header-nav">
            <ul className="nav-list">
                <li className="nav-item"><Link to="/" className="nav-link">메인페이지</Link></li>
                <li className="nav-item"><Link to="/board" className="nav-link">게시판</Link></li>
                <li className="nav-item"><Link to="/profile" className="nav-link">프로필</Link></li>
            </ul>
        </nav>
    )
}

export default Header;