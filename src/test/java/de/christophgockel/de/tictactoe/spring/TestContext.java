package de.christophgockel.de.tictactoe.spring;

import de.christophgockel.tictactoe.spring.PlayGameUseCase;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = { "de.christophgockel.tictactoe.spring" })
public class TestContext {
  @Bean
  public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/WEB-INF/views/");
    viewResolver.setSuffix(".jsp");

    return viewResolver;
  }

  @Bean
  public PlayGameUseCase playGameUseCase() {
    return Mockito.mock(PlayGameUseCase.class);
  }
}
