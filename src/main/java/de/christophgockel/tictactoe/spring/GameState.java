package de.christophgockel.tictactoe.spring;

import de.christophgockel.tictactoe.game.Board;
import de.christophgockel.tictactoe.game.Input;
import de.christophgockel.tictactoe.game.Mark;
import de.christophgockel.tictactoe.game.Output;

import java.util.LinkedList;
import java.util.Map;

public class GameState implements Output, Input {
  private Mark nextPlayer;
  public String status;
  public Board board;
  private LinkedList<Integer> moves;
  private boolean isOngoing;

  public GameState() {
    moves = new LinkedList<>();
    isOngoing = true;
  }

  @Override
  public boolean canProvideMove() {
    return moves.size() > 0;
  }

  @Override
  public int getMove() {
    return moves.pop();
  }

  @Override
  public void show(Board board) {
    this.board = board;
  }

  @Override
  public void showWinner(Mark mark) {
    status = "Winner is: " + mark;
    nextPlayer = null;
  }

  @Override
  public void showDraw() {
    status = "Game ended in a draw.";
    nextPlayer = null;
  }

  @Override
  public void showNextPlayer(Mark mark) {
    nextPlayer = mark;
  }

  @Override
  public void showInvalidMoveMessage() {
    status = "Invalid move";
  }

  public void setNextMove(int move) {
    moves.clear();
    moves.add(move);
  }

  public void setOngoing(boolean ongoing) {
    isOngoing = ongoing;
  }

  public boolean isOngoing() {
    return isOngoing;
  }

  public Mark getNextPlayer() {
    return nextPlayer;
  }

  public Mark[][] getBoardRows() {
    int sideLength = board.getSideLength();
    Mark[][] rows = new Mark[sideLength][];
    Mark[] row;
    Map<Integer, Mark> marks = board.getMarks();

    for (int i = 0; i < sideLength; i++) {
      row = new Mark[sideLength];

      for (int j = 0; j < sideLength; j++) {
        row[j] = marks.get(i * sideLength + j + 1);
      }

      rows[i] = row;
    }

    return rows;
  }
}
