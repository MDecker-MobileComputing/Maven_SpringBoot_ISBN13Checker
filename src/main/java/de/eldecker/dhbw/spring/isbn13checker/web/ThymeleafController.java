package de.eldecker.dhbw.spring.isbn13checker.web;


import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.eldecker.dhbw.spring.isbn13checker.logik.ISBN13Check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


/**
 * Thymeleaf-Controller mit i18n.
 * <br><br>
 * 
 * i18n mit Thymeleaf in einer Spring-Boot-Anwendung:
 * <ul>
 * <li>https://www.baeldung.com/spring-boot-internationalization</li>
 * <li>https://howtodoinjava.com/spring-boot/rest-i18n-example/</li>
 * </ul>
 */
@Controller
@RequestMapping( "/app/" )
public class ThymeleafController {

    /**
     * Service-Bean für die ISBN-13-Prüfung.
     */
    private ISBN13Check _isbn13Check;
    
    
    /**
     * Konstruktor für Dependency Injection.
     */
    @Autowired
    public ThymeleafController( ISBN13Check isbn13Check ) {
        
        _isbn13Check = isbn13Check;
    }
        
    /**
     * Controller-Methode für die Anzeige des Formulars zur Eingabe der ISBN-13.
     * 
     * @param model Model-Objekt für die View
     * 
     * @return Immer "formular" als View-Name für "formular.html"
     */
    @GetMapping( "/formular" )
    public String formular( Model model ) {
        
        return "formular";
    }
    
    
    @GetMapping( "/ueberpruefen" )
    public String ergebnis( Model model,
                            @RequestParam( name = "isbn13", required = true ) String isbn13 ) {
        
        return "ergebnis";
    }
    
}
