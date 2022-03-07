package com.github.weichun97.mysqlreplicationtest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.weichun97.mysqlreplicationtest.entity.mapper.UserMapper;
import com.github.weichun97.mysqlreplicationtest.entity.po.User;
import org.springframework.stereotype.Service;

/**
 * @author chun
 * @date 2022/3/7 15:40
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IService<User> {
}
