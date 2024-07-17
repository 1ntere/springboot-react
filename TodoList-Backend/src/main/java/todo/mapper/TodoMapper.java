package todo.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import todo.dto.Todo;
import todo.dto.TodoMember;

@Mapper
public interface TodoMapper {
	int idCheck(String id);
	int signup(TodoMember member);
	Map<String, Object> login(TodoMember member);
	int insert(Todo todo);
	int update(Todo todo);
	int delete(int todoNo);
}
