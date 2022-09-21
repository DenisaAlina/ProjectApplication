package org.fasttrackit.ProjectApp.service;

import org.fasttrackit.ProjectApp.dao.UserDao;
import org.fasttrackit.ProjectApp.model.ProjectAppRequest;
import org.fasttrackit.ProjectApp.model.ProjectAppResponse;
import org.fasttrackit.ProjectApp.model.User;
import org.fasttrackit.ProjectApp.util.ProjectAppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ProjectAppService implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ProjectAppUtil projectAppUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ProjectAppResponse createProjectAppToken(ProjectAppRequest projectAppRequest) throws Exception {
        String userName = projectAppRequest.getUserName();
        String userPassword = projectAppRequest.getUserPassword();
        authenticate(userName, userPassword);
        UserDetails userDetails=loadUserByUsername(userName);
       String newGeneratedToken=projectAppUtil.generateToken(userDetails);
       User user=userDao.findById(userName).get();
       return new ProjectAppResponse(user, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findById(username).get();

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserPassword(),
                    getAuthorities(user)

            );
        } else {
            throw  new UsernameNotFoundException("Username is not valid");

        }
    }

    private Set getAuthorities(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet();
        user.getRole().forEach(role -> {
            authorities.add((new SimpleGrantedAuthority("ROLE_" + role.getRoleName())));
        });
        return authorities;
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }
}
