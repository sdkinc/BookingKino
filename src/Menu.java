import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {

  private static final String EXIT = "0";
  private static final String ADDFILM = "1";
  private static final String ADDSESSION = "2";
  private static final String PRINTFILMLIST = "3";
  private static final String PRINTSESSIONLIST = "4";
  private static final String PRINTSESSIONPLACES = "5";
  private static final String BOOKTICKET = "6";

  private static final Map<String, String> descriptions = new LinkedHashMap<>();

  static {
    descriptions.put(ADDFILM, "Добавить фильм");
    descriptions.put(ADDSESSION, "Добавить сеанс");
    descriptions.put(PRINTFILMLIST, "Вывести список фильмов");
    descriptions.put(PRINTSESSIONLIST, "Вывести список сеансов");
    descriptions.put(PRINTSESSIONPLACES, "Вывести карту мест сеанса");
    descriptions.put(BOOKTICKET, "Продать билет");
    descriptions.put(EXIT, "Выход");
  }

  private static final Map<String, Runnable> actions = new LinkedHashMap<>();

  static {
    actions.put(ADDFILM, () -> {
      try {
        System.out.println();
        System.out.println("Выполняется команда добавить фильм");
        System.out.println();
        readAndParseAndAddFilmFromScanner();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
    actions.put(ADDSESSION, () -> {
      try {
        System.out.println();
        System.out.println("Выполняется команда добавить сеанс");
        System.out.println();
        addSessionFromScanner();
      } catch (IOException | ParseException e) {
        throw new RuntimeException(e);
      }
    });
    actions.put(PRINTSESSIONLIST,
        Menu::printSessionList);
    actions.put(PRINTSESSIONPLACES, Menu::askSessionAndPrintPlaces);
    actions.put(PRINTFILMLIST, Menu::printPrettyFilmList);
    actions.put(EXIT, () -> System.exit(0));
  }

  private static void addSessionFromScanner() throws IOException, ParseException {
    Scanner scanner1 = new Scanner(System.in);
    List<Session> sessionList = Constants.cinema.getCinemaHall().get(0).getSessions();
    Session newSession = Session.parseSessionFromScanner(scanner1);
    sessionList.add(newSession);
    Files.writeObjectToFile(newSession);
  }

  private static void printPrettyFilmList() {
    System.out.println();
    System.out.println("Выполняется команда показать список фильмов");
    System.out.println();

    List<Film> filmList = Constants.cinema.getFilms();
    for (Film f : filmList) {
      System.out.println(f.toStringPretty());
    }
  }

  private static void printMenu() {
    System.out.println();
    System.out.println("\t\t\tМеню:\t\t\t");
    for (Map.Entry<String, String> entry : descriptions.entrySet()) {
      System.out.println(entry.getKey() + ". " + entry.getValue());
    }
  }

  public static String read() {
    printMenu();
    Scanner scanner = new Scanner(System.in);
    System.out.print("Введите команду: ");
    String command = scanner.nextLine();
    while (!descriptions.containsKey(command)) {
      System.out.println("Некорректная команда: \"" + command + '"');
      System.out.print("Введите команду: ");
      command = scanner.nextLine();
    }
    return command;
  }

  public static void apply(String command) {
    if (!actions.containsKey(command)) {
      throw new IllegalArgumentException("Некорректная команда: " + command);
    }
    actions.get(command).run();
  }

  public static void askSessionAndPrintPlaces() {
    System.out.println();
    System.out.println("Выполняется команда показать места на сеанс");
    System.out.println();

    Scanner scanner = new Scanner(System.in);
    printSessionListWithoutMessage();
    List<Session> sessions = Constants.cinema.getCinemaHall().get(0).getSessions();
    System.out.println("Выберите сеанс:");
    int i = scanner.nextInt();
    Session current = sessions.get(i - 1);
    current.printMapPlaces();
  }

  public static void readAndParseAndAddFilmFromScanner() throws IOException {
    Film newFilm = Film.parseFilmFromScanner();
    List<Film> filmList = Constants.cinema.getFilms();
    filmList.add(newFilm);
    Constants.cinema.setFilms(filmList);
    Files.writeObjectToFile(newFilm);
  }

  private static void printSessionList() {
    System.out.println();
    System.out.println("Выполняется команда показать сеансы");
    System.out.println();

    printSessionListWithoutMessage();
  }

  private static void printSessionListWithoutMessage() {
    List<Session> sessions = Constants.cinema.getCinemaHall().get(0).getSessions();
    int j = 1;
    for (Session s : sessions) {
      System.out.println("#" + j + ". " + s.toStringPretty());
      j++;
    }
  }
}
