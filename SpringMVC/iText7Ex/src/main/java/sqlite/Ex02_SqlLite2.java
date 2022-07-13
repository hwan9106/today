package sqlite;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
// chunja.txt 파일을 hanja.db의 hanja1테이블에 넣기 
public class Ex02_SqlLite2 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			// SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");
			// SQLite 데이터베이스 파일에 연결
			String dbFile = new File("src/main/resources/database/hanja.db").getAbsolutePath();
			System.out.println(dbFile);
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			// SQL 수행
			String sql = "drop table if exists hanja1";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			sql = "create table hanja1 (idx integer primary key, h text, k text, m text)";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			String fileName = "src/main/resources/chunja.txt";
			List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			sql = "insert into hanja1(h,k,m) values (?,?,?)";
			for(String line : lines) {
				String lineArray[] = line.split("\\|");
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, lineArray[1]);
				pstmt.setString(2, lineArray[2]);
				pstmt.setString(3, lineArray[3]);
				pstmt.executeUpdate();
				pstmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
			} catch (Exception e) {

			}
		}
	}
}
