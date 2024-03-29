package kz.alim.hotel.config;

import kz.alim.hotel.MyUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    MyUserDetailsService userService;

    public SecurityConfig(MyUserDetailsService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .logout( log -> {log.logoutUrl("/api/auth/logout");})
                .httpBasic().and()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/auth/signin").authenticated()
                    .antMatchers("/api/clerk/**").hasAnyRole(MyUserDetailsService.ROLE_CLERK, MyUserDetailsService.ROLE_MANAGER)
                    .antMatchers("/api/manager/**", "/admin/manager/**").hasRole(MyUserDetailsService.ROLE_MANAGER)
                    .anyRequest().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }
}
