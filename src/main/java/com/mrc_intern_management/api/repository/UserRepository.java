package com.mrc_intern_management.api.repository;

import com.mrc_intern_management.api.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

}
