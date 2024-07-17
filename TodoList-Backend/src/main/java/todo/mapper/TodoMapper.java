package todo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public class TodoMapper {
	int idCheck(String id);
	int signup(멤버 DTO);
	멤버DTO login(멤버 DTO);
	list<할일 DTO> selectTodoList(int todoMemberNo);
	int insert(할일 DTO);
	int update(할일 DTO);
	int delete(int todoNo);
}
