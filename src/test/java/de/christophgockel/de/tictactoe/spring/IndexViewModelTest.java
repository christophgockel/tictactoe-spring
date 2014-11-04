package de.christophgockel.de.tictactoe.spring;

import de.christophgockel.tictactoe.game.Board;
import de.christophgockel.tictactoe.game.PlayerPairsFactory;
import de.christophgockel.tictactoe.spring.IndexViewModel;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class IndexViewModelTest {
  IndexViewModel viewModel;

  @Before
  public void setup() {
    viewModel = new IndexViewModel(Board.getAvailableSizes(), PlayerPairsFactory.getAvailablePairs());
  }

  @Test
  public void convertsBoardSizeMaps() {
    Map<Integer, String> sizes = new HashMap<>();
    sizes.put(1, "3x3");
    sizes.put(2, "4x4");

    assertEquals(sizes, viewModel.getBoardSizes());
  }

  @Test
  public void convertsPlayerPairs() {
    Map<Integer, String> modes = new HashMap<>();
    modes.put(1, "Human vs. Human");
    modes.put(2, "Human vs. Computer");
    modes.put(3, "Computer vs. Human");
    modes.put(4, "Computer vs. Computer");

    assertEquals(modes, viewModel.getPlayerPairs());
  }
}
