package hu.nye.progkorfilmek.controller;

import java.util.List;

import hu.nye.progkorfilmek.model.Movies;
import hu.nye.progkorfilmek.model.exception.NotFoundException;
import hu.nye.progkorfilmek.service.ProgkorFilmekService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class rest.
 */
@RestController
@RequestMapping("/api/v1/catalog")
public class ProgkorFilmekRestController {

  private final ProgkorFilmekService progkorFilmekService;

  public ProgkorFilmekRestController(final ProgkorFilmekService progkorFilmekService) {
    this.progkorFilmekService = progkorFilmekService;
  }

  /**
   * get All Movies.
   */
  @GetMapping
  public List<Movies> getAllMovies() {
    return progkorFilmekService.getAllMovies();
  }

  /**
   * get Movie ID.
   */
  @GetMapping("/{id}")
  Movies getMovie(final @PathVariable Long id) {
    return progkorFilmekService.getMovie(id);
  }

  /**
   * post Created Movie.
   */
  @PostMapping
  Movies createMovie(final @RequestBody Movies movies) {
    return progkorFilmekService.createMovie(movies);
  }

  /**
   * put Movie.
   */
  @PutMapping("/{id}")
  Movies updateMovie(final @PathVariable Long id, final @RequestBody Movies moviesChange) {
    return progkorFilmekService.updateMovie(id, moviesChange);
  }

  /**
   * delete Movie.
   */
  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteMovie(final @PathVariable Long id) {
    try {
      progkorFilmekService.deleteMovie(id);
    } catch (NotFoundException e) {
      // Ignored
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
