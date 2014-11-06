package de.christophgockel.de.tictactoe.spring;

import de.christophgockel.tictactoe.game.Mark;
import de.christophgockel.tictactoe.spring.GameState;
import de.christophgockel.tictactoe.spring.PlayGameUseCase;
import org.junit.Before;
import org.junit.Test;

import static de.christophgockel.tictactoe.game.Board.Size.ThreeByThree;
import static de.christophgockel.tictactoe.game.PlayerPairsFactory.Pair.ComputerComputer;
import static de.christophgockel.tictactoe.game.PlayerPairsFactory.Pair.ComputerHuman;
import static de.christophgockel.tictactoe.game.PlayerPairsFactory.Pair.HumanHuman;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

public class PlayGameUseCaseTest {
  private PlayGameUseCase useCase;
  private GameState gameState;

  @Before
  public void setup() {
    gameState = new GameState();
    useCase = new PlayGameUseCase(gameState);
  }

  @Test
  public void createsANewGame() {
    useCase.newGame(HumanHuman, ThreeByThree);

    assertNotNull(useCase.getGameState());
  }

  @Test
  public void playingARoundChangesTheBoard() {
    useCase.newGame(HumanHuman, ThreeByThree);

    useCase.playMove(1);

    assertEquals(8, gameState.getBoard().getFreeLocations().size());
  }

  @Test
  public void playsARoundOfTheGame() {
    useCase.newGame(HumanHuman, ThreeByThree);

    useCase.playMove(1);

    assertEquals(Mark.O, gameState.getNextPlayer());
  }

  @Test
  public void playsRounds() {
    useCase.newGame(HumanHuman, ThreeByThree);

    useCase.playMove(1);
    useCase.playMove(9);
    useCase.playMove(2);
    useCase.playMove(8);
    useCase.playMove(3);

    assertThat(gameState.getStatus(), containsString(Mark.X.toString()));
  }

  @Test
  public void isNotOngoingWhenOver() {
    useCase.newGame(HumanHuman, ThreeByThree);

    useCase.playMove(1);
    useCase.playMove(9);
    useCase.playMove(2);
    useCase.playMove(8);
    useCase.playMove(3);

    assertFalse(gameState.getIsOngoing());
  }

  @Test
  public void isNotOngoingWhenWaitingForInput() {
    useCase.newGame(HumanHuman, ThreeByThree);
    useCase.playMove(1);

    assertFalse(gameState.getIsOngoing());
  }

  @Test
  public void isOngoingAsLongAsPlayersCanProvideInput() {
    useCase.newGame(ComputerComputer, ThreeByThree);
    useCase.playMove(1);

    assertTrue(gameState.getIsOngoing());
  }

  @Test
  public void computerMovesDoNotNeedALocation() {
    useCase.newGame(ComputerHuman, ThreeByThree);
    useCase.playMove();

    assertEquals(Mark.O, gameState.getNextPlayer());
  }
}
