package cinema;

import java.text.SimpleDateFormat;

public class Constants {
  //formatter используется в разных классах, для приведения даты к единому формату
  // при чтении и записи в файл из файла
  public static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
  //SEP разделитель колонок в строках с данными в файлах
  public final static String SEP = ";";
  //SEP_PLACES разделитель рядов в поле с данными по местам в файле
  public final static String SEP_PLACES = "\\|";
  public final static String SEP_PLACES_FOR_WRITE = "|";
  //FILMS_FILENAME путь к файлу с фильмами
  public static final String FILMS_FILENAME = "res/FilmList.csv";
  //SESSIONS_FILENAME путь к файлу с сеансами
  public static final String SESSIONS_FILENAME = "res/SessionList.csv";
  public static Cinema cinema = null;
  //NAME_CINEMA наименование кинотеатра
  public static final String NAME_CINEMA = "Cinemax AIT entertainment";
}
