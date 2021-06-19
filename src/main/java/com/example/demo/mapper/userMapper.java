package com.example.demo.mapper;

import com.example.demo.Model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface userMapper {
    List<User> testFind();
}
