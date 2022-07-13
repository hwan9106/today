package vo;

import java.util.List;

import lombok.Data;

// https://www.kobis.or.kr/kobisopenapi/homepg/apiservice/searchServiceInfo.do 참조
@Data
public class MovieListAPI {
	private MovieListResult movieListResult;

	@Data
	public class MovieListResult {
		private int totCnt;
		private String source;
		private List<MovieList> movieList;

		@Data
		public class MovieList {
			private String movieCd;
			private String movieNm;
			private String movieNmEn;
			private String prdtYear;
			private String openDt;
			private String typeNm;
			private String prdtStatNm;
			private String nationAlt;
			private String genreAlt;
			private String repNationNm;
			private String repGenreNm;
			private List<Director> directors;
			private List<Company> companys;
		}

		@Data
		public class Director {
			private String peopleNm;
		}

		@Data
		public class Company {
			private String companyCd;
			private String companyNm;
		}
	}
}