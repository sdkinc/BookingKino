package cinema;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Files {
  /***
   * Метод получает на вход объект типа Film и дописывает его в файл.
   * @param film
   * @throws IOException
   */
  static void writeObjectToFile(Film film) throws IOException {
    List<Film> listFilmFromFile = readFilmsFromFile();
    FileWriter fileWriter = new FileWriter(Constants.FILMS_FILENAME);
    for (Film f : listFilmFromFile) {
      fileWriter.write(f.toFile() + "\n");
    }
    fileWriter.write(film.toFile());
    fileWriter.close();
  }
  /***
   * Метод получает на вход объект типа Session и дописывает его в файл.
   * @param session
   * @throws IOException
   */
  static void writeObjectToFile(Session session) throws IOException, ParseException {
    List<Session> listFromFile = readSessionsFromFile();
    FileWriter fileWriter = new FileWriter(Constants.SESSIONS_FILENAME);
    for (Session s : listFromFile) {
      fileWriter.write(s.toFile() + "\n");
    }
    fileWriter.write(session.toFile());
    fileWriter.close();
  }

  /***
   * Функция читает сеансы из файла в объект типа List<Session>
   * @return возвращает List<Session>
   * @throws IOException
   * @throws ParseException
   */
  public static List<Session> readSessionsFromFile() throws IOException, ParseException {
    Scanner scanner = new Scanner(new FileReader(Constants.SESSIONS_FILENAME));
    checkExistFile(Constants.SESSIONS_FILENAME);
    List<Session> result = new ArrayList<>();
    while (scanner.hasNextLine()) {
      result.add(Session.parseSessionFromString(scanner.nextLine()));
    }
    return result;
  }

  /***
   * Функция читает фильмы из файла в объект типа List<Film>
   * @return возвращает List<Film>
   * @throws IOException
   */
  static List<Film> readFilmsFromFile() throws IOException {
    Scanner scanner = new Scanner(new FileReader(Constants.FILMS_FILENAME));
    checkExistFile(Constants.FILMS_FILENAME);
    List<Film> result = new ArrayList<>();
    while (scanner.hasNextLine()) {
      result.add(Film.parseFilmFromString(scanner.nextLine()));
    }
    return result;
  }

  /***
   * Метод проверяет существует ли файл, и если не существует, то создает новый и записывает его.
   * @param filename
   * @throws IOException
   */
  private static void checkExistFile(String filename) throws IOException {
    File file = new File(filename);
    if (!file.exists()) {
      FileWriter fileWriter = new FileWriter(filename);
      fileWriter.close();
    }
  }

  /***
   * Функция читает фильмы из файла в объект типа ArrayList<Hall>
   * @return объект ArrayList<Hall>
   * @throws IOException
   */
  static ArrayList<Hall> readHallFromFile() throws IOException {
    String filename = "res/HallList.csv";
    Scanner scanner = new Scanner(new FileReader(filename));
    checkExistFile(filename);
    ArrayList<Hall> result = new ArrayList<>();
    while (scanner.hasNextLine()) {
      result.add(Hall.parseHallFromString(scanner.nextLine()));
    }
    return result;
  }

  /***
   * Функция создает кинотеатр читая из файла список залов и список фильмов
   * @return объект типа Cinema
   * @throws IOException
   */
  public static Cinema readCinemaFromFile() throws IOException {
    return new Cinema(Constants.NAME_CINEMA, Files.readHallFromFile(),
        Files.readFilmsFromFile());
  }
}
