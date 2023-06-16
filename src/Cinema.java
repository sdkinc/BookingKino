import java.util.Date;
import java.util.List;
import java.util.Map;

public class Cinema {

  private String name;
  private List<Hall> hall;
  private Map<Date, Film> films;

  public Cinema(String name, List<Hall> hall, Map<Date, Film> films) {
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

  public Map<Date, Film> getFilms() {
    return films;
  }

  public void setFilms(Map<Date, Film> films) {
    this.films = films;
  }
}
