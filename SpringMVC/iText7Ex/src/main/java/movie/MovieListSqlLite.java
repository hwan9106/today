package movie;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import vo.MovieListAPI;
import vo.MovieListAPI.MovieListResult.Company;
import vo.MovieListAPI.MovieListResult.Director;
import vo.MovieListAPI.MovieListResult.MovieList;
// movieList2021.json을 읽어 DB에 넣기
public class MovieListSqlLite {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			// SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");
			// SQLite 데이터베이스 파일에 연결
			String dbFile = new File("src/main/resources/movielist.db").getAbsolutePath();
			System.out.println(dbFile);
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			// SQL 수행
			String sql = "drop table if exists movielist2021";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			sql = "create table movielist2021 (idx integer primary key,"
					+ " movieCd text," 
					+ " movieNm text," 
					+ " movieNmEn text," 
					+ " prdtYear text," 
					+ " openDt text," 
					+ " typeNm text," 
					+ " prdtStatNm text," 
					+ " nationAlt text," 
					+ " genreAlt text," 
					+ " repNationNm text," 
					+ " repGenreNm text," 
					+ " directors text," 
					+ " companys text" 
					+ ")";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			Gson gson = new Gson();
			FileReader fileReader = new FileReader("movieList2021.json");
			List<MovieListAPI.MovieListResult.MovieList> movieLists = gson.fromJson(fileReader,
					new TypeToken<List<MovieListAPI.MovieListResult.MovieList>>() {
					}.getType());
			
			sql = "insert into movielist2021(movieCd,movieNm,movieNmEn,prdtYear,openDt,typeNm,"
				  + "prdtStatNm,nationAlt,genreAlt,repNationNm,repGenreNm,directors,companys"	
				  + ") values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			int count = 0;
			for (MovieList movieList : movieLists) {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, movieList.getMovieCd());
				pstmt.setString(2, movieList.getMovieNm());
				pstmt.setString(3, movieList.getMovieNmEn());
				pstmt.setString(4, movieList.getPrdtYear());
				pstmt.setString(5, movieList.getOpenDt());
				pstmt.setString(6, movieList.getTypeNm());
				pstmt.setString(7, movieList.getPrdtStatNm());
				pstmt.setString(8, movieList.getNationAlt());
				pstmt.setString(9, movieList.getGenreAlt());
				pstmt.setString(10, movieList.getRepNationNm());
				pstmt.setString(11, movieList.getRepGenreNm());
				
				StringBuffer sb = new StringBuffer();
				for(Director director : movieList.getDirectors()) {
					sb.append(director.getPeopleNm() + "|");
				}
				String result = sb.toString().trim();
				if(result.length()>0) result = result.substring(0, result.length()-1);
				pstmt.setString(12, result);
				
				sb = new StringBuffer();
				for(Company company : movieList.getCompanys()) {
					sb.append(company.getCompanyCd() + "-" + company.getCompanyNm()+ "|");
				}
				result = sb.toString().trim();
				if(result.length()>0) result = result.substring(0, result.length()-1);
				pstmt.setString(13, result);

				pstmt.executeUpdate();
				pstmt.close();
				System.out.println(++count + "번째 입력 성공");
			}
			System.out.println("전체 : " + movieLists.size() + "개");
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
