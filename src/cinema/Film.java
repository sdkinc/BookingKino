package cinema;

import java.util.Scanner;

public class Film {

  private String name;
  private String producer;
  private String genre;
  private int lengthInMin;

  public Film(String name, int lengthInMin) {
    this.name = name;
    this.lengthInMin = lengthInMin;
  }

  public Film(String name, String producer, String genre, int lengthInMin) {
    this.name = name;
    this.producer = producer;
    this.genre = genre;
    this.lengthInMin = lengthInMin;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getProducer() {
    return producer;
  }

  public void setProducer(String producer) {
    this.producer = producer;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public int getLengthInMin() {
    return lengthInMin;
  }

  public void setLengthInMin(int lengthInMin) {
    this.lengthInMin = lengthInMin;
  }

  @Override
  public String toString() {
    return "Film{" +
        "name='" + name + '\'' +
        ", producer='" + producer + '\'' +
        ", genre='" + genre + '\'' +
        ", lengthInMin=" + lengthInMin +
        '}';
  }

  /***
   * функция возвращает отформатированную строку для более красивого вывода на экран
   * @return
   */
  public String toStringPretty() {
    return "Название фильма - " + name +
        ", режиссер - " + producer +
        ", жанр - " + genre +
        ", длительность: " + lengthInMin + " мин";
  }

  /***
   * Функция возвращает отформатированную строку для записи в файл с использованием разделителя
   * @return
   */
  public String toFile() {
    String sep = Constants.SEP;
    return name + sep + producer + sep + genre + sep + lengthInMin;
  }

  /***
   * Функция разбирает прочитанную из файла строку, и возвращает объект типа Film
   * @param filmString
   * @return
   */
  public static Film parseFilmFromString(String filmString) {
    String[] strAfterSplit = filmString.split(Constants.SEP);
    return new Film(strAfterSplit[0], strAfterSplit[1], strAfterSplit[2],
        Integer.parseInt(strAfterSplit[3]));
  }

  /***
   * Функция работая со Scanner получает на вход форматированные данные от пользователя,
   * и возвращает объект типа Film
   * @return
   */
  public static Film parseFilmFromScanner() {
    Scanner scanner = new Scanner((System.in));
    System.out.print("Введите наименование фильма:");
    String nameFilm = scanner.nextLine();
    System.out.print("Введите длительность фильма:");
    int lengthFilm = Integer.parseInt(scanner.nextLine());
    System.out.print("Введите жанр фильма:");
    String genreFilm = scanner.nextLine();
    System.out.print("Введите режиссера фильма:");
    String producerFilm = scanner.nextLine();
    return new Film(nameFilm, producerFilm, genreFilm, lengthFilm);
  }
}
