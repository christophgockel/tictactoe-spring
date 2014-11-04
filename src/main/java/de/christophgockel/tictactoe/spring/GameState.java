package de.christophgockel.tictactoe.spring;

import de.christophgockel.tictactoe.game.Board;
import de.christophgockel.tictactoe.game.Input;
import de.christophgockel.tictactoe.game.Mark;
import de.christophgockel.tictactoe.game.Output;

import java.util.LinkedList;

public class GameState implements Output, Input {
  private Mark nextPlayer;
  private String status;
  private Board board;
  private LinkedList<Integer> moves;
  private boolean isOngoing;

  public GameState() {
    moves = new LinkedList<>();
    isOngoing = true;
    status = "";
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

  public boolean getIsOngoing() {
    return isOngoing;
  }

  public Mark getNextPlayer() {
    return nextPlayer;
  }

  public String getStatus() {
    return status;
  }

  public Board getBoard() {
    return board;
  }

  public int getBoardSize() {
    return board.getSideLength();
  }
}
