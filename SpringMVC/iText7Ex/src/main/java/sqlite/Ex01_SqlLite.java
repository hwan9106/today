package sqlite;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
// 창세기 읽기
public class Ex01_SqlLite {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			// SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");
			// SQLite 데이터베이스 파일에 연결
			String dbFile = new File("src/main/resources/database/bible.db").getAbsolutePath();
			System.out.println(dbFile);
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			// SQL 수행
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM bible where bookid=1");
			while (rs.next()) {
				int chapter = rs.getInt("chapter");
				int subchap = rs.getInt("subchap");
				String content = rs.getString("content");
				System.out.println(chapter + "-" + subchap + " " + content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {

			}
		}
	}

}
