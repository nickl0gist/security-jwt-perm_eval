package com.test.demo.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 31.07.2020
 *
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = getAuthentication(httpServletRequest);
        if (authentication == null) {
            // the token is invalid or is missing
            httpServletResponse.setStatus(401);
            httpServletResponse.getWriter().println("Invalid or missing token");
        } else {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    private Authentication getAuthentication(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(SecurityConstants.TOKEN_HEADER);
        if(token != null && !token.isEmpty() && token.startsWith(SecurityConstants.TOKEN_PREFIX)){
            try{
                byte[] key = SecurityConstants.JWT_SECRET.getBytes();

                Jws<Claims> parsedToken = Jwts.parser()
                        .setSigningKey(key)
                        .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""));

                String userName = parsedToken.getBody().getSubject();

                List<GrantedAuthority> authorityList = (List) parsedToken.getBody()
                        .get("rol", List.class).stream()
                        .map(authority -> new SimpleGrantedAuthority("ROLE_" + authority))
                        .collect(Collectors.toList());
                if (userName != null && !userName.isEmpty()){
                    return new UsernamePasswordAuthenticationToken(userName, null, authorityList);
                }
            } catch (Exception e){
                log.error(e.getMessage());
            }
        }
        return null;
    }
}
