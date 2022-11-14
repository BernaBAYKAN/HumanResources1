package org.team3.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthServiceSecurityConfig {


    /**
     * Sunucunuza gelen tüm isteklerin konfigürasyonunu burada yapıyorsunuz
     * */
    @Bean
    JwtTokenFilter getJwtTokenFilter(){
        return new JwtTokenFilter();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        /**
         * olmasını istediğimiz tüm ayarlamaları burada yapıp
         * */
        // 1- Tüm isteklerde csrf kapalı olsun
        http.csrf().disable();
        /** 1- anyRequest -> gelen tüm istekler
            2- authenticated -> kimlik doğrulaması yapılsın
            3- permitall -> kimlik doğrulamasına tabi tutma*/
        http.authorizeRequests()
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/v1/api/auth/dologin","/v1/api/auth/register").permitAll()
                .anyRequest().authenticated();

        /**1- Eğer kimlik doğrulaması yapması gerekiyor ise bunu springin login formu üzerinden yap*/
        http.formLogin();
        /**
         * JWT ile gelen isteklerin doğrulamasının yapılmasını talep ettiğimiz kısım burada gelen isteğin
         * cevabı verilmeden filter araya sokularak doğrulama mekanizması burada işlenmelidir*/
        http.addFilterBefore(getJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class); //todo parametre gelecek


        /**
        http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/v3/api/docs/**","/swagger-ui/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin();   */

        /**
         * gelen isteği yeni kurguladığımız şekli ile springe iletiyoruz
         * */


        return http.build();
    }


}
