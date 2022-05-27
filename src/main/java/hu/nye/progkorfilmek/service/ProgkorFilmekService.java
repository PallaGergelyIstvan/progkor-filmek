package hu.nye.progkorfilmek.service;

import java.util.List;

import hu.nye.progkorfilmek.model.Movies;

/**
 * Service interface.
 */
public interface ProgkorFilmekService {

  List<Movies> getAllMovies();

  Movies getMovie(Long id);

  Movies createMovie(Movies movies);

  Movies updateMovie(Long id, Movies moviesChange);

  void deleteMovie(Long id);
}
