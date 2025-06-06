package de.eldecker.dhbw.spring.isbn13checker.konfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;


@Component
public class WebKonfigurierer implements WebMvcConfigurer {

	private LocaleChangeInterceptor _localeInterceptor;

	
	/**
	 * Konstruktor für Dependency Injection.
	 */
	@Autowired
	public WebKonfigurierer( LocaleChangeInterceptor localeInterceptor ) {
		
		_localeInterceptor = localeInterceptor;
	}
	
	
    /**
     * Einzige abstrakte Methode aus Interface {@code WebMvcConfigurer} überschreiben.
     * 
     * @param registry Registry für Interceptoren, dem der {@code LocaleChangeInterceptor}
     *                 hinzugefügt wird.
     */
    @Override
    public void addInterceptors( InterceptorRegistry registry ) {
        
        registry.addInterceptor( _localeInterceptor );
    }

}
