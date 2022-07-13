package movie;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import vo.MovieListAPI;
import vo.MovieListAPI.MovieListResult.MovieList;

// 국내 전체 영화 목록 읽어서 저장하기
public class ReadMovieList2 {
	public static void main(String[] args) {
		List<MovieListAPI.MovieListResult.MovieList> movieList2021 = new ArrayList<MovieListAPI.MovieListResult.MovieList>();
		Gson gson = new Gson();
		try {
			FileReader fileReader = new FileReader("movieList.json");
			List<MovieListAPI.MovieListResult.MovieList> movieLists = gson.fromJson(fileReader,
					new TypeToken<List<MovieListAPI.MovieListResult.MovieList>>() {
					}.getType());
			System.out.println(movieLists.size() + "개");
			for (MovieList movieList : movieLists) {
				if (movieList.getPrdtYear().equals("2021")) {
					movieList2021.add(movieList);
				}
			}
			System.out.println(movieList2021.size() + "개");
			// JSON 저장
			PrintWriter pw = new PrintWriter("movieList2021.json");
			gson.toJson(movieList2021, pw);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
