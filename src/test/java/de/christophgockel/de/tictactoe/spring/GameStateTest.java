package de.christophgockel.de.tictactoe.spring;

import de.christophgockel.tictactoe.game.Board;
import de.christophgockel.tictactoe.game.Mark;
import de.christophgockel.tictactoe.spring.GameState;
import org.junit.Before;
import org.junit.Test;

import static de.christophgockel.tictactoe.game.Board.Size.FourByFour;
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
  public void returnsBoardRowsAsArrayOfMaps() {
    Board board = new Board();
    board = board.setMove(1, Mark.X)
                 .setMove(5, Mark.O)
                 .setMove(9, Mark.X);
    state.show(board);

    Mark[][] marks = new Mark[][] {
      { Mark.X, null, null },
      { null, Mark.O, null },
      { null, null, Mark.X }
    };

    assertEquals(marks, state.getBoardRows());
  }

  @Test
  public void returnsBoardRowsFor4x4AsArrayOfMaps() {
    Board board = new Board(FourByFour);
    board = board.setMove(1, Mark.X)
      .setMove(6, Mark.O)
      .setMove(11, Mark.O)
      .setMove(16, Mark.X);
    state.show(board);

    Mark[][] marks = new Mark[][] {
      { Mark.X, null, null, null },
      { null, Mark.O, null, null },
      { null, null, Mark.O, null },
      { null, null, null, Mark.X }
    };

    assertEquals(marks, state.getBoardRows());
  }
}
