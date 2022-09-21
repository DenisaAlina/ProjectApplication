package org.fasttrackit.ProjectApp.dao;

import org.fasttrackit.ProjectApp.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, String> {
}
