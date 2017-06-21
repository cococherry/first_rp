package jdbc.mvc.model.vo;
import java.io.Serializable;
import java.sql.Date;
// [Value Object] 값 기록 저장 용
// DB Table에 한 행의 정보를 저장하는 객체
// DO (Domain Object) == Entity == DTO (Data Transfer Object) 라고도 함
// Java프레임워크에서는 Bean이라고도 함

// 모든 필드는 반드시 Private이어야 한다.
// 기본 생성자와 매개변수가 있는 생성자가 존재해야 한다.
// 모든 필드에 대한 Getter&Setter가 존재해야 한다.
// 반드시 직렬화를 해야한다.(Serializable)

public class Employee implements Serializable{
	//Field Variable
	private String empId;	// 사원번호
	private String empName; // 사원명
	private String empNo;	// 사원 주민번호
	private String email;	// 사원 이메일
	private String phone;	// 전화번호
	private Date hireDate;  // 입사일 : 자바의 sql 패키지에 있는 Date 형과 매칭이 댐
	private String jobId;	// 직급번호
	private int salary;		// 월급
	private double bonusPct;// 보너스
	private String marriage;// 기혼여부
	private String mgrId;	// 관리자사번
	private String deptId;	// 부서번호
	
	// Constructor
	public Employee(){}

	public Employee(String empId, String empName, String empNo, String email, String phone, Date hireDate, String jobId,
			int salary, double bonusPct, String marriage, String mgrId, String deptId) {
		// super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.email = email;
		this.phone = phone;
		this.hireDate = hireDate;
		this.jobId = jobId;
		this.salary = salary;
		this.bonusPct = bonusPct;
		this.marriage = marriage;
		this.mgrId = mgrId;
		this.deptId = deptId;
	}

	// Getter & Setter
	public String getEmpId() { return empId; }
	public String getEmpName() { return empName; }
	public String getEmpNo() { return empNo; }
	public String getEmail() { return email; }
	public String getPhone() { return phone; }
	public Date getHireDate() { return hireDate; }
	public String getJobId() { return jobId; }
	public int getSalary() { return salary; }
	public double getBonusPct() { return bonusPct; }
	public String getMarriage() { return marriage; }
	public String getMgrId() { return mgrId; }
	public String getDeptId() { return deptId; }
	
	public void setEmpId(String empId) { this.empId = empId; }
	public void setEmpName(String empName) { this.empName = empName; }
	public void setEmpNo(String empNo) { this.empNo = empNo; }
	public void setEmail(String email) { this.email = email; }
	public void setPhone(String phone) { this.phone = phone; }
	public void setHireDate(Date hireDate) { this.hireDate = hireDate; }
	public void setJobId(String jobId) { this.jobId = jobId; }
	public void setSalary(int salary) { this.salary = salary; }
	public void setBonusPct(double bonusPct) { this.bonusPct = bonusPct; }
	public void setMarriage(String marriage) { this.marriage = marriage; }
	public void setMgrId(String mgrId) { this.mgrId = mgrId; }
	public void setDeptId(String deptId) { this.deptId = deptId; }

	@Override
	public String toString() {
		return empId+", "+empName+", "+email+", "+phone+", "+hireDate+", "+jobId+", "+salary
				+", "+bonusPct+", "+marriage+", "+mgrId+", "+deptId;
	}	
//	public String toString2() {
//		return empName;
//	}
}