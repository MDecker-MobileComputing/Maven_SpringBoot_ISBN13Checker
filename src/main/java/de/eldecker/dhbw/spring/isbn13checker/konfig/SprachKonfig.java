package de.eldecker.dhbw.spring.isbn13checker.konfig;

import static java.util.Locale.ENGLISH;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


/**
 * Konfiguration für i18n mit Thymeleaf nach folgendem Artikel:
 * https://lokalise.com/blog/spring-boot-internationalization/
 */ 
@Configuration
public class SprachKonfig implements WebMvcConfigurer  {

    /**
     * Erzeugt den LocalResolver. Es handelt sich hierbei um das Objekt, mit dem 
     * bestimmt wird, welche Sprache verwendet werden soll;
     * siehe auch:
     * https://lokalise.com/blog/spring-boot-internationalization/#Meet_LocaleResolver
     * <br><br>
     * 
     * Der {@code AcceptHeaderLocalResolver} wertet den HTTP-Request-Header
     * {@code Accept-Language} aus. Beispielwert für diesen Header:
     * {@code en-US,en;q=0.9,de-DE;q=0.8,de;q=0.7}
     * <br><br>
     * 
     * Damit die in dieser Methode definierte Fallback-Sprache zieht, muss
     * in der Datei {@code application.properties} die folgende Einstellung
     * gesetzt sein:
     * <pre>
     * spring.messages.fallback-to-system-locale=false
     * </pre>  
     * 
     * @return Konfiguriertes LocalResolver-Objekt
     */
    @Bean
    public LocaleResolver localeResolver() {
        
        //AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
                
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        
        resolver.setDefaultLocale( ENGLISH );
        
        return resolver;
    }
    
    
    /**
     * Sprache kann mit URL-Parameter {@code lang} gesetzt werden,
     * z.B. {@code sprache=de} oder {@code sprache=en}; die
     * Sprache wird dann aber nur bei Verwendung von {@code SessionLocaleResolver}
     * für alle anderen Seiten beachtet.
     * 
     * @return Konfigurierter Interceptor
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName( "sprache" );
        
        return lci;
    }
           
}
