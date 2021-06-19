package com.example.demo.mapper;

import java.util.List;

import com.example.demo.Model.ModelRole;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface rolesMapper {
    List<ModelRole> testFind();
}
