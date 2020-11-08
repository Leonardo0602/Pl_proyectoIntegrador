package com.facaaprende.PI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import com.facaaprende.PI.exception.AuthException;

//Indica los recursos autenticados y liberados del aplicatico
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	//Trae todo lo que configuramos en el SecurityConfig
	@Autowired
    private ResourceServerTokenServices tokenServices;
	
	
    @Value("${security.jwt.resource-ids}")
    private String resourceIds;
    
    //De donde se van a crear los tokens y la configuración del resourcesIds
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceIds).tokenServices(tokenServices);
    }
    
    //Url que vamos a proteger y como
    @Override
    public void configure(HttpSecurity http) throws Exception {
                http
                .exceptionHandling().authenticationEntryPoint(new AuthException())
                .and()
                .requestMatchers()
                .and()
                .authorizeRequests()                  
                .antMatchers("/archivos/profesor/**").authenticated()
                .antMatchers("/personas/persona/**").authenticated()
                .antMatchers("/consultas/**").authenticated()
                .antMatchers("/nucleos/**").authenticated()
                .antMatchers("/publicaciones/**").authenticated()
                .antMatchers("/tipos/**").authenticated()
                .antMatchers("/roles/**").permitAll()
                .antMatchers("/tokens/**" ).permitAll();         
    }    

}
