package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cinema.Constants;
import cinema.Hall;
import org.junit.jupiter.api.Test;

public class HallTests {
  @Test
  public void testConstructor(){
    //arrange
    String name = "RED";
    int price  =50;

    //act
    Hall hall = new Hall(name,price);
    assertEquals(name,hall.getName());
    assertEquals(price,hall.getPrice());
  }

  @Test
  public void testParseFromString(){
    //arrange
    String string = "RED;50";
    String name = "RED";
    int price  =50;

    //act
    Hall hall = Hall.parseHallFromString(string);
    assertEquals(name,hall.getName());
    assertEquals(price,hall.getPrice());
  }
}
