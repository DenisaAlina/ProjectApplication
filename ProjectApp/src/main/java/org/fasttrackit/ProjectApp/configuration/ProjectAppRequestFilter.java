package org.fasttrackit.ProjectApp.configuration;

import io.jsonwebtoken.ExpiredJwtException;
import org.fasttrackit.ProjectApp.service.ProjectAppService;
import org.fasttrackit.ProjectApp.util.ProjectAppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ProjectAppRequestFilter extends OncePerRequestFilter {

    @Autowired
    private ProjectAppUtil projectAppUtil;

    @Autowired
    private ProjectAppService projectAppService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
       final String header= request.getHeader("Authorization");

       String userName=null;
        String projectAppToken=null;
        if(header != null && header.startsWith("Bearer ")){
          projectAppToken= header.substring(7);

           try{
             userName =  projectAppUtil.getUserNameFromToken(projectAppToken);

           }catch (IllegalArgumentException e){
               System.out.println("Unable to get JWT token");
           }catch (ExpiredJwtException e){
               System.out.println(" Jwt token is expired");
           }
       }else{
           System.out.println("Jwt token does not start with Bearer");
       }
       if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
          UserDetails userDetails=  projectAppService.loadUserByUsername(userName);

          if(projectAppUtil.validateToken(projectAppToken, userDetails)){

             UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                     new UsernamePasswordAuthenticationToken(userDetails,
                      null,
                      userDetails.getAuthorities());
             usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

             SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
          }
       }
       filterChain.doFilter(request, response);
    }
}
