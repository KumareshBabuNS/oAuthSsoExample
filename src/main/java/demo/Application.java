package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.security.oauth2.sso.EnableOAuth2Sso;
import org.springframework.cloud.security.oauth2.sso.OAuth2SsoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableOAuth2Sso
public class Application extends SpringBootServletInitializer {

	/**
     * Required for JAR deployment
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Required for WAR deployment
     */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}
    
	@Configuration
	protected static class OauthConfig extends OAuth2SsoConfigurerAdapter {
	    @Override
	    public void match(RequestMatchers matchers) {
	        matchers.antMatchers("/unprotected/**");
	    }

		@Override
		public void configure(HttpSecurity http) throws Exception {
			  http
			  	.authorizeRequests()
			  		.antMatchers("/protected/**").authenticated();	    
		}
	}

//	@Configuration
//	protected static class SecurityConfig extends WebSecurityConfigurerAdapter {
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//		  http
//		  	.authorizeRequests()
//		  		.antMatchers("/entry").permitAll()
////		  		.antMatchers("/greeting/**").authenticated()
//		  		;
//		}		
//	}
	
}
