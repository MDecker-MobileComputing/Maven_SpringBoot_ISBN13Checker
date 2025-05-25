package de.eldecker.dhbw.spring.isbn13checker.logik;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;


/**
 * Bean-Klasse mit Logik für die eigentliche ISBN13-Prüfung.
 */
@Service
public class ISBN13Check {
    
    private final static Logger LOG = LoggerFactory.getLogger( ISBN13Check.class );

    
    /**
     * Methode prüft, ob die Prüfziffer der als Argument übergbebenen ISBN13 korrekt ist.
     * <br><br>
     * 
     * Für Algorithmus zur Berechnung der ISBN13-Prüfziffer siehe auch:
     * https://de.wikipedia.org/wiki/Internationale_Standardbuchnummer#Formeln_zur_Berechnung_der_Pr%C3%BCfziffer
     * 
     * @param isbn ISBN13 als String, z.B. "978-3-16-148410-0"
     * 
     * @return {@code true} gdw. die Prüfziffer korrekt ist
     */
    public boolean isbn13Pruefziffer13IstKorrekt( String isbn ) {
        
        if ( isbn == null ) {
            
            LOG.error( "ISBN ist null." );
            return false;
        }
        
        // alle Zeichen entfernen, die keine Ziffern sind
        final String ziffernString = isbn.replaceAll( "[^0-9]", "" );
        
        if ( ziffernString.length() != 13 ) {
            
            LOG.error( "ISBN hat nicht genau 13 Ziffern sondern {} Ziffern.", 
                       ziffernString.length() );
            
            return false;
        }
            

        int summe = 0;
        for ( int i = 0; i < 12; i++ ) {
            
            char zifferAlsChar = ziffernString.charAt( i );
            int ziffer = Character.getNumericValue( zifferAlsChar );
            if ( i % 2 == 0 ) {
                
                summe += ziffer;
                
            } else {
                
                summe += ziffer * 3;
            }
        }
        
        final int pruefZiffer = ( 10 - ( summe % 10 ) ) % 10;
        
        final char pruefzifferAlsChar = ziffernString.charAt( 12 );
        final int  pruefziffer        = Character.getNumericValue( pruefzifferAlsChar );
        
        LOG.info( "Prüfziffer in ISBN13 enthalten: {}; Prüfziffer berechnet: {}", 
                  pruefZiffer, pruefziffer );
        
        return pruefZiffer == pruefziffer;
    }
    
}
