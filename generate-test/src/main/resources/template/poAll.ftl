package ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>${dir?replace("/", ".")};

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chun
 * @date 2020/8/12 15:51
 */
@Data
@TableName("${tableInfo.tableName}")
public class ${tableInfo.tableNameCamelCase} implements Serializable {

	private static final long serialVersionUID = 1L;

    <#if tableInfo.columnDtos?exists && tableInfo.columnDtos?size gt 0>
    <#list tableInfo.columnDtos as columnDTO >
        <#if columnDTO.columnName == 'id'>
    /**
     * ${columnDTO.columnComment}
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "${columnDTO.oldColumnName}")
    private ${columnDTO.javaType} ${columnDTO.columnName};

        <#elseif columnDTO.columnName == 'deleteTime'>
    /**
     * ${columnDTO.columnComment}
     */
    @TableField(value = "${columnDTO.oldColumnName}")
    @TableLogic(value = "0", delval = "UNIX_TIMESTAMP()")
    private Long deleteTime;

        <#else>
    /**
     * ${columnDTO.columnComment}
     */
    @TableField(value = "${columnDTO.oldColumnName}")
    private ${columnDTO.javaType} ${columnDTO.columnName};

        </#if>
    </#list>
    </#if>

}
