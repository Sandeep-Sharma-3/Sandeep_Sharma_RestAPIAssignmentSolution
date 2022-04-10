package employeeManagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import employeeManagement.serviceImpl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService getMyUserDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(getMyUserDetailsService()).passwordEncoder(getPasswordEncoder());
		//.and().
		//inMemoryAuthentication().withUser("sandeep").password(getPasswordEncoder().encode("admin123")).roles("ADMIN");
		//auth.userDetailsService(getMyUserDetailsService()).passwordEncoder(getPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/employeeManagement/addSingleEmployee", "/employeeManagement/addAllEmployees","/employeeManagement/addSingleRole","/employeeManagement/addAllRoles","/employeeManagement/addSingleUser","/employeeManagement/addAllUsers").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/employeeManagement/deleteEmployeeById/*").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.PUT,"/employeeManagement/updateEmployeeDetails").hasAnyAuthority("ADMIN")
				.antMatchers(HttpMethod.GET, "/employees/*").hasAnyAuthority("USER","ADMIN")
				.anyRequest().authenticated().and().httpBasic()
				.and()
	            //.formLogin().loginProcessingUrl("/login").successForwardUrl("http://localhost:8080/swagger-ui.html#/")
	            //.and()
	            //.logout().logoutSuccessUrl("/login").permitAll()
	            //.and()
	            .exceptionHandling().accessDeniedPage("/student/403")
				.and().cors().and().csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/*");
	}

}
