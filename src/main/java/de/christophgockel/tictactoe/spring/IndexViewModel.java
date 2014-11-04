package de.christophgockel.tictactoe.spring;

import java.util.HashMap;
import java.util.Map;

import static de.christophgockel.tictactoe.game.Board.Size;
import static de.christophgockel.tictactoe.game.PlayerPairsFactory.Pair;

public class IndexViewModel {
  private Map<Integer, Size> sizes;
  private Map<Integer, Pair> pairs;

  public IndexViewModel(Map<Integer, Size> availableSizes, Map<Integer, Pair> availablePairs) {
    sizes = availableSizes;
    pairs = availablePairs;
  }

  public Map<Integer, String> getBoardSizes() {
    Map<Integer, String> boardSizes = new HashMap<>();

    for (Map.Entry<Integer, Size> sizeEntry : sizes.entrySet()) {
      boardSizes.put(sizeEntry.getKey(), sizeEntry.getValue().getDescription());
    }

    return boardSizes;
  }

  public Map<Integer, String> getPlayerPairs() {
    Map<Integer, String> playerPairs = new HashMap<>();

    for (Map.Entry<Integer, Pair> modeEntry : pairs.entrySet()) {
      playerPairs.put(modeEntry.getKey(), modeEntry.getValue().getDescription());
    }

    return playerPairs;
  }
}
