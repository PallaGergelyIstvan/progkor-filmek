package hu.nye.progkorfilmek;

import hu.nye.progkorfilmek.model.Accessibility;
import hu.nye.progkorfilmek.model.Genre;
import hu.nye.progkorfilmek.model.Movies;
import hu.nye.progkorfilmek.model.exception.NotFoundException;
import hu.nye.progkorfilmek.service.ProgkorFilmekService;
import hu.nye.progkorfilmek.service.impl.ProgkorFilmekServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProgkorFilmekServiceImplTest {

	private static final Movies CHIHIRO_SZELLEMORSZÁGBAN_MOVIE = new Movies(1L, "Chihiro_Szellemországban", Genre.FANTASY, Accessibility.NETFLIX);
	private static final Movies Hét_SZAMURÁLY_MOVIE = new Movies(2L, "Hét_Szamurály", Genre.ACTION, Accessibility.CD);
	private static final Movies PÁNCÉLBA_ZÁRT_SZELLEM_MOVIE = new Movies(3L, "Páncélba_zárt_szellem", Genre.SCI_FI, Accessibility.DVD);

	private static final List<Movies> MOVIES = List.of(
			CHIHIRO_SZELLEMORSZÁGBAN_MOVIE,
			Hét_SZAMURÁLY_MOVIE,
			PÁNCÉLBA_ZÁRT_SZELLEM_MOVIE
	);
	public static final long UNKNOWN_MOVIE_ID = -1L;
	public static final String SNATCH_NAME = "Snatch";

	private ProgkorFilmekService underTest;

	@BeforeEach
	void setUp() {
		underTest = new ProgkorFilmekServiceImpl(MOVIES);
	}

	@Test
	void getAllMoviesShouldReturnAllMovies() {
		// when
		final List<Movies> actual = underTest.getAllMovies();
		// then
		assertThat(actual).isEqualTo(MOVIES);
	}

	@Test
	void getMovieShouldReturnMovieWhenGivenIdOfExistingMovie() {
		// when
		final Movies actual = underTest.getMovie(CHIHIRO_SZELLEMORSZÁGBAN_MOVIE.getId());
		// then
		assertThat(actual).isEqualTo(CHIHIRO_SZELLEMORSZÁGBAN_MOVIE);
	}

	@Test
	void getMovieShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingMovie() {
		// when then
		assertThrows(NotFoundException.class, () -> underTest.getMovie(UNKNOWN_MOVIE_ID));
	}

	@Test
	void createMovieShouldReturnMovieWhenDelegateIt() {
		// given
		final Movies snatchMovie = new Movies(null, SNATCH_NAME, Genre.ACTION, Accessibility.NETFLIX);
		final Movies expectedSnatchMovie = new Movies(4L, SNATCH_NAME, Genre.ACTION, Accessibility.NETFLIX);
		// when
		final Movies actual = underTest.createMovie(snatchMovie);
		// then
		assertThat(actual).isEqualTo(expectedSnatchMovie);
	}

	@Test
	void updateMovieShouldReturnUpdatedMovieWhenGivenIdOfExistingMovie() {
		// given
		final Movies snatchMovie = new Movies(null, SNATCH_NAME, Genre.ACTION, Accessibility.NETFLIX);
		final Movies expectedMovie = new Movies(PÁNCÉLBA_ZÁRT_SZELLEM_MOVIE.getId(), SNATCH_NAME, Genre.ACTION, Accessibility.NETFLIX);
		// when
		final Movies actual = underTest.updateMovie(PÁNCÉLBA_ZÁRT_SZELLEM_MOVIE.getId(), snatchMovie);
		// then
		assertThat(actual).isEqualTo(expectedMovie);
	}

	@Test
	void updateMovieShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingMovie() {
		// given
		final Movies snatchMovie = new Movies(null, SNATCH_NAME, Genre.ACTION, Accessibility.NETFLIX);
		// when then
		assertThrows(NotFoundException.class, () -> underTest.updateMovie(UNKNOWN_MOVIE_ID, snatchMovie));
	}

	@Test
	void deleteMovieShouldDeleteMovieWhenGivenIdOfMovie() {
		// given
		final List<Movies> expectedMovies = List.of(CHIHIRO_SZELLEMORSZÁGBAN_MOVIE);
		// when
		underTest.deleteMovie(Hét_SZAMURÁLY_MOVIE.getId());
		underTest.deleteMovie(PÁNCÉLBA_ZÁRT_SZELLEM_MOVIE.getId());
		final List<Movies> actual = underTest.getAllMovies();
		// then
		assertThat(actual).isEqualTo(expectedMovies);
	}
}