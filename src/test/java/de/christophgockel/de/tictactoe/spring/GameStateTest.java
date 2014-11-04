package de.christophgockel.de.tictactoe.spring;

import de.christophgockel.tictactoe.game.Board;
import de.christophgockel.tictactoe.game.Mark;
import de.christophgockel.tictactoe.spring.GameState;
import org.junit.Before;
import org.junit.Test;

import static de.christophgockel.tictactoe.game.Board.Size.ThreeByThree;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class GameStateTest {
  private GameState state;

  @Before
  public void setup() {
    state = new GameState();
  }

  @Test
  public void canSetANewMove() {
    state.setNextMove(3);

    assertEquals(3, state.getMove());
  }

  @Test
  public void settingAMoveEnablesGamePlay() {
    state.setNextMove(3);

    assertTrue(state.canProvideMove());
  }

  @Test
  public void onlyOneMoveCanBeSetAtATime() {
    state.setNextMove(1);
    state.setNextMove(2);
    state.setNextMove(3);
    state.getMove();

    assertFalse(state.canProvideMove());
  }

  @Test
  public void returnsTheNextPlayer() {
    state.showNextPlayer(Mark.O);

    assertEquals(Mark.O, state.getNextPlayer());
  }

  @Test
  public void initialStateHasNoStatus() {
    assertThat(state.getStatus(), is(""));
  }

  @Test
  public void announcingDrawChangesStatus() {
    state.showDraw();
    assertThat(state.getStatus(), is(not("")));
  }

  @Test
  public void announcingWinnerChangesStatus() {
    state.showWinner(Mark.X);
    assertThat(state.getStatus(), is(not("")));
  }

  @Test
  public void announcingInvalidMoveChangesStatus() {
    state.showInvalidMoveMessage();
    assertThat(state.getStatus(), is(not("")));
  }

  @Test
  public void knowsBoardSize() {
    state.show(new Board(ThreeByThree));
    assertEquals(3, state.getBoardSize());
  }
}
