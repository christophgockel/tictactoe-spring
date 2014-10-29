package de.christophgockel.tictactoe.spring;

import de.christophgockel.tictactoe.game.Board;
import de.christophgockel.tictactoe.game.Game;
import de.christophgockel.tictactoe.game.Player;
import de.christophgockel.tictactoe.game.PlayerPairsFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class GameController {
  @RequestMapping(value = "/game", method = GET)
  public String game(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession();
    GameStateViewModel viewModel = (GameStateViewModel) session.getAttribute("view_model");

    model.addAttribute("model", viewModel);
    return "game";
  }

  @RequestMapping(value = "/game/new", method = POST)
  public String newGame(HttpServletRequest request, @RequestParam("game_mode") String mode, @RequestParam("board_size") String size) {
    HttpSession session = request.getSession();

    session.setAttribute("game_mode", mode);
    session.setAttribute("board_size", size);

    PlayerPairsFactory.Pair pair = PlayerPairsFactory.getAvailablePairs().get(new Integer(mode));
    GameStateViewModel viewModel = new GameStateViewModel();

    Player[] players = PlayerPairsFactory.createPair(pair, viewModel);
    Board board = new Board();

    Game game = new Game(players[0], players[1], board, viewModel);
    viewModel.board = board;

    viewModel.setOngoing(game.isPlayable());

    session.setAttribute("game", game);
    session.setAttribute("view_model", viewModel);

    return "redirect:/game";
  }

  @RequestMapping(value = "/game/play", method = GET)
  public String play(HttpServletRequest request, @RequestParam(value = "move", required = false) Integer move) {
    HttpSession session = request.getSession();

    Game game = (Game) session.getAttribute("game");
    GameStateViewModel viewModel = (GameStateViewModel) session.getAttribute("view_model");

    if (move != null) {
      viewModel.setNextMove(move);
    }

    try {
      game.nextRound();
      viewModel.setOngoing(game.isPlayable());
    } catch (Game.Over over) {
      viewModel.setOngoing(false);
      session.setAttribute("view_model", viewModel);
    }

    return "redirect:/game";
  }
}
