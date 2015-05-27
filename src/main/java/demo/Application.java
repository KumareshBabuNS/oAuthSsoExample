package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.security.oauth2.sso.EnableOAuth2Sso;
import org.springframework.cloud.security.oauth2.sso.OAuth2SsoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

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
     * Required to fine-tune the OAuth 2 SSO behavior.
     */
	@Configuration
	protected static class OauthConfig extends OAuth2SsoConfigurerAdapter {

		/**
		 *	We want the "protected" resource to require OAuth 2 SSO. 
		 */
		@Override
	    public void match(RequestMatchers matchers) {
	        matchers.antMatchers("/protected/**");
	    }

		/**
		 *	We want the "unprotected" resource to require no authentication at all. 
		 */
		@Override
		public void configure(HttpSecurity http) throws Exception {
			  http
			  	.authorizeRequests()
			  		.antMatchers("/protected/**").authenticated()
			  		.antMatchers("/unprotected/**").permitAll()
			  		;	    
		}
	}
}
