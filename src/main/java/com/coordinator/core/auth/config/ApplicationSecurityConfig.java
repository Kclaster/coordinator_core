package com.coordinator.core.auth.config;

import com.coordinator.core.auth.filters.AccessDeniedFilter;
import com.coordinator.core.auth.filters.AuthenticationEntryPointFilter;
import com.coordinator.core.auth.filters.JwtTokenVerifier;
import com.coordinator.core.auth.filters.JwtUsernameAndPasswordJwtFilter;
import com.coordinator.core.auth.service.AuthUserServiceImpl;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import java.util.Arrays;

import static com.coordinator.core.auth.ApplicationUserRole.COORDINATOR;
import static com.coordinator.core.auth.ApplicationUserRole.ADMIN;
import static com.coordinator.core.auth.ApplicationUserRole.USER;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthUserServiceImpl authUserServiceImpl;
    private final PasswordEncoder passwordEncoder;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;
    private final AccessDeniedFilter accessDeniedFilter;
    private final AuthenticationEntryPointFilter authenticationEntryPointFilter;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,
                                     AuthUserServiceImpl authUserServiceImpl,
                                     SecretKey secretKey,
                                     JwtConfig jwtConfig,
                                     AccessDeniedFilter accessDeniedFilter,
                                     AuthenticationEntryPointFilter authenticationEntryPointFilter) {
        this.passwordEncoder = passwordEncoder;
        this.authUserServiceImpl = authUserServiceImpl;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
        this.accessDeniedFilter = accessDeniedFilter;
        this.authenticationEntryPointFilter = authenticationEntryPointFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/", "index", "/css/*", "/js/*", "/api/v1/auth/register").permitAll()
                .antMatchers("/api/**").hasAnyRole(COORDINATOR.name(), ADMIN.name(), USER.name())
                    .anyRequest()
                    .authenticated()
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .exceptionHandling()
                    .accessDeniedHandler(accessDeniedFilter)
                    .authenticationEntryPoint(authenticationEntryPointFilter)
                    .and()
                .addFilter(jwtAuthorizationFilter())
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordJwtFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://coordinate-core.herokuapp.com", "http://localhost:3000"));
        configuration.setAllowedMethods(ImmutableList.of("HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type", "access-control-allow-origin"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(authUserServiceImpl);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    public JwtUsernameAndPasswordJwtFilter jwtAuthorizationFilter() throws Exception {
        JwtUsernameAndPasswordJwtFilter jwtAuthenticationFilter = new JwtUsernameAndPasswordJwtFilter(authenticationManager(), jwtConfig, secretKey);
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/v1/auth/login");
        return jwtAuthenticationFilter;
    }

}