package cinema;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Session {

  private Film film;
  private Date dateStart;
  private List<List<Integer>> places;
  // Количество рядов в зале по умолчанию
  private static int DEFAULT_ROWS = 2;
  // Количество кресел в ряду по умолчанию
  private static int DEFAULT_PLACES_IN_ROW = 16;

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

  /***
   * функция возвращает отформатированную строку для более красивого вывода на экран
   * @return
   */
  public String toStringPretty() {
    return "дата сеанса - " + dateStart + "\n\t\t\t\t\t\t"
        + film.toStringPretty()
        ;
  }

  /***
   * Функция возвращает отформатированную строку для записи в файл с использованием разделителя
   * @return
   */
  public String toFile() {
    return film + Constants.SEP +
        Constants.formatter.format(dateStart) + Constants.SEP +
        placesToText(places);
  }

  /***
   * Функция возвращает отформатированную строку для записи в файл с использованием разделителя
   * @param places
   * @return
   */
  public static String placesToText(List<List<Integer>> places) {
    StringBuilder result = new StringBuilder();
    for (List<Integer> row : places) {
      for (Integer integer : row) {
        result.append(integer);
      }
      result.append(Constants.SEP_PLACES);
    }
    result = new StringBuilder(result.substring(0, result.length() - 1));
    return result.toString();
  }

  /***
   * Метод выводит на экран карту свободных занятых мест в сеансе.
   */
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
      for (int free : row) {
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

  /***
   * Функция читает из строки места и возвращает в виде объекта List<List<Integer>>, где занятые
   * места обозначаются как 1, а свободные как 0
   * @param string
   * @return
   */
  private static List<List<Integer>> readPlacesFromStringToListList(String string) {
    String[] arrayString = string.split(Constants.SEP_PLACES);
    List<List<Integer>> places = new ArrayList<>();
    for (int i = 0; i < arrayString.length; i++) {
      String[] rowString = arrayString[i].split("");
      List<Integer> row = new ArrayList<>();
      for (int j = 0; j < rowString.length; j++) {
        row.add(j, Integer.parseInt(rowString[j]));
      }
      places.add(i, row);
    }
    return places;
  }

  /***
   * Функция читает из переданной на вход строки сеанс и возвращает в виде объекта Session
   * @param sessionString
   * @return
   * @throws ParseException
   */
  static Session parseSessionFromString(String sessionString) throws ParseException {
    String[] strAfterSplit = sessionString.split(Constants.SEP);
    Date dateSession = Constants.formatter.parse(strAfterSplit[1]);
    Film film = Constants.cinema.getFilms().get(Integer.parseInt(strAfterSplit[0]));
    return new Session(film, dateSession,
        readPlacesFromStringToListList(strAfterSplit[2]));
  }

  /***
   * Функция читает из сканера входные данные и возвращает объект Session
   * @param scanner
   * @return
   */
  public static Session parseSessionFromScanner(Scanner scanner) {
    String inputMessageDate = "Введите дату и время сеанса в формате (\"dd-MM-yyyy HH:mm\"):";
    System.out.print(inputMessageDate);
    Date sessionDate = new Date();
    boolean successReadData = false;
    while (!successReadData) {
      try {
        sessionDate = Constants.formatter.parse(scanner.nextLine());
        successReadData = true;
      } catch (ParseException p) {
        System.out.println(p);
        System.out.print(inputMessageDate);
      }
    }
    System.out.println("Введите номер фильма:");

    List<Film> filmList = Constants.cinema.getFilms();
    for (int i = 1; i < filmList.size() + 1; i++) {
      System.out.println("# \t" + i + " = " + filmList.get(i - 1));
    }
    int indexFilm = scanner.nextInt() - 1;
    Film film = filmList.get(indexFilm);
    List<List<Integer>> places = getEmptyMapPlaces(DEFAULT_ROWS, DEFAULT_PLACES_IN_ROW);
    return new Session(film, sessionDate, places);
  }

  /***
   * Функция генерирует карту мест зала с предзаполненнными пустыми значениями
   * (т.е. на старте все места свободны)
   * @param rows
   * @param placesInRow
   * @return
   */
  private static List<List<Integer>> getEmptyMapPlaces(int rows, int placesInRow) {
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      List<Integer> row = new ArrayList<>();
      for (int j = 0; j < placesInRow; j++) {
        row.add(0);
      }
      result.add(row);
    }
    return result;
  }
}
