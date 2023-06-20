import java.util.Date;
import java.util.List;

public class Session {

  private Film film;
  private Date dateStart;
  private List<List<Integer>> places;

  public Session(Film film, Date dateStart, List<List<Integer>> places) {
    this.film = film;
    this.dateStart = dateStart;
    this.places = places;
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

  public List<List<Integer>> getPlaces() {
    return places;
  }

  public void setPlaces(List<List<Integer>> places) {
    this.places = places;
  }

  @Override
  public String toString() {
    return "Session{" +
        ", film=" + film +
        ", dateStart=" + dateStart +
        ", places=" + places +
        '}';
  }

  public String toFile() {
    return film + Constants.SEP +
        Constants.formatter.format(dateStart) + Constants.SEP +
        placesToText(places);
  }

  private static String placesToText(List<List<Integer>> places) {
    String result = "";
    for (int i = 0; i < places.size(); i++) {
      List<Integer> row = places.get(i);
      for (int j = 0; j < row.size(); j++) {
        result += row.get(j);
      }
      result += Constants.SEP_PLACES;
    }
    result = result.substring(0, result.length() - 1);
    return result;
  }

  public void printMapPlaces() {
    System.out.println();

    System.out.println("Карта свободных и занятых мест на сеанс:");
    System.out.println(film.toStringPretty());
    System.out.println("Дата сеанса: " + Constants.formatter.format(dateStart));

    int rowSize = 0;
    if (places.size() > 0) {
      rowSize = places.get(0).size();
    }
    System.out.print("     " + ":\t");
    for (
        int i = 0;
        i < rowSize; i++) {
      System.out.print((i + 1) + " \t");
    }
    System.out.println();
    for (
        int i = 0; i < places.size(); i++) {
      System.out.print("Ряд " + (i + 1) + ": \t");
      List<Integer> row = places.get(i);
      for (int j = 0; j < row.size(); j++) {
        int free = row.get(j);
        if (free == 0) {
          System.out.print("R");
        } else {
          System.out.print("F");
        }
        System.out.print(" \t");
      }
      System.out.println();
    }
    System.out.println("+++++++++++++++++++++++++++++++++++++");
  }
}
