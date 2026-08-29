package com.guanwx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guanwx.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/25 星期二 01:39
 * @Description:
 * @Version: 1.0
 */

@Mapper
public interface UsersMapper extends BaseMapper<Users> {

    List<Users> getUsersByUsername(@Param("username") String username);

}
