package cinema;

import java.text.SimpleDateFormat;

public class Constants {

  public static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
  public final static String SEP = ";";
  public final static String SEP_PLACES = "\\|";
  public static final String FILMS_FILENAME = "res/FilmList.csv";
  public static final String SESSIONS_FILENAME = "res/SessionList.csv";
  public static Cinema cinema = null;
  public static final String NAME_CINEMA = "Cinemax AIT entertainment";
}
