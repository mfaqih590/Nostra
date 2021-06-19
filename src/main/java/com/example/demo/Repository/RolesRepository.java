package com.example.demo.Repository;

import com.example.demo.Model.ModelRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<ModelRole, Integer> {

}
