package projectCar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("userServiceImpl")
    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/").not().fullyAuthenticated()
                .antMatchers("/**").hasRole("USER").anyRequest().authenticated().and();
        http.formLogin().loginPage("/login")
                .loginProcessingUrl("/login-check").failureUrl("/error")
                .usernameParameter("check_username").passwordParameter("check_password").defaultSuccessUrl("/user").permitAll();
        http.logout().permitAll().logoutUrl("/logout").logoutSuccessUrl("/login?logout").invalidateHttpSession(true)
                .and();
//        http.authorizeRequests().anyRequest().hasAnyRole("USER").and()
//                .authorizeRequests().antMatchers("/").permitAll().and()
//                .formLogin().loginPage("/login").loginProcessingUrl("/loginAction").permitAll().and()
//                .logout().logoutSuccessUrl("/login").permitAll().and().csrf().disable();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder authentication) throws Exception{
        authentication.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

}
