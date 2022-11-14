package org.team3.config.security;

import org.mehmet.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetails implements UserDetailsService {

    @Autowired
    AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }


    public UserDetails loadUserByUserId(Long authId) throws UsernameNotFoundException {
        /**
         * id si verilen kullanıcının var olup olmadığına bakılmalıdır
         * */
        boolean isUserExist = authService.findById(authId).getId() != null;
        if (isUserExist){
            /**
             * Burada oluşturulan kullanıcı, hangi sayfalara giriş yapabileceğinin anlaşılabilmesi kontrol edilebilmesi
             * için bir yetki listesinin olmasına gerek vardır. Bu nedenle "USER", "ADMIN" vs gibi listeyi burada belirtmeliyiz
             */
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
            return  User.builder()
                    .username(authId.toString())
                    .password("")
                    .accountExpired(false)
                    .accountLocked(false)
                    .authorities("USER")
                    .build();

        }
        return null;
    }
}
