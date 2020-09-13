package com.github.weichun97.generatetest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.weichun97.generatetest.mapper.GroupMapper;
import com.github.weichun97.generatetest.entity.po.Group;
import com.github.weichun97.generatetest.service.GroupService;
import org.springframework.stereotype.Service;

/**
 * @author chun
 * @date 2020/8/12 15:54
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {


}
