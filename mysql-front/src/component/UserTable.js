import React from 'react';

/*
const 컴포넌트명 = () => {}
어떤 값도 받지 않고, 단독으로 기능을 진행할 때 사용
특정 상태나 값을 다른 곳에서 전달받고, 전달받은 값에 의해 변화가 필요하지 않은 경우 사용

const 컴포넌트명 = ({특정값}) => {}
특정값에 영향을 받아 기능이 변화할 때 사용
*/  

//USerTable 컴포넌트는 사이트에 회원가입한 유저들의 정보를 보는 공간
//App.js 전달받은 User 값들을 받아와서 유저 정보를 보여줄 것
const UserTable = ({users}) => {
    return (
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>EMAIL</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {users.map(user => (
                    <tr key={user.id}>
                        <td>{user.id}</td>
                        <td>{user.name}</td>
                        <td>{user.email}</td>
                        <td>
                            <button>유저 삭제하기</button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
    )
}
export default UserTable;