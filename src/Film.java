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

  public String toStringPretty() {
    return "" +
        "Название фильма:" + name +
        ", режиссер - " + producer +
        ", жанр - " + genre +
        ", длительность: " + lengthInMin + " мин";
  }

  public String toFile() {
    String sep = Constants.SEP;
    return name + sep + producer + sep + genre + sep + lengthInMin;
  }
}
