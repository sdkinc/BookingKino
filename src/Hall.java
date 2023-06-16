import java.util.Date;
import java.util.Map;

public class Hall {

  private String name;
  private double price;
  private Map<Date, Session> sessions;

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

  public Map<Date, Session> getSessions() {
    return sessions;
  }

  public void setSessions(Map<Date, Session> sessions) {
    this.sessions = sessions;
  }
}
