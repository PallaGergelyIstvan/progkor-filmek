package hu.nye.progkorfilmek.controller;

import java.util.List;

import hu.nye.progkorfilmek.model.Movies;
import hu.nye.progkorfilmek.model.exception.NotFoundException;
import hu.nye.progkorfilmek.service.ProgkorFilmekService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller class.
 */
@Controller
@RequestMapping("/catalog")
public class ProgkorFilmekController {

  private static final String MOVIES_LIST_TEMPLATE_NAME = "catalog/list";
  private static final String MOVIES_EDIT_TEMPLATE_NAME = "catalog/edit";
  private static final String MOVIES_ATTRIBUTE_NAME = "movies";

  private final ProgkorFilmekService progkorFilmekService;

  public ProgkorFilmekController(final ProgkorFilmekService progkorFilmekService) {
    this.progkorFilmekService = progkorFilmekService;
  }

  /**
   * get All Movie.
   */
  @GetMapping
  public String getAllMovie(final Model model) {
    final List<Movies> movies = progkorFilmekService.getAllMovies();
    model.addAttribute("movies", movies);
    return MOVIES_LIST_TEMPLATE_NAME;
  }

  /**
   * get Movie ID.
   */
  @GetMapping("/{id}")
  public String getMovie(final Model model, final @PathVariable Long id) {
    final Movies movies = progkorFilmekService.getMovie(id);
    model.addAttribute(MOVIES_ATTRIBUTE_NAME, movies);
    return MOVIES_EDIT_TEMPLATE_NAME;
  }

  /**
   * post Update Movie.
   */
  @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String updateMovie(final Model model,
                            final @RequestParam(value = "id", required = false) Long id,
                            final Movies moviesChanges) {
    final Movies movies = progkorFilmekService.updateMovie(id, moviesChanges);
    model.addAttribute(MOVIES_ATTRIBUTE_NAME, movies);
    return MOVIES_EDIT_TEMPLATE_NAME;
  }

  /**
   * get Create Movie.
   */
  @GetMapping("/create")
  public String createMovieForm(final Model model) {
    return "catalog/create";
  }

  /**
   * post Create Movie.
   */
  @PostMapping("/create")
  public String createMovie(final Model model, final Movies movies) {
    final Movies savedMovies = progkorFilmekService.createMovie(movies);
    model.addAttribute(MOVIES_ATTRIBUTE_NAME, savedMovies);
    return MOVIES_EDIT_TEMPLATE_NAME;
  }

  /**
   * Delete Movie.
   */
  @GetMapping("/{id}/delete")
  public String deleteMovie(final Model model, final @PathVariable("id") Long id) {
    try {
      progkorFilmekService.deleteMovie(id);
    } catch (NotFoundException e) {
      // Ignored
    }
    final List<Movies> movies = progkorFilmekService.getAllMovies();
    model.addAttribute("movies", movies);
    return MOVIES_LIST_TEMPLATE_NAME;
  }
}
