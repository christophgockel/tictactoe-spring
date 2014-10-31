package de.christophgockel.de.tictactoe.spring;

import de.christophgockel.tictactoe.game.Mark;
import de.christophgockel.tictactoe.spring.GameState;
import de.christophgockel.tictactoe.spring.PlayGameUseCase;
import org.junit.Test;

import static de.christophgockel.tictactoe.game.Board.Size.ThreeByThree;
import static de.christophgockel.tictactoe.game.PlayerPairsFactory.Pair.ComputerComputer;
import static de.christophgockel.tictactoe.game.PlayerPairsFactory.Pair.HumanHuman;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

public class PlayGameUseCaseTest {
  @Test
  public void createsANewGame() {
    PlayGameUseCase useCase = new PlayGameUseCase();

    useCase.newGame(HumanHuman, ThreeByThree);

    assertNotNull(useCase.getGameState());
  }

  @Test
  public void playingARoundChangesTheBoard() {
    PlayGameUseCase useCase = new PlayGameUseCase();
    useCase.newGame(HumanHuman, ThreeByThree);

    useCase.playMove(1);

    assertEquals(8, useCase.getGameState().board.getFreeLocations().size());
  }

  @Test
  public void playsARoundOfTheGame() {
    PlayGameUseCase useCase = new PlayGameUseCase();
    useCase.newGame(HumanHuman, ThreeByThree);

    useCase.playMove(1);

    assertEquals(Mark.O, useCase.getGameState().getNextPlayer());
  }

  @Test
  public void playsRounds() {
    PlayGameUseCase useCase = new PlayGameUseCase();
    useCase.newGame(HumanHuman, ThreeByThree);

    useCase.playMove(1);
    useCase.playMove(9);
    useCase.playMove(2);
    useCase.playMove(8);
    useCase.playMove(3);

    assertThat(useCase.getGameState().status, containsString(Mark.X.toString()));
  }

  @Test
  public void isNotOngoingWhenOver() {
    PlayGameUseCase useCase = new PlayGameUseCase();
    useCase.newGame(HumanHuman, ThreeByThree);

    useCase.playMove(1);
    useCase.playMove(9);
    useCase.playMove(2);
    useCase.playMove(8);
    useCase.playMove(3);

    assertFalse(useCase.getGameState().isOngoing());
  }

  @Test
  public void isNotOngoingWhenWaitingForInput() {
    GameState state = new GameState();
    PlayGameUseCase useCase = new PlayGameUseCase(state);

    useCase.newGame(HumanHuman, ThreeByThree);
    useCase.playMove(1);

    assertFalse(state.isOngoing());
  }

  @Test
  public void isOngoingAsLongAsPlayersCanProvideInput() {
    GameState state = new GameState();
    PlayGameUseCase useCase = new PlayGameUseCase(state);

    useCase.newGame(ComputerComputer, ThreeByThree);
    useCase.playMove(1);

    assertTrue(state.isOngoing());
  }
}
