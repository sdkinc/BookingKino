package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cinema.Constants;
import cinema.Cinema;
import cinema.Film;
import cinema.Hall;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CinemaTests {

  @Test
  public void testConstructor() {
    // arrange
    String nameFilm = "Вий";
    String producer = "Олександр Роу";
    String genre = "Фантастика/Комедія";
    int lengthInMin = 69;
    Film film = new Film(nameFilm,producer,genre,lengthInMin);
    List<Film> filmList = new ArrayList<>();
    filmList.add(film);
    String nameHall = "RED";
    int price = 50;
    Hall hall = new Hall(nameHall,price);
    List<Hall> hallList = new ArrayList<>();
    hallList.add(hall);
    String nameCinema = Constants.NAME_CINEMA;
    //act
    Cinema actual = new Cinema(nameCinema,hallList,filmList);
    //arrange
    assertEquals(nameCinema,actual.getName());
    assertEquals(hallList,actual.getCinemaHall());
    assertEquals(filmList,actual.getFilms());
  }

}
