package org.fasttrackit.ProjectApp.service;

import org.fasttrackit.ProjectApp.repository.RoleRepository;
import org.fasttrackit.ProjectApp.repository.UserRepository;
import org.fasttrackit.ProjectApp.model.Role;
import org.fasttrackit.ProjectApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public User registerNewUser(User user){
      Role role = roleRepository.findById("User").get();
      Set<Role> roles = new HashSet<>();
      roles.add(role);
      user.setRole(roles);
      user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        return  userRepository.save(user);
    }

    public void initRolesAndUser(){
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleRepository.save(userRole);

        User adminUser= new User();
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        Set<Role> adminRoles= new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepository.save(adminUser);

//        User user= new User();
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@pass"));
//        Set<Role> userRoles= new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userRepository.save(user);
    }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }
}
