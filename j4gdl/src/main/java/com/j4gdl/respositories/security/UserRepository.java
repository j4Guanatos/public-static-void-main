package com.j4gdl.respositories.security;

import com.j4gdl.model.security.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Emmanuel_Garcia on 3/30/2017.
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
}
