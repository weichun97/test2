package com.github.weichun97.mysqlreplicationtest.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author chun
 * @date 2022/3/7 15:18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Date createTime;
    private Date updateTime;
}
