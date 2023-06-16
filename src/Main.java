import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

  private final static String SEP = ";";
  private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

  public static void main(String[] args) throws IOException {
    Cinema cinema = readCinemaFromFile();
  }

  private static void fillSessionList(Hall hall, Film film) {
    Map<Date, Session> sessions = new HashMap<>();
    Map<String, Boolean> places = new HashMap<>();
    places.put("01|01", true);
    sessions.put(
        java.sql.Date.valueOf(LocalDate.now()),
        new Session("11.00", film, java.sql.Date.valueOf(LocalDate.now()), places));
    hall.setSessions(sessions);
  }

  private static Cinema readCinemaFromFile() throws IOException {
    Cinema result = new Cinema("Cinemax AIT entertainment", readHallFromFile(),
        readFilmsFromFile());
    return result;
  }

  private static ArrayList<Hall> readHallFromFile() throws IOException {
    String filename = "res/HallList.csv";
    Scanner scanner = new Scanner(new FileReader(filename));
    checkExistFile(filename);
    ArrayList<Hall> result = new ArrayList<>();
    while (scanner.hasNextLine()) {
      result.add(parseHallFromString(scanner.nextLine()));
    }
    return result;
  }

  private static Hall parseHallFromString(String hallString) {
    int posSep = hallString.indexOf(SEP);
    return new Hall(hallString.substring(0, posSep),
        Integer.parseInt(hallString.substring(posSep + 1)));
  }

  private static Film parseFilmFromString(String filmString) {
    String[] strAfterSplit = filmString.split(SEP);
    return new Film(strAfterSplit[0], Integer.parseInt(strAfterSplit[1]));
  }

  private static Map<Date, Film> readFilmsFromFile() throws IOException {
    String filename = "res/FilmList.csv";
    Scanner scanner = new Scanner(new FileReader(filename));
    checkExistFile(filename);

    Map<Date, Film> result = new HashMap<>();
    int counterDate = 0;
    while (scanner.hasNextLine()) {
      result.put(java.sql.Date.valueOf(LocalDate.now().minusDays(counterDate)),
          parseFilmFromString(scanner.nextLine()));
      counterDate--;
    }
    return result;
  }

  private static void checkExistFile(String filename) throws IOException {
    File file = new File(filename);
    if (!file.exists()) {
      FileWriter fileWriter = new FileWriter(filename);
      fileWriter.close();
    }
  }
}
