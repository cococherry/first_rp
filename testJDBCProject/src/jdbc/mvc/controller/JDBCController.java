package jdbc.mvc.controller;

import java.util.ArrayList;

import jdbc.mvc.model.dao.EmployeeDAO;
import jdbc.mvc.model.vo.Employee;
import jdbc.mvc.view.JDBCView;

public class JDBCController {
	public JDBCController() {
	}

	public ArrayList<Employee> selectList() {
		ArrayList<Employee> empList = new EmployeeDAO().selectAllEmployee();
		
		//return new EmployeeDAO().selectAllEmployee();
		// - 이렇게 되면 성공, 실패에 대한 구분을 컨트롤러에서 처리하지 못함
		return empList;
	}

	public Employee selectEmployee(String empId) {
		Employee e = new EmployeeDAO().selectOne(empId);
		return e;
	}

	public void insertEmployee(Employee e) {
		int result = new EmployeeDAO().insertEmployee(e);
		if(result > 0){
			//성공
			System.out.println(result+"행이 추가되었습니다.");
			//new JDBCView().displayMenu();
		} else {
			//실패
			System.out.println("새 직원 추가 등록 실패!!");
			//new JDBCView().displayMenu();
		}
		//System.out.println(e+"\n데이터가 정상적으로 입력되었습니다.");
	}
	public void updateEmployee(Employee e) {
		int result = new EmployeeDAO().updateEmployee(e);
		if(result > 0){
			//성공
			System.out.println(result+"행이 수정되었습니다.");
			//new JDBCView().displayMenu();
		} else {
			//실패
			System.out.println("직원 수정 실패!!");
			//new JDBCView().displayMenu();
		}
	}
	public void deleteEmployee(String empId) {
		int result = new EmployeeDAO().deleteEmployee(empId);
		if(result > 0){
			//성공
			System.out.println(result+"행이 삭제되었습니다.");
			//new JDBCView().displayMenu();
		} else {
			//실패
			System.out.println("직원 삭제 실패!!");
			//new JDBCView().displayMenu();
		}
	}
}
