package com.github.weichun97.mysqlreplicationtest.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.weichun97.mysqlreplicationtest.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chun
 * @date 2022/3/7 15:42
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
