import java.util.Date;
import java.util.Map;

public class Session {

  private String timeSession;
  private Film film;
  private Date dateStart;
  private Map<String, Boolean> places;

  public Session(String timeSession, Film film, Date dateStart, Map<String, Boolean> places) {
    this.timeSession = timeSession;
    this.film = film;
    this.dateStart = dateStart;
    this.places = places;
  }

  public String getTimeSession() {
    return timeSession;
  }

  public void setTimeSession(String timeSession) {
    this.timeSession = timeSession;
  }

  public Film getFilm() {
    return film;
  }

  public void setFilm(Film film) {
    this.film = film;
  }

  public Date getDateStart() {
    return dateStart;
  }

  public void setDateStart(Date dateStart) {
    this.dateStart = dateStart;
  }

  public Map<String, Boolean> getPlaces() {
    return places;
  }

  public void setPlaces(Map<String, Boolean> places) {
    this.places = places;
  }
}
