package hu.nye.progkorfilmek.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.nye.progkorfilmek.model.Accessibility;
import hu.nye.progkorfilmek.model.Genre;
import hu.nye.progkorfilmek.model.Movies;
import hu.nye.progkorfilmek.model.exception.NotFoundException;
import hu.nye.progkorfilmek.service.ProgkorFilmekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implements.
 */
@Service
public class ProgkorFilmekServiceImpl implements ProgkorFilmekService {

  private final List<Movies> dataBase = new ArrayList<>();

  @Autowired
  public ProgkorFilmekServiceImpl() {
    dataBase.add(new Movies(1L, "Chihiro_Szellemországban", Genre.FANTASY, Accessibility.NETFLIX));
    dataBase.add(new Movies(2L, "Hét_Szamurály", Genre.ACTION, Accessibility.CD));
    dataBase.add(new Movies(3L, "Páncélba_zárt_szellem", Genre.SCI_FI, Accessibility.DVD));
  }

  public ProgkorFilmekServiceImpl(final List<Movies> movies) {
    dataBase.addAll(movies);
  }

  @Override
  public List<Movies> getAllMovies() {
    return Collections.unmodifiableList(dataBase);
  }

  @Override
  public Movies getMovie(final Long id) {
    return dataBase.stream()
            .filter(movie -> movie.getId().equals(id))
            .findFirst()
            .orElseThrow(NotFoundException::new);
  }

  @Override
  public Movies createMovie(final Movies movies) {
    movies.setId(getNextId());
    dataBase.add(movies);
    return movies;
  }

  @Override
  public Movies updateMovie(final Long id, final Movies moviesChange) {
    final Movies movies = getMovie(id);
    movies.setName(moviesChange.getName());
    movies.setGenre(moviesChange.getGenre());
    movies.setAccessibility(moviesChange.getAccessibility());
    return movies;
  }

  @Override
  public void deleteMovie(final Long id) {
    final Movies movies = getMovie(id);
    dataBase.remove(movies);
  }

  private long getNextId() {
    return getLastId() + 1L;
  }

  private long getLastId() {
    return dataBase.stream()
            .mapToLong(Movies::getId)
            .max()
            .orElse(0);
  }
}
