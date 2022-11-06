import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBA {
	private Connection conn;
	private static String dburl= "jdbc:mysql://localhost/testdb";;
	private static String dbUser= "testjju";
	private static String dbpw="1234";
	
	public DBA() {
		try {
			System.out.println("db로딩중");
			conn=DriverManager.getConnection(dburl, dbUser, dbpw);
		}catch(Exception e) {
			System.out.println("db로딩 실패");
		}
		try {
			conn.close();
		}catch(SQLException e) {}
	}
	
	public void insertData(int id,String name, String depart, String rank, int halfway, int reward,int point) {
		String sql="insert into board values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt=null;
		
		try {
			pstmt.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, depart);
			pstmt.setString(4, rank);
			pstmt.setInt(5, halfway);
			pstmt.setInt(6, reward);
			pstmt.setInt(7, point);
			
			int result= pstmt.executeUpdate();
			if(result==1)
				System.out.println("insert complete");
		}catch(Exception e) {
			System.out.println("insert failed");
		}finally {
			try {
				if(pstmt!=null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch (Exception e) {}
		}
	}
	public void updateData(int id) {
		String sql="update user set 부서=?, 직급=?, 반차=?, 상벌점=?";
		PreparedStatement pstmt=null;
		//폼에서 데이터 받아오기
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, ""/*부서 들어갈곳*/);
			pstmt.setString(2, ""/*직급 들어갈곳*/);
			pstmt.setInt(3, 0/*숫자(반차) 들어갈곳*/);
			pstmt.setInt(4, 0/*숫자(상벌점) 들어갈곳*/);
		}catch(Exception e) {
			System.out.println("수정 실패");
		}finally {
			try {
				if(pstmt!=null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch (Exception e) {}
		}
	}
	public void deleteData(int id) {
	      String sql="delete from test where 사원번호=?";
	      PreparedStatement pstmt=null;

	      try {
	         pstmt.getConnection().prepareStatement(sql);
	         pstmt.setInt(1,id);
	         pstmt.executeUpdate();
	      } catch (SQLException e) {
	         throw new RuntimeException(e);
	      }
	   }
}