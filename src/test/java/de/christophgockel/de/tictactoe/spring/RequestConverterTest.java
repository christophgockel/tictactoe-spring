package de.christophgockel.de.tictactoe.spring;

import de.christophgockel.tictactoe.game.Board;
import de.christophgockel.tictactoe.game.PlayerPairsFactory;
import de.christophgockel.tictactoe.spring.RequestConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestConverterTest {
  @Test
  public void convertsPlayerPairs() {
    RequestConverter converter = new RequestConverter();

    assertEquals(PlayerPairsFactory.Pair.HumanHuman, converter.convertPair(1));
  }

  @Test
  public void convertsBoardSizes() {
    RequestConverter converter = new RequestConverter();

    assertEquals(Board.Size.FourByFour, converter.convertSize(2));
  }
}
