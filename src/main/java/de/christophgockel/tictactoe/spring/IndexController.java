package de.christophgockel.tictactoe.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class IndexController {
  @RequestMapping(value = "/", method = GET)
  public String index() {
    return "index";
  }
}
