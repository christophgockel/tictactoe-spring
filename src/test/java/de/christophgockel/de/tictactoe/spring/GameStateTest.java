package de.christophgockel.de.tictactoe.spring;

import de.christophgockel.tictactoe.spring.GameState;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameStateTest {
  @Test
  public void canSetANewMove() {
    GameState model = new GameState();
    model.setNextMove(3);

    assertEquals(3, model.getMove());
  }

  @Test
  public void settingAMoveEnablesGamePlay() {
    GameState model = new GameState();
    model.setNextMove(3);

    assertTrue(model.canProvideMove());
  }

  @Test
  public void onlyOneMoveCanBeSetAtATime() {
    GameState model = new GameState();
    model.setNextMove(1);
    model.setNextMove(2);
    model.setNextMove(3);
    model.getMove();

    assertFalse(model.canProvideMove());
  }
}
