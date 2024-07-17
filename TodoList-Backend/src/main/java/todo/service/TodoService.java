package todo.service;

import java.util.List;

import todo.dto.Todo;
import todo.dto.TodoMember;

public interface TodoService {
	int idCheck(String id);
	
	int signup(TodoMember member);
	
	TodoMember login(TodoMember member);
	
	List<Todo> selectTodoList(int todoMemberNo);
	
	int insert(Todo todo);
	
	int update(Todo todo);
	
	int delete(int todoNo);
}
