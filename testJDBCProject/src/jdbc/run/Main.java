package jdbc.run;

import jdbc.mvc.view.JDBCView;

public class Main {
	public static void main(String[] args) {
		// JDBC Test
		new JDBCView().displayMenu();
		System.out.print("\n프로그램을 종료합니다.");
	}
}
