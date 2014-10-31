package de.christophgockel.tictactoe.spring;

import de.christophgockel.tictactoe.game.Board;
import de.christophgockel.tictactoe.game.PlayerPairsFactory;

public class RequestConverter {
  public PlayerPairsFactory.Pair convertPair(int id) {
    return PlayerPairsFactory.getAvailablePairs().get(id);
  }

  public Board.Size convertSize(int id) {
    return Board.getAvailableSizes().get(id);
  }
}
