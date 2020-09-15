package ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>${dir?replace("/", ".")};

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private ${columnDTO.javaType} ${columnDTO.columnName};

        <#elseif columnDTO.columnName == 'isDeleted'>
    /**
     * ${columnDTO.columnComment}
     */
    @TableLogic(value = "0", delval = "1")
    private Boolean deleted;

        <#else>
    /**
     * ${columnDTO.columnComment}
     */
    private ${columnDTO.javaType} ${columnDTO.columnName};

        </#if>
    </#list>
    </#if>

}
