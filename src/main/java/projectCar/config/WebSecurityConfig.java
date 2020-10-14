package projectCar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/","/signup").not().fullyAuthenticated()
                .anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
                .and().logout().permitAll().logoutSuccessUrl("/");
    }

}
