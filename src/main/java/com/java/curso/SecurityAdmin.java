package com.java.curso;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Order(2)
public class SecurityAdmin {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected UserDetailsManager auth(DataSource dataSource) {
        JdbcUserDetailsManager auth = new JdbcUserDetailsManager(dataSource);
        
		auth.setUsersByUsernameQuery("select email as username, senha as password, 1 as enable from funcionario where email=?");
		auth.setAuthoritiesByUsernameQuery("select funcionario.email as username, perfil.nome as authority from permissoes inner join funcionario on funcionario.id=permissoes.funcionario_id inner join perfil on perfil.id=permissoes.perfil_id where funcionario.email=?");
		//auth.setPasswordEncoder(new BCryptPasswordEncoder());		
		return auth;
	}	
    
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	   http.authorizeHttpRequests( (authorize) -> authorize
			 //.requestMatchers("/**").permitAll()
			 .requestMatchers("/administrativo/**").hasAnyAuthority("gerente", "vendedor")		//.requestMatchers("/administrativo/**").hasAuthority("gerente")	
			 .anyRequest().authenticated()
	   ).formLogin( (form) -> form
	         .loginPage("/login")
	         .defaultSuccessUrl("/administrativo", true)
	         .failureUrl("/login-error")
	         .permitAll()
	    ).logout( (logout) -> logout
		     .logoutSuccessUrl("/logout")
		     .invalidateHttpSession(true)
	         .deleteCookies("JSESSIONID")
	         .permitAll()
	    ).exceptionHandling( (ex) -> ex
	         .accessDeniedPage("/negado")
	    ).csrf()
	  		.ignoringRequestMatchers("/**")
	  	;	   	
	   	return http.build();
	}
}
