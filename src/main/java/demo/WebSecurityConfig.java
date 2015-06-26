package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * Created by DanielY on 6/24/2015.
 */
@Configuration
@EnableWebMvcSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*@Autowired
    private DataSource dataSource;*/
    @Autowired
    UserDetailsService userDS;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index.html", "/registerPage.html", "/assets/**", "/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/welcome", true)
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                /*.jdbcAuthentication()
                .dataSource(DbManager.getDataSource())
                .usersByUsernameQuery("select * from users where Username ?");*/
                /*.jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema();*/

                /*.inMemoryAuthentication()
                .withUser("user@user.com").password("password").roles("USER");*/
                .userDetailsService(userDS);
    }

    @Override
    protected UserDetailsService userDetailsService(){
        return userDS;
    }

}
