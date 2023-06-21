package cinema;

import java.util.List;

public class Cinema {

  private String name;
  private List<Hall> hall;
  private List<Film> films;

  public Cinema(String name, List<Hall> hall, List<Film> films) {
    this.name = name;
    this.hall = hall;
    this.films = films;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Hall> getCinemaHall() {
    return hall;
  }

  public void setCinemaHall(List<Hall> hall) {
    this.hall = hall;
  }

  public List<Film> getFilms() {
    return films;
  }

  public void setFilms(List<Film> films) {
    this.films = films;
  }

}
