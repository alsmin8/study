package query_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_Study {

	public static void main(String[] args) throws SQLException {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "minn";
		String password = "1234";
		
//		try {
//			Class.forName(driver);
//			System.out.println("jdbc driver 로딩 성공");
//			DriverManager.getConnection(url, user, password);
//			System.out.println("오라클 연결 성공");
//		} catch (ClassNotFoundException e) {
//			System.out.println("jdbc driver 로딩 실패");
//		} catch (SQLException e) {
//			System.out.println("오라클 연결 실패");
//		}
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		String QueryString=null;
		ResultSet rs=null;
		
		String DEPT_NO="";
		String DEPT_NAME="";
		
		int EMP_NO=0;
		String EMP_NAME="";
		String EMP_PASS="";
		
		
		System.out.println("?");
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, password);
		
			QueryString="select ";
			QueryString+="DEPT_NO, DEPT_NAME ";
			QueryString+="FROM DEPT ";
			QueryString+="WHERE DEPT_NO=?";
			
			pstmt=conn.prepareStatement(QueryString);
			
			pstmt.setString(1, "104");
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				DEPT_NO=rs.getString("DEPT_NO");
				DEPT_NAME=rs.getString("DEPT_NAME");

			}
			
			rs.close();
			pstmt.close();
			
			System.out.println("DEPT_NO; "+DEPT_NO+"         , DEPT_NAME: "+DEPT_NAME);
			
			QueryString="select ";
			QueryString+="EMP_NO, EMP_NAME, EMP_PASS, DEPT_NO ";
			QueryString+="FROM EMP ";
			QueryString+="WHERE DEPT_NO=? ";
			
			pstmt=conn.prepareStatement(QueryString); // 미리 준비해둔 쿼리문
			
			pstmt.setString(1, DEPT_NO);
			
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				EMP_NO=rs.getInt("EMP_NO");
				EMP_NAME=rs.getString("EMP_NAME");
				EMP_PASS=rs.getString("EMP_PASS");
				DEPT_NO=rs.getString("DEPT_NO");
				
				
				if (EMP_NO > 0) {
					
					QueryString="UPDATE ";
					QueryString+="EMP ";
					QueryString+="SET ";
					QueryString+="BATCHFLAG = 'Y'";
					
					pstmt=conn.prepareStatement(QueryString);
					
					pstmt.executeUpdate();
					
					System.out.println("[알림]"+EMP_NO+"의 플래그를 Y로 변경.");
					
					pstmt.close();
					
				}else{
					
					QueryString="INSERT INTO ";
					QueryString+="EMP(EMP_NAME)";
					QueryString+="VALUES ";
					QueryString+="?";
					
					pstmt=conn.prepareStatement(QueryString);
					
					pstmt.setString(1, "랜덤인");
					
					pstmt.executeUpdate();
					
					System.out.println("EMP 추가");
					
					pstmt.close();
					
				}
				
				
	
				
				
			}
			
			rs.close();
			pstmt.close();
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
			
			//객체를 얻는 순서 : Connection -> Statement -> ResultSet
			//얻은 순서의 역순으로 객체를 닫아준다: ResultSet -> Statement -> Connection
			
			
		}
		
		
		
		
		
	}
	
}
