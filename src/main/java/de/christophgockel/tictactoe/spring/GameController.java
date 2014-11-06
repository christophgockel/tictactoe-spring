package de.christophgockel.tictactoe.spring;

import de.christophgockel.tictactoe.game.Board;
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
  private PlayGameUseCase useCase;

  @RequestMapping(value = "/game", method = GET)
  public String game(HttpServletRequest request, Model model) {
    useCase = getUseCaseFromSession(request);

    if (useCase == null) {
      return "redirect:/";
    } else {
      model.addAttribute("model", useCase.getGameState());

      return "game";
    }
  }

  @RequestMapping(value = "/game/new", method = POST)
  public String newGame(HttpServletRequest request, @RequestParam("player_pair") int pair, @RequestParam("board_size") int size) {
    HttpSession session = request.getSession();
    RequestConverter converter = new RequestConverter();


    PlayerPairsFactory.Pair playerPair = converter.convertPair(pair);
    Board.Size boardSize = converter.convertSize(size);

    useCase = new PlayGameUseCase();
    useCase.newGame(playerPair, boardSize);

    setUseCaseIntoSession(request, useCase);

    return "redirect:/game";
  }

  @RequestMapping(value = "/game/play", method = GET)
  public String play(HttpServletRequest request, @RequestParam(value = "move", required = false) Integer move) {
    useCase = getUseCaseFromSession(request);

    if (move == null) {
      useCase.playMove();
    } else {
      useCase.playMove(move);
    }

    return "redirect:/game";
  }

  private PlayGameUseCase getUseCaseFromSession(HttpServletRequest request) {
    HttpSession session = request.getSession();

    return (PlayGameUseCase) session.getAttribute("use_case");
  }

  private void setUseCaseIntoSession(HttpServletRequest request, PlayGameUseCase useCase) {
    HttpSession session = request.getSession();

    session.setAttribute("use_case", useCase);
  }
}
