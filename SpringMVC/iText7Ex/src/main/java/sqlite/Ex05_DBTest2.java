package sqlite;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import vo.HanjaVO2;

public class Ex05_DBTest2 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");
			// SQLite 데이터베이스 파일에 연결
			String dbFile = new File("src/main/resources/database/hanja.db").getAbsolutePath();
			System.out.println(dbFile);
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			// SQL 수행
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM hanja2");
			List<HanjaVO2> list = new ArrayList<HanjaVO2>();
			while (rs.next()) {
				HanjaVO2 vo = new HanjaVO2();
				vo.setIdx(rs.getInt("idx"));
				vo.setH(rs.getString("h"));
				vo.setK(rs.getString("k"));
				vo.setM(rs.getString("m"));
				vo.setM1(rs.getString("m1"));
				list.add(vo);
			}
			System.out.println(list.size() + "개 읽음");
			// JSON으로 저장
			Gson gson = new Gson();
			PrintWriter pw = new PrintWriter("src/main/resources/hanja2.json");
			gson.toJson(list, pw);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {

			}
		}
	}
}
