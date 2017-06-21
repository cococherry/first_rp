package jdbc.mvc.model.dao;

import java.util.ArrayList;
import java.sql.*;
import jdbc.mvc.model.vo.Employee;

//Database Access Object
// 데이터 베이스 관련 클래스는 모두 Dao임
// 쿼리문을 db로 보내서 실행하고 난 결과를 돌려받는 클래스임
// 해당 파트를 Business Logic이라고 하고, 해당 과정을 Model이 담당한다.
public class EmployeeDAO {
	public EmployeeDAO(){}
	
	public int insertEmployee(Employee e){
		int result = 0;
		Connection conn = null;
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "student", "student");
			//stmt = conn.createStatement();
//			String query = "insert into employee "
//					+"(EMP_ID, EMP_NAME, EMP_NO, EMAIL, PHONE, HIRE_DATE, JOB_ID"+
//					", SALARY, BONUS_PCT, MARRIAGE, MGR_ID, DEPT_ID) "
//					+"values('"+e.getEmpId()
//					+"', '"+e.getEmpName()
//					+"', '"+e.getEmpNo()
//					+"', '"+e.getEmail()
//					+"', '"+e.getPhone()
//					+"', '"+e.getHireDate()
//					+"', '"+e.getJobId()
//					+"', "+e.getSalary()
//					+", "+e.getBonusPct()
//					+", '"+e.getMarriage()
//					+"', '"+e.getMgrId()
//					+"', '"+e.getDeptId()
//					+"')";
//			result = stmt.executeUpdate(query);
			
			pstmt = conn.prepareStatement("INSERT INTO EMPLOYEE VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, e.getEmpId());
			pstmt.setString(2, e.getEmpName());
			pstmt.setString(3, e.getEmpNo());
			pstmt.setString(4, e.getEmail());
			pstmt.setString(5, e.getPhone());
			pstmt.setDate(6, e.getHireDate());
			pstmt.setString(7, e.getJobId());
			pstmt.setInt(8, e.getSalary());
			pstmt.setDouble(9, e.getBonusPct());
			pstmt.setString(10, e.getMarriage());
			pstmt.setString(11, e.getMgrId());
			pstmt.setString(12, e.getDeptId());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) conn.commit();
			else conn.rollback();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				pstmt.close();
				//stmt.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return result;
	}
//	public int insertEmployee(Employee e){
//		// DB에서 DML 결과를 행의 갯수로 판단하기 때문에
//		// 리턴 타입은 integer
//		int result = 0;
//		Connection conn= null;
//		Statement stmt = null;
//		try {
//			// 1. 오라클에 대한 Driver 클래스 등록
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			// 2. 데이터베이스 연결처리
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
//					"student", "student");
//			stmt = conn.createStatement();
//			/*conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe",
//					"student", "student");*/
//			 // 3. Statement 생성
//			 // 4. SQL전송
//			 // 5. 결과 받기
//			 // 6. 닫기
//		} catch (ClassNotFoundException e1) {
//			//e1.printStackTrace();
//		} catch (SQLException e1) {
//			//e1.printStackTrace();
//		} finally{
//			try {
//				stmt.close();
//				conn.close();
//			} catch (SQLException e1) {
//				//e1.printStackTrace();
//			}
//		}
//		
//		return result;
//	}
	
	public ArrayList<Employee> selectAllEmployee(){
		// 모든 데이터를 결과로 받기 때문에 List나 Map으로 결과를 받아 넘기고
		// 성공 실패의 결과는 null이냐, 아니냐가 됨
		ArrayList<Employee> empList = null;
		// DataBase 연결 과정
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "student", "student");
			stmt = conn.createStatement();
			String query = "SELECT * FROM EMPLOYEE";
			rset = stmt.executeQuery(query);
			
			if (rset != null){
				empList = new ArrayList<Employee>();
				while(rset.next()){
					Employee e = new Employee();
					e.setEmpId(rset.getString("emp_id"));
					 // 대소문자 구분 X
					e.setEmpName(rset.getString("emp_name"));
					e.setEmpNo(rset.getString("emp_no"));
					e.setEmail(rset.getString("email"));
					e.setPhone(rset.getString("phone"));
					e.setHireDate(rset.getDate("hire_date"));
					e.setJobId(rset.getString("job_id"));
					e.setSalary(rset.getInt("salary"));
					e.setBonusPct(rset.getDouble("bonus_pct"));
					e.setMarriage(rset.getString("marriage"));
					e.setMgrId(rset.getString("mgr_id"));
					e.setDeptId(rset.getString("dept_id"));
					empList.add(e);
				}
			}
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return empList;
	}
	
	public Employee selectOne(String empId){
		// 조회 한 개에 대해서는 객체가 return으로 쓰임
		Employee emp = null;
		Connection conn = null;
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "student", "student");
			//stmt = conn.createStatement();
			//String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '"+empId+"'";
			// 작은 따옴표 없어도 되긴 함 ㅎㅎ;;
			//rset = stmt.executeQuery(query);
			pstmt = conn.prepareStatement("SELECT * FROM EMPLOYEE WHERE EMP_ID = ?");
			rset = pstmt.executeQuery();
			
			if (rset != null){
				emp = new Employee();
				while(rset.next()){
					emp.setEmpId(rset.getString("emp_id"));
					 // 대소문자 구분 X
					emp.setEmpName(rset.getString("emp_name"));
					emp.setEmpNo(rset.getString("emp_no"));
					emp.setEmail(rset.getString("email"));
					emp.setPhone(rset.getString("phone"));
					emp.setHireDate(rset.getDate("hire_date"));
					emp.setJobId(rset.getString("job_id"));
					emp.setSalary(rset.getInt("salary"));
					emp.setBonusPct(rset.getDouble("bonus_pct"));
					emp.setMarriage(rset.getString("marriage"));
					emp.setMgrId(rset.getString("mgr_id"));
					emp.setDeptId(rset.getString("dept_id"));
				}
			}
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				//stmt.close();
				conn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return emp;
	}
	
	public int updateEmployee(Employee e){
		int result = 0;
		Connection conn = null;
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "student", "student");
//			stmt = conn.createStatement();
//			String query = "update employee set JOB_ID = '"+e.getJobId()
//					+"', salary = "+e.getSalary()
//					+", BONUS_PCT = "+e.getBonusPct()
//					+", DEPT_ID = '"+e.getDeptId()+"' where emp_id='"+e.getEmpId()+"'";
//			result = stmt.executeUpdate(query);
			pstmt = conn.prepareStatement("UPDATE EMPLOYEE SET JOB_ID = ?, SALARY = ?, BONUS_PCT = ?, DEPT_ID = ? "
					+"WHERE EMP_ID = ?");
			pstmt.setString(1, e.getJobId());
			pstmt.setInt(2, e.getSalary());
			pstmt.setDouble(3, e.getBonusPct());
			pstmt.setString(4, e.getDeptId());
			pstmt.setString(5, e.getEmpId());
			result = pstmt.executeUpdate();
			if(result > 0) conn.commit();
			else conn.rollback();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				pstmt.close();
				//stmt.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int deleteEmployee(String empId){
		int result = 0;
		Connection conn = null;
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "student", "student");
//			stmt = conn.createStatement();
//			String query = "DELETE FROM EMPLOYEE WHERE EMP_ID = '"+empId+"'";
//			result = stmt.executeUpdate(query);
			pstmt = conn.prepareStatement("DELETE FROM EMPLOYEE WHERE EMP_ID = ?");
			pstmt.setString(1, empId);
			result = pstmt.executeUpdate();

			// ------ 트랜잭션 관리 ------ //
			if (result > 0) conn.commit();
			else conn.rollback();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				//stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
