package org.fasttrackit.ProjectApp.service;

import org.fasttrackit.ProjectApp.dao.RoleDao;
import org.fasttrackit.ProjectApp.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;
    public Role createNewRole(Role role){
       return roleDao.save(role);

    }
}
