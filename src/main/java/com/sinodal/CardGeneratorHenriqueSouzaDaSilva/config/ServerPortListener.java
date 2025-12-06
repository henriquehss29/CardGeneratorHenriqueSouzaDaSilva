package com.sinodal.CardGeneratorHenriqueSouzaDaSilva.config;

import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ServerPortListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        if (event.getApplicationContext() instanceof ServletWebServerApplicationContext context) {
            int port = context.getWebServer().getPort();
  
            System.out.println("\n ===========CARD GENERATOR INICIADO!===========");            
            System.out.println(" | ACESSE NO NAVEGADOR:                       |");
            System.out.println(" | http://localhost:" + port +"                     |");
            System.out.println(" | Pronto para gerar cartões!                 |");
            System.out.println(" ==============================================");
            System.out.println(" | ACESSE O H2 NO NAVEGADOR:                  |");
            System.out.println(" | http://localhost:" + port + "/h2-console          |");
            System.out.println(" | URL: jdbc:h2:mem:card  --  USERNAME: card  |");
            System.out.println(" ==============================================");
        }
    }
}