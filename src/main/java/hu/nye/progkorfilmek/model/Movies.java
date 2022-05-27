package hu.nye.progkorfilmek.model;

import java.util.Objects;

/**
 * Movie Model Class.
 */
public class Movies {

  private Long id;
  private String name;
  private Genre genre;
  private Accessibility accessibility;

  public Movies() {
  }

  /**
   * Movie Constructor.
   */
  public Movies(final Long id,
                final String name,
                final Genre genre,
                final Accessibility accessibility) {
    this.id = id;
    this.name = name;
    this.genre = genre;
    this.accessibility = accessibility;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(final Genre genre) {
    this.genre = genre;
  }

  public Accessibility getAccessibility() {
    return accessibility;
  }

  public void setAccessibility(final Accessibility accessibility) {
    this.accessibility = accessibility;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Movies)) {
      return false;
    }
    final Movies movies = (Movies) o;
    return Objects.equals(id, movies.id)
            && Objects.equals(name, movies.name)
            && genre == movies.genre
            && accessibility == movies.accessibility;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, genre, accessibility);
  }

  @Override
  public String toString() {
    return "Movies{"
            + "id=" + id
            + ", name='" + name + '\''
            + ", genre=" + genre
            + ", accessibility=" + accessibility
            + '}';
  }
}
