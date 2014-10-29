package de.christophgockel.tictactoe.spring;

import de.christophgockel.tictactoe.game.Board;
import de.christophgockel.tictactoe.game.Input;
import de.christophgockel.tictactoe.game.Mark;
import de.christophgockel.tictactoe.game.Output;

public class GameStateViewModel implements Output, Input {
  public int move = 0;
  public String status;
  public Board board;

  @Override
  public boolean canProvideMove() {
    return move != 0;
  }

  @Override
  public int getMove() {
    return move;
  }

  @Override
  public void show(Board board) {
    this.board = board;
  }

  @Override
  public void showWinner(Mark mark) {
    status = "Winner is: " + mark;
  }

  @Override
  public void showDraw() {
    status = "Game ended in a draw.";
  }

  @Override
  public void showNextPlayer(Mark mark) {
    status = "Next player: " + mark;
  }

  @Override
  public void showInvalidMoveMessage() {
    status = "Invalid move";
  }
}
