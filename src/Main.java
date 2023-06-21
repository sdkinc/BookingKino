import cinema.Constants;
import cinema.Files;
import cinema.Hall;
import cinema.Menu;
import cinema.Session;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException, ParseException {
    Constants.cinema = Files.readCinemaFromFile();
    System.out.println("АРМ кассира в кинотеатре '" + Constants.cinema.getName() + "'");
    List<Session> session = Files.readSessionsFromFile();
    List<Hall> hallList = Constants.cinema.getCinemaHall();
    for (Hall h : hallList) {
      h.setSessions(session);
    }
    while (true) {
      Menu.apply(Menu.read());
    }
  }
}
