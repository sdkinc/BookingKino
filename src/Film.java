public class Film {

  private String name;
  private String producer;
  private String genre;
  private int lengthInMin;

  public Film(String name, int lengthInMin) {
    this.name = name;
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
}
