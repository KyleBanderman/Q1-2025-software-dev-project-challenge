package org.launchcode.backend.models.data;

import org.launchcode.backend.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
}
