package de.christophgockel.tictactoe.spring;

import de.christophgockel.tictactoe.game.Board;
import de.christophgockel.tictactoe.game.Game;
import de.christophgockel.tictactoe.game.Player;
import de.christophgockel.tictactoe.game.PlayerPairsFactory;

public class PlayGameUseCase {
  private GameState gameState;
  private Game game;

  public PlayGameUseCase() {
    gameState = new GameState();
  }

  public PlayGameUseCase(GameState state) {
    gameState = state;
  }

  public Game newGame(PlayerPairsFactory.Pair pair, Board.Size boardSize) {
    Player[] players = PlayerPairsFactory.createPair(pair, gameState);
    Board board = new Board(boardSize);

    game = new Game(players[0], players[1], board, gameState);
    gameState.setOngoing(game.isPlayable());

    return game;
  }

  public GameState getGameState() {
    return gameState;
  }

  public void playMove(int move) {
    gameState.setNextMove(move);
    playNextRound();
  }

  public void playMove() {
    playNextRound();
  }

  private void playNextRound() {
    try {
      game.nextRound();
      gameState.setOngoing(game.isPlayable());
    } catch (Game.Over over) {
      gameState.setOngoing(false);
    }
  }
}
