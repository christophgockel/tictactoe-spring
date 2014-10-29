package de.christophgockel.de.tictactoe.spring;

import de.christophgockel.tictactoe.spring.GameStateViewModel;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameStateViewModelTest {
  @Test
  public void canSetANewMove() {
    GameStateViewModel model = new GameStateViewModel();
    model.setNextMove(3);

    assertEquals(3, model.getMove());
  }

  @Test
  public void settingAMoveEnablesGamePlay() {
    GameStateViewModel model = new GameStateViewModel();
    model.setNextMove(3);

    assertTrue(model.canProvideMove());
  }

  @Test
  public void onlyOneMoveCanBeSetAtATime() {
    GameStateViewModel model = new GameStateViewModel();
    model.setNextMove(1);
    model.setNextMove(2);
    model.setNextMove(3);
    model.getMove();

    assertFalse(model.canProvideMove());
  }
}
