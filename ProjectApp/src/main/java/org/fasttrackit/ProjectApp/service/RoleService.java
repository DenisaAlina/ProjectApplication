package org.fasttrackit.ProjectApp.service;

import org.fasttrackit.ProjectApp.repository.RoleRepository;
import org.fasttrackit.ProjectApp.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public Role createNewRole(Role role){
       return roleRepository.save(role);

    }
}
