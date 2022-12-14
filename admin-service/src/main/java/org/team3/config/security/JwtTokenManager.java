package org.team3.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {

    /**
     * 1- Create JWT
     * 2- Validate JWT
     * */
    public Optional<String> createToken(Long id){
        String token = null;

        //qAh~md_iJL-w?:5j3$CDgpT8l)Ze&%4\(~4RG#ruvE`cP%dX53e/$<K,=i1Z@4Z şifre anahtarı böyle olmalı
        String sifreAnahtari = "1234";

        try{
            /**
             * JWT içerisine hassas bilgilerinizi koymayınız. Örneğin şifre gibi
             * */
        token = JWT.create()
                .withAudience()
                .withClaim("id", id)
                .withIssuer("mehmet")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000* 1000))
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(sifreAnahtari));
        return Optional.of(token);

        }catch(Exception e){
            return Optional.empty();
        }
    }


    public Boolean validateToken(String token){

        try{
            Algorithm algorithm = Algorithm.HMAC256("1234");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("mehmet")
                    .build();
            DecodedJWT decode = verifier.verify(token);
            if (decode == null){
                return false;
            }

        }catch (Exception e){
            return false;
        }
        return true;

    }
    public Optional<Long> getUserId(String token){

        try{
            Algorithm algorithm = Algorithm.HMAC256("1234");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("mehmet")
                    .build();
            DecodedJWT decode = verifier.verify(token);
            if (decode == null)
                return Optional.empty();
            return Optional.of(decode.getClaim("id").asLong());

        }catch (Exception e){
            return Optional.empty();
        }

    }

    public String encryptPassword(String password){
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            /**
             * 1 -> 1
             * 2 -> 2
             * 10 -> A
             * 11 -> B
             * 12 -> C
             * 13 -> D
             * 14 -> E
             * 15 -> F
             */
            for (int i = 0; i < byteData.length; i++) {
                System.out.println("Bytedata.....: "+byteData[i]);
                sb.append(Integer
                        .toString((byteData[i] & 0xff) + 0x100, 16)
                        .substring(1));
                System.out.println("Integer data.....: "+ Integer
                        .toString((byteData[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            return sb.toString();
        }catch (Exception e){
            return null;
        }
    }
}
