package cinema;

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

  /***
   * Статическое заполнение меню
   */
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

  /***
   * Наполняем LinkedHashMap actions соответствующими командами меню и действиями.
   */
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
    actions.put(BOOKTICKET, () -> {
      try {
        bookTicket();
      } catch (IOException | ParseException e) {
        throw new RuntimeException(e);
      }
    });
    actions.put(EXIT, () -> System.exit(0));
  }

  /***
   * Метод вызывает чтение данных из Scanner для нового сеанса, после чего создает новый сеанс,
   * добавляет его в список сеансов и записывает в файл
   * @throws IOException
   * @throws ParseException
   */
  private static void addSessionFromScanner() throws IOException, ParseException {
    Scanner scanner = new Scanner(System.in);
    List<Session> sessionList = Constants.cinema.getCinemaHall().get(0).getSessions();
    Session newSession = Session.parseSessionFromScanner(scanner);
    sessionList.add(newSession);
    Files.writeObjectToFile(newSession);
  }

  /***
   * Метод выводит на экран список фильмов в отформатированном виде
   */
  private static void printPrettyFilmList() {
    System.out.println();
    System.out.println("Выполняется команда показать список фильмов");
    System.out.println();

    List<Film> filmList = Constants.cinema.getFilms();
    for (Film f : filmList) {
      System.out.println(f.toStringPretty());
    }
  }

  /***
   * Метод выводит меню на экран
   */
  private static void printMenu() {
    System.out.println();
    System.out.println("\t\t\tМеню:\t\t\t");
    for (Map.Entry<String, String> entry : descriptions.entrySet()) {
      System.out.println(entry.getKey() + ". " + entry.getValue());
    }
  }

  /***
   * Метод выполняет чтение команды с клавиатуры с проверкой ввода и выводом меню
   * @return
   */
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

  /***
   * Метод вызывает действие для команды, которая ему на вход передана
   * @param command
   */
  public static void apply(String command) {
    if (!actions.containsKey(command)) {
      throw new IllegalArgumentException("Некорректная команда: " + command);
    }
    actions.get(command).run();
  }

  /***
   * Метод показывает список сеансов и после выбора пользователя показывает карту мест сеанса
   */
  public static void askSessionAndPrintPlaces() {
    System.out.println();
    System.out.println("Выполняется команда показать места на сеанс");
    System.out.println();

    Scanner scanner = new Scanner(System.in);

    List<Session> sessions = Constants.cinema.getCinemaHall().get(0).getSessions();
    printSessionListWithoutMessage(sessions);
    System.out.println("Выберите сеанс:");
    int i = scanner.nextInt();
    Session current = sessions.get(i - 1);
    current.printMapPlaces();
  }

  /***
   * Метод вызывает чтение нового фильма из сканера в новый объект,
   * после чего добавляет его в список фильмов
   * @throws IOException
   */
  public static void readAndParseAndAddFilmFromScanner() throws IOException {
    Film newFilm = Film.parseFilmFromScanner();
    List<Film> filmList = Constants.cinema.getFilms();
    filmList.add(newFilm);
    Constants.cinema.setFilms(filmList);
    Files.writeObjectToFile(newFilm);
  }

  /***
   * Метод выводит наименование команды и вызывает действие для этой команды
   */
  private static void printSessionList() {
    System.out.println();
    System.out.println("Выполняется команда показать сеансы");
    System.out.println();

    printSessionListWithoutMessage(Constants.cinema.getCinemaHall().get(0).getSessions());
  }

  /***
   * Метод выполняет получение списка сеансов и вывод на экран в приятно оформленном виде
   */
  private static void printSessionListWithoutMessage(List<Session> sessions) {
    int j = 1;
    for (Session s : sessions) {
      System.out.println("#" + j + ". " + s.toStringPretty());
      j++;
    }
  }

  public static void bookTicket() throws IOException, ParseException {
    System.out.println();
    System.out.println("Выполняется команда продажа билета ");
    System.out.println();

    Scanner scanner = new Scanner(System.in);
    List<Session> sessions = Constants.cinema.getCinemaHall().get(0).getSessions();
    printSessionListWithoutMessage(sessions);
    int sessionNumber = scanner.nextInt();
    Session currentSession = sessions.get(sessionNumber-1);
    currentSession.printMapPlaces();
    System.out.print("Введите номер ряда:");
    int rowNumber = scanner.nextInt();
    System.out.print("Введите номер места:");
    int placeNumber = scanner.nextInt();
    List<List<Integer>> currentPlaces = currentSession.getPlaces();
    if(currentPlaces.get(rowNumber-1).get(placeNumber-1)==1){
      System.out.println("Данное место уже продано! Попробуйте снова, "
          + "или введите 0 для перехода в главное меню");
    }else {
      System.out.println();
      System.out.println("Место на сеанс \n"+currentSession.toStringPretty()+"\nзабронировано успешно: ряд "+rowNumber+" номер места "+placeNumber);
      System.out.println();
      currentSession.reservePlaceInSession(rowNumber-1,  placeNumber-1);
      try {
        Files.writeObjectToFile(currentSession);
      }catch (IOException exception){
        throw new RuntimeException(exception.toString());
      }catch (ParseException exception2){
        throw new RuntimeException(exception2);
      }
      currentSession.printMapPlaces();
    }

  }
}
