package de.christophgockel.tictactoe.spring;

import de.christophgockel.tictactoe.game.Board;

import static de.christophgockel.tictactoe.game.Board.Size;
import static de.christophgockel.tictactoe.game.PlayerPairsFactory.Pair;
import static de.christophgockel.tictactoe.game.PlayerPairsFactory.getAvailablePairs;

public class RequestConverter {
  public Pair convertPair(int id) {
    Pair pair = getAvailablePairs().get(id);

    if (pair == null) {
      throw new InvalidId();
    }

    return pair;
  }

  public Size convertSize(int id) {
    Size size = Board.getAvailableSizes().get(id);

    if (size == null) {
      throw new InvalidId();
    }

    return size;
  }

  public class InvalidId extends RuntimeException {
  }
}
