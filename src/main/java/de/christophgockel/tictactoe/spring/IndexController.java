package de.christophgockel.tictactoe.spring;

import de.christophgockel.tictactoe.game.Board;
import de.christophgockel.tictactoe.game.PlayerPairsFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class IndexController {
  @RequestMapping(value = "/", method = GET)
  public String index(Model model) {
    model.addAttribute("modes", PlayerPairsFactory.getAvailablePairs());
    model.addAttribute("sizes", Board.getAvailableSizes());

    return "index";
  }
}
