package kanban_board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(requests -> requests
                        .mvcMatchers("/").permitAll()
                        .mvcMatchers("/home").permitAll()
                        .mvcMatchers("/contact").permitAll()
                        .mvcMatchers("/about").permitAll()
                        .mvcMatchers("/login").permitAll()
                        .mvcMatchers("/signup").permitAll()
                        .mvcMatchers("/updateBoardTitle/**").hasAnyRole("ADMIN", "SUPERADMIN")
                        .mvcMatchers("/createNewBoard/**").hasAnyRole("ADMIN", "SUPERADMIN")
                        .mvcMatchers("/saveBoard/**").hasAnyRole("ADMIN", "SUPERADMIN")
                        .mvcMatchers("/deleteBoard/**").hasAnyRole("ADMIN", "SUPERADMIN")
                        .mvcMatchers("/**").hasAnyRole("USER", "ADMIN", "SUPERADMIN")
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/loggedin", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login"));

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder passwordEncoder
                = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth.inMemoryAuthentication()

                .withUser("user")
                .password(passwordEncoder.encode("user"))
                .roles("USER")

                .and()
                .withUser("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("USER", "ADMIN")

                .and()
                .withUser("superadmin")
                .password(passwordEncoder.encode("superadmin"))
                .roles("USER", "ADMIN", "SUPERADMIN");

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/**/*.jpg")
                .antMatchers("/**/*.js")
                .antMatchers("/static/**")
                .antMatchers("/**/*.css")
                .antMatchers("/h2-console/**");
    }
}