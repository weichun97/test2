package com.github.weichun97.generatetest.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chun
 * @date 2020/8/12 15:51
 */
@Data
@TableName("group")
public class Group implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 组id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 父组id
     */
    private Long parentId;
    /**
     * 组名称
     */
    private String name;
    /**
     * 简介
     */
    private String description;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date updatedTime;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 更新人
     */
    private String updatedBy;

}
