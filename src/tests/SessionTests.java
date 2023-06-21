package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cinema.Constants;
import cinema.Film;
import cinema.Session;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;


public class SessionTests {

  @Test
  public void testConstructor() throws ParseException {
    // arrange
    String name = "Вий";
    String producer = "Олександр Роу";
    String genre = "Фантастика/Комедія";
    int lengthInMin = 69;
    Film film = new Film(name, producer, genre, lengthInMin);
    Date dateStart = Constants.formatter.parse("24-06-2023 11:00");
    int rows = 2;
    int placesInRow = 16;
    List<List<Integer>> places = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      List<Integer> row = new ArrayList<>();
      for (int j = 0; j < placesInRow; j++) {
        row.add(0);
      }
      places.add(row);
    }

    // arrange & act
    Session session = new Session(film, dateStart, places);
    // assert
    assertEquals(dateStart, session.getDateStart());
    assertEquals(places, session.getPlaces());
    assertEquals(film, session.getFilm());
  }

  @Test
  public void testToString() throws ParseException {
    //arrange
    String name = "Вий";
    String producer = "Олександр Роу";
    String genre = "Фантастика/Комедія";
    int lengthInMin = 69;
    Film film = new Film(name, producer, genre, lengthInMin);
    Date dateStart = Constants.formatter.parse("24-06-2023 11:00");
    int rows = 2;
    int placesInRow = 16;
    List<List<Integer>> places = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      List<Integer> row = new ArrayList<>();
      for (int j = 0; j < placesInRow; j++) {
        row.add(0);
      }
      places.add(row);
    }

    String expected = "Session{" +
        ", film=" + film +
        ", dateStart=" + dateStart +
        ", places=" + places +
        '}';
    // arrange & act
    Session session = new Session(film, dateStart, places);
    //assert
    assertEquals(expected, session.toString());
  }

  @Test
  public void testToStringPretty() throws ParseException {
    //arrange
    String name = "Вий";
    String producer = "Олександр Роу";
    String genre = "Фантастика/Комедія";
    int lengthInMin = 69;
    Film film = new Film(name, producer, genre, lengthInMin);
    Date dateStart = Constants.formatter.parse("24-06-2023 11:00");
    int rows = 2;
    int placesInRow = 16;
    List<List<Integer>> places = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      List<Integer> row = new ArrayList<>();
      for (int j = 0; j < placesInRow; j++) {
        row.add(0);
      }
      places.add(row);
    }

    String expected = "дата сеанса - " + dateStart + "\n\t\t\t\t\t\t"
        + film.toStringPretty();
    // arrange & act
    Session session = new Session(film, dateStart, places);
    //assert
    assertEquals(expected, session.toStringPretty());
  }

  @Test
  public void testToFile() throws ParseException {
    //arrange
    String name = "Вий";
    String producer = "Олександр Роу";
    String genre = "Фантастика/Комедія";
    int lengthInMin = 69;
    Film film = new Film(name, producer, genre, lengthInMin);
    Date dateStart = Constants.formatter.parse("24-06-2023 11:00");
    int rows = 2;
    int placesInRow = 16;
    List<List<Integer>> places = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      List<Integer> row = new ArrayList<>();
      for (int j = 0; j < placesInRow; j++) {
        row.add(0);
      }
      places.add(row);
    }

    String sep = Constants.SEP;
    String expected = film + Constants.SEP +
        Constants.formatter.format(dateStart) + Constants.SEP +
        Session.placesToText(places);
    // arrange & act
    Session session = new Session(film, dateStart, places);
    //assert
    assertEquals(expected, session.toFile());
  }
}
