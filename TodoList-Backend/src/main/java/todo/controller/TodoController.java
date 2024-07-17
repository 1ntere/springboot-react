package todo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import todo.dto.Todo;
import todo.dto.TodoMember;
import todo.service.TodoService;

@RestController
//Controller : DTO와 Service의 URL 주소 연결
public class TodoController {
	@Autowired
	private TodoService service;
	
	/** 아이디 중복검사
	 * @param id
	 * @Return 중복 : 1, 사용가능 : 0
	 *         중복 = select Count(*)를 했을 때 만약에 사용하고자 하는 아이디가 존재하면 
	 *               Count 값이 1로 넘어오고
	 *               사용하고자 하는 아이디가 DB에 존재하지 않으면 0이 넘어옴
	 */
	@GetMapping("idCheck")
	public int idCheck(@RequestParam("id") String id) {
		return service.idCheck(id);
	}
	
	/** 회원가입
	 * @param member
	 * @return 성공 : 1, 실패 : 0
	 */
	@PostMapping("/signup")
	public int signup(@RequestBody TodoMember member) {
		return service.signup(member);
	}

	/** 로그인
	 * @param member
	 * @return 성공 : 회원정보의 /todolist, 실패 : null
	 */
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody TodoMember member) {
		return service.login(member);
	}
	
	/** 할 일 추가
	 * @param todo
	 * @return 성공 1 : 실패 0
	 */
	@PostMapping("/todo")
	public int insert(@RequestBody Todo todo) {
		return service.insert(todo);
	}
	
	/** 할 일 수정
	 * @param todo
	 * @return 성공 1 : 실패 0
	 * 
	 * update(수정)할 때는 @PostMapping 대신 @PutMapping을 사용하도록 하자
	 * @PostMapping을 사용해도 가능하긴 하지만 직관적이지 않다
	 */
	@PutMapping("/todo")
	public int update(@RequestBody Todo todo) {
		return service.update(todo);
	}
	
	/** 할 일 삭제
	 * @param todoNo
	 * @return 성공 1 : 실패 0
	 * 
	 * delete(삭제)할 때는 @PostMapping 대신 @DeleteMapping을 사용하도록 하자
	 * @PostMapping을 사용해도 가능하긴 하지만 직관적이지 않다
	 */
	@DeleteMapping("/todo")
	public int delete(@RequestBody int todoNo) {
		return service.delete(todoNo);
	}
	
	/*
	★ CRUD와 DML의 차이점 ★
	
	CRUD	: DataBase 이외의 다른 것과 상호작용하여
	          데이터를 조작하는 기본적인 4가지 작업
	
			  Create	: 새로운 데이터를 생성
			  Read		: 데이터를 읽고 조회
			  Update	: 데이터 수정
			  Delete	: 데이터 삭제
	
	Insert Select Update Delete : DML, 데이터베이스 내에서 데이터를 조작하는 작업
	*/
	
	/*
	http메서드 : GET, POST, PUT, DELETE
	           웹 주소에서 사용되는 기능 명칭
	           http(인터넷=웹)에서 사용자(소비자)가
	           서버에 회원가입이나 로그인과 같은 요청을 보낼 때 사용하는 기능, 명칭
	           http메서드는 CRUD 작업과 연결해서 사용
	           
	           GET		 : 서버로부터 데이터를 조회하기 위한 요청
	                       CRUD에서 Read
	                       GET /users = 모든 사용자 목록을 조회하는 주소
	           POST		 : 클라이언트가 서버에 새로운 데이터를 생성해달라고 요청
	                       CRUD에서 Create
	                       POST /users = 새로운 사용자를 생성
	                       body에 사용자의 정보가 포함되어 DB에 전송
	           PUT		 : 클라이언트가 서버에 존재하는 데이터를
	                       본인의 취지에 맞게 업데이트 해달라고 요청
	                       CRUD에서 Update
	                       PUT /mypage = 기존에 존재하는 사용자가
	                                     자신의 정보를 수정해달라고 서버에 요청함
	                                     DB가 수정됨
	           DELETE	 : 클라이언트가 서버에 존재하는 데이터를 삭제하기 위해 요청
	                       CRUD에서 Delete
	                       Delete /user/1 = 회원정보가 1인 사용자를 삭제
	*/
}
