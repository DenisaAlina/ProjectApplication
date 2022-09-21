package org.fasttrackit.ProjectApp.dao;

import org.fasttrackit.ProjectApp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository <User, String> {

}
