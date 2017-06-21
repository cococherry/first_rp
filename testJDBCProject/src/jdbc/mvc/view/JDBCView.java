package jdbc.mvc.view;

import java.sql.Date;
import java.util.*;

import jdbc.mvc.controller.JDBCController;
import jdbc.mvc.model.vo.Employee;

public class JDBCView {
	private Scanner sc = new Scanner(System.in);
	private JDBCController controller;
	// View는 JDBC 컨트롤러와 교류함
	
	public JDBCView(){}
	
	public void displayMenu(){
		int no;
		controller = new JDBCController();
		do{
			System.out.println("\n\nJDBC 테스트 프로그램\n");
			System.out.println("1.새 직원 등록");
			System.out.println("2.전체 직원 조회");
			System.out.println("3.사번으로 직원 조회");
			System.out.println("4.직원 정보 수정");
			System.out.println("5.직원 정보 삭제");
			System.out.println("9.프로그램 종료");
			System.out.println("메뉴를 고르세요.");
			System.out.print("메뉴 선택 : ");
			no = sc.nextInt();
//			try{
//				no = sc.nextInt();
//			} catch (InputMismatchException e) {
//				System.out.println("옳은 숫자 메뉴가 아닙니다.");
//			}
			switch(no){
			case 1: controller.insertEmployee(input()); break;
			case 2: printList(controller.selectList()); break;
			case 3: printOne(controller.selectEmployee(inputEmpId())); break;
			case 4: controller.updateEmployee(update()); break;
			case 5: controller.deleteEmployee(delete()); break;
			case 9: System.out.print("정말 종료하시겠습니까? (Y/N) : ");
					if(sc.next().toUpperCase().charAt(0) == 'Y') return;
					else {
						System.out.println("메뉴를 다시 실행합니다.");
						break;
					}
			default: System.out.println("잘못 고르셨습니다.\n다시 골라주세요.");
			}
		}while(true);
	}
	
	private void printOne(Employee e) {
		System.out.println(e);
	}

	public String inputEmpId(){
		System.out.print("사번 : ");
		return sc.next();
	}
	
	private void printList(ArrayList<Employee> selectList) {
		for(Employee e : selectList)
			System.out.println(e);
			//System.out.println(e.toString2());
	}

	private String delete() {
		System.out.println("삭제할 사원정보를 입력하세요.");
		System.out.print("사번 : ");
		return sc.next();
	}

	private Employee update() {
		Employee emp = new Employee();
		
		System.out.println("수정할 사원정보를 입력하세요.");
		
		System.out.print("사번 : ");
		emp.setEmpId(sc.next());
		System.out.print("직급번호 : ");
		emp.setJobId(sc.next().toUpperCase());
		System.out.print("월급 : ");
		emp.setSalary(sc.nextInt());
		System.out.print("보너스포인트 : ");
		emp.setBonusPct(sc.nextDouble());
		System.out.print("부서번호 : ");
		emp.setDeptId(sc.next());
		
		return emp;
	}

	// 새 직원 입력용 메소드
	public Employee input(){
		Employee emp = new Employee();
		
		System.out.print("사번 : ");
		emp.setEmpId(sc.next());
		System.out.print("사원명 : ");
		emp.setEmpName(sc.next());
		System.out.print("주민번호( - 포함) : ");
		emp.setEmpNo(sc.next());
		System.out.print("이메일 주소 : ");
		emp.setEmail(sc.next());
		System.out.print("전화번호 : ");
		emp.setPhone(sc.next());
		System.out.print("직급번호 : ");
		emp.setJobId(sc.next().toUpperCase());
		System.out.print("월급 : ");
		emp.setSalary(sc.nextInt());
		System.out.print("보너스포인트 : ");
		emp.setBonusPct(sc.nextDouble());
		System.out.print("기혼여부(Y/N) : ");
		emp.setMarriage(sc.next().toUpperCase());
		System.out.print("관리자 사번 : ");
		emp.setMgrId(sc.next());
		System.out.print("부서번호 : ");
		emp.setDeptId(sc.next());
		
		return emp;
	}
	public void error(){
		System.out.println("직원 정보 추가/수정/삭제 에러 발생!!");
		displayMenu();
	}
}
