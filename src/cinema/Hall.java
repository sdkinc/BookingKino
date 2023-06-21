package cinema;

import java.util.List;

public class Hall {

  private String name;
  private double price;
  private List<Session> sessions;

  public Hall(String name, double price) {
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public List<Session> getSessions() {
    return sessions;
  }

  public void setSessions(List<Session> sessions) {
    this.sessions = sessions;
  }

  public static Hall parseHallFromString(String hallString) {
    int posSep = hallString.indexOf(Constants.SEP);
    return new Hall(hallString.substring(0, posSep),
        Integer.parseInt(hallString.substring(posSep + 1)));
  }
}
