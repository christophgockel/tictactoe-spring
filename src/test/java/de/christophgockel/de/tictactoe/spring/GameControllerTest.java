package de.christophgockel.de.tictactoe.spring;

import de.christophgockel.tictactoe.spring.PlayGameUseCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestContext.class })
public class GameControllerTest {
  private MockMvc mvc;
  private MockHttpSession session;

  @Autowired
  WebApplicationContext context;

  @Autowired
  PlayGameUseCase playGameUseCase;

  @Before
  public void setup() {
    Mockito.reset(playGameUseCase);

    mvc = MockMvcBuilders.webAppContextSetup(context).build();
    session = new MockHttpSession(context.getServletContext(), UUID.randomUUID().toString());
  }

  @Test
  public void newGameRedirectsToGameIndex() throws Exception {
    mvc.perform(post("/game/new")
                .session(session)
                .param("player_pair", "1")
                .param("board_size", "1")
       .accept(MediaType.TEXT_HTML))
       .andExpect(status().is3xxRedirection());
  }

  @Test
  public void gameRendersGameTemplate() throws Exception {
    session.setAttribute("use_case", playGameUseCase);

    mvc.perform(get("/game")
                .session(session)
                .accept(MediaType.TEXT_HTML))
       .andExpect(status().isOk())
       .andExpect(view().name("game"));
  }

  @Test
  public void newGameStoresGameStateInSession() throws Exception {
    mvc.perform(post("/game/new")
                .session(session)
                .param("player_pair", "1")
                .param("board_size", "1"));

    assertNotNull(session.getAttribute("use_case"));
  }

  @Test
  public void playPlacesAMove() throws Exception {
    session.setAttribute("use_case", playGameUseCase);
    mvc.perform(get("/game/play?move=1").session(session));

    verify(playGameUseCase).playMove(anyInt());
  }
}
