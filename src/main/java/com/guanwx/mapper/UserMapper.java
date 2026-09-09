package com.guanwx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guanwx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> selectListByName(@Param("name") String name);

    int updateById1(@Param("name") String name,
                    @Param("age") Integer age,
                    @Param("id") Integer id);

    List<User> selectListByNameAndAge(@Param("name") String name,
                                      @Param("age") Integer age);

}
