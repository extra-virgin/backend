package com.example.tinkofftradingrobot.config;

//import com.example.tinkofftradingrobot.config.security.ApiAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private ApiAuthenticationProvider apiAuthenticationProvider;
//    public SecurityConfig(ApiAuthenticationProvider apiAuthenticationProvider) {
//        this.apiAuthenticationProvider = apiAuthenticationProvider;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // enable CORS support
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
        // http basic authentication
        http.httpBasic().disable();
        // disable cross site request forgery
        http.csrf().disable();
        // disable session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/api/user/create").permitAll()
                .anyRequest().authenticated();
    }

    private RequestMatcher getRequestMatchers() {
        return new OrRequestMatcher(new AntPathRequestMatcher("/**"));
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/api/user/create");
//    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(apiAuthenticationProvider);
//    }
}
