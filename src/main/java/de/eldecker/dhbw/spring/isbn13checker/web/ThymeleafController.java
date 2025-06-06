package de.eldecker.dhbw.spring.isbn13checker.web;


import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.eldecker.dhbw.spring.isbn13checker.logik.ISBN13Check;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;


/**
 * Thymeleaf-Controller mit i18n.
 * <br><br>
 * 
 * Spracheinstellung von Browser abfragen:
 * In JavaScript-Konsole {@code navigator.languages} eingeben. 
 * Damit wird ein Array zurückgeliefert, der die Sprachen in der im Browser
 * einstellten Reihenfolge zurückliefert, z.B.:
 * <pre>
 * [ 'en-US', 'de-DE', 'de', 'en' ]
 * </pre>
 */
@Controller
@RequestMapping( "/app/" )
public class ThymeleafController {
    
    private final static Logger LOG = LoggerFactory.getLogger( ThymeleafController.class );

    
    /**
     * Service-Bean für die ISBN13-Prüfung.
     */
    private ISBN13Check _isbn13Check;

    /**
     * Bean für Zugriff auf i18n-Texte.
     */
    private MessageSource _messageSource;
    
    
    /**
     * Konstruktor für Dependency Injection.
     */
    @Autowired
    public ThymeleafController( ISBN13Check isbn13Check, 
                                MessageSource messageSource ) {
        
        _isbn13Check   = isbn13Check;
        _messageSource = messageSource;
    }
    
    
    /**
     * Schreibt aktuelle Sprache auf den Logger.
     */
    private void sprache2logger() {
        
        Locale aktuelleSprache = LocaleContextHolder.getLocale();
        LOG.info( "Aktuelle Sprache: {}", aktuelleSprache );
    }
        
    
    /**
     * Controller-Methode für die Anzeige des Formulars zur Eingabe der ISBN13.
     * 
     * @return Immer "formular" als View-Name für "formular.html"
     */
    @GetMapping( "/formular" )
    public String formular() {
        
        sprache2logger();
        
        return "formular";
    }
    
    
    /**
     * Ergebnis der Überprüfung anzeigen.
     * 
     * @param model Model-Objekt für Übergabe Werte für Platzhalterwerte
     * 
     * @param isbn13 Von Nutzer in Formular eingegebene ISBN13, die überprüft
     *               werden soll
     * 
     * @return Immer "ergebnis" als View-Name für "ergebnis.html"
     */
    @GetMapping( "/ueberpruefen" )
    public String ergebnis( Model model,
                            @RequestParam( name     = "isbn13", 
                                           required = true     ) String isbn13 ) {    
        sprache2logger();
        
        isbn13 = isbn13.trim();
        
        final boolean istOkay = _isbn13Check.isbn13Pruefziffer13IstKorrekt( isbn13 );
        
        final Locale   aktuelleSprache  = LocaleContextHolder.getLocale();
        final String   textSchluessel   = istOkay ? "ergebnis.gueltig" : "ergebnis.ungueltig";
        final Object[] platzhalterWerte = { isbn13 };        
        
        final String ergebnis = _messageSource.getMessage( textSchluessel, 
        		                                           platzhalterWerte, 
        		                                           aktuelleSprache );         		                                                 
        model.addAttribute( "ergebnis" , ergebnis );
        
        return "ergebnis";
    }
    
}
