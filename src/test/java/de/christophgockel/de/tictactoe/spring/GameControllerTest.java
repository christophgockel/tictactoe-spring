package de.christophgockel.de.tictactoe.spring;

import de.christophgockel.tictactoe.game.Mark;
import de.christophgockel.tictactoe.spring.Application;
import de.christophgockel.tictactoe.spring.GameState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class GameControllerTest {
  private MockMvc mvc;
  private MockHttpSession session;


  @Autowired
  WebApplicationContext context;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
    session = new MockHttpSession(context.getServletContext(), UUID.randomUUID().toString());
  }

  @Test
  public void newGameRedirectsToGameIndex() throws Exception {
    mvc.perform(post("/game/new")
                  .session(session)
                  .param("game_mode", "42")
                  .param("board_size", "24")
                  .accept(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection());
  }

  @Test
  public void gameRendersGameTemplate() throws Exception {
    mvc.perform(post("/game/new")
      .session(session)
      .param("game_mode", "1")
      .param("board_size", "1"));

    mvc.perform(get("/game")
                  .session(session)
                  .accept(MediaType.TEXT_HTML))
         .andExpect(status().isOk())
         .andExpect(view().name("game"));
  }

  @Test
  public void newGameKeepsFormDataInSession() throws Exception {
    mvc.perform(post("/game/new")
      .session(session)
      .param("game_mode", "42")
      .param("board_size", "24"));

    assertEquals("42", session.getAttribute("game_mode"));
    assertEquals("24", session.getAttribute("board_size"));
  }

  @Test
  public void newGameCreatesNewGameInSession() throws Exception {
    mvc.perform(post("/game/new")
      .session(session)
      .param("game_mode", "42")
      .param("board_size", "24"));

    assertNotNull(session.getAttribute("game"));
    assertNotNull(session.getAttribute("view_model"));
  }

  @Test
  public void playPlacesAMove() throws Exception {
    mvc.perform(post("/game/new")
      .session(session)
      .param("game_mode", "1")
      .param("board_size", "1"));

    mvc.perform(get("/game/play?move=1")
      .session(session));

    GameState gameState = (GameState) session.getAttribute("view_model");

    assertEquals(Mark.X, gameState.board.getMarks().get(1));
  }
}
