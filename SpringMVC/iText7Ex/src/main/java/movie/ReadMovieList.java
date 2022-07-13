package movie;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import vo.MovieListAPI;
// 국내 전체 영화 목록 읽어서 저장하기
public class ReadMovieList {
	public static void main(String[] args) {
		String address = "https://kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json";
		String APIKey = "f5eef3421c602c6cb7ea224104795888";
		int itemPerPage = 1;
		int curPage=1;
		String urlAddress = String.format("%s?key=%s&itemPerPage=%d&curPage=%d", address, APIKey, itemPerPage, curPage);
		// System.out.println(urlAddress);
		List<MovieListAPI.MovieListResult.MovieList> movieLists = new ArrayList<MovieListAPI.MovieListResult.MovieList>();
		try {
			URL url = new URL(urlAddress);
			InputStreamReader isr = new InputStreamReader(url.openStream(), "UTF-8");
			Gson gson = new Gson();
			MovieListAPI movieListAPI = gson.fromJson(isr, MovieListAPI.class);
			int totalCount = movieListAPI.getMovieListResult().getTotCnt();
			System.out.println("전체 : " + totalCount + "개");
			itemPerPage = 100;
			int numberOfPage = (totalCount-1)/itemPerPage + 1;
			System.out.println("전체페이지 : " + numberOfPage + "페이지");
			for(int i=1;i<=numberOfPage;i++) {
				urlAddress = String.format("%s?key=%s&itemPerPage=%d&curPage=%d", address, APIKey, itemPerPage, i);
				url = new URL(urlAddress);
				isr = new InputStreamReader(url.openStream(), "UTF-8");
				movieListAPI = gson.fromJson(isr, MovieListAPI.class);
				movieLists.addAll(movieListAPI.getMovieListResult().getMovieList());
				System.out.println(i + "페이지 읽음");
			}
			System.out.println(movieLists.size() + "개 읽음");
			// JSON 저장
			PrintWriter pw = new PrintWriter("movieList.json");
			gson.toJson(movieLists, pw);
			pw.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
