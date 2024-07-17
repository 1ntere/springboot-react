package todo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import todo.dto.TodoMember;
import todo.mapper.TodoMapper;

@Service
public class TodoServiceImpl implements TodoService {
	@Autowired
	private TodoMapper mapper;
	
	@Override
	public int idCheck(String id) {
		return mapper.idCheck(id);
	}
	
	@Override
	public int signup(TodoMember member) {
		return mapper.signup(member);
	}
	
	@Override
	public Map<String, Object> login(TodoMember member) {
		TodoMember loginMember = mapper.login(member);
		
		Map<String, Object> mape = new HashMap<String, Object>();
		mape.put("loginMember", loginMember)
	}
}
