package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cinema.Constants;
import cinema.Film;
import org.junit.jupiter.api.Test;

public class FilmTests {
  @Test
  public void testConstructor() {
    // arrange
    String name = "Вий";
    String producer = "Олександр Роу";
    String genre = "Фантастика/Комедія";
    int lengthInMin = 69;

    // arrange & act
    Film film = new Film(name,producer,genre,lengthInMin);
    // assert
    assertEquals(name, film.getName());
    assertEquals(producer, film.getProducer());
    assertEquals(genre, film.getGenre());
    assertEquals(lengthInMin, film.getLengthInMin());
  }
  @Test
  public void testToString(){
    // arrange
    String name = "Вий";
    String producer = "Олександр Роу";
    String genre = "Фантастика/Комедія";
    int lengthInMin = 69;
    String expected = "Film{" +
        "name='" + name + '\'' +
        ", producer='" + producer + '\'' +
        ", genre='" + genre + '\'' +
        ", lengthInMin=" + lengthInMin +
        '}';
    // arrange & act
    Film film = new Film(name,producer,genre,lengthInMin);

    // assert
    assertEquals(expected, film.toString());
  }

  @Test
  public void testToStringPretty(){
    // arrange
    String name = "Вий";
    String producer = "Олександр Роу";
    String genre = "Фантастика/Комедія";
    int lengthInMin = 69;
    String expected = "Название фильма - " + name +
        ", режиссер - " + producer +
        ", жанр - " + genre +
        ", длительность: " + lengthInMin + " мин";
    // arrange & act
    Film film = new Film(name,producer,genre,lengthInMin);

    // assert
    assertEquals(expected, film.toStringPretty());
  }

  @Test
  public void testToFile(){
    // arrange
    String name = "Вий";
    String producer = "Олександр Роу";
    String genre = "Фантастика/Комедія";
    int lengthInMin = 69;
    String sep = Constants.SEP;

    String expected = name + sep + producer + sep + genre + sep + lengthInMin;

    // arrange & act
    Film film = new Film(name,producer,genre,lengthInMin);

    // assert
    assertEquals(expected, film.toFile());
  }
}
