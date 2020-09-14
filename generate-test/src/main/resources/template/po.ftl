package ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>${dir?replace("/", ".")};

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
@TableName("${tableInfo.tableName}")
public class ${tableInfo.tableNameCamelCase} implements Serializable {

	private static final long serialVersionUID = 1L;

    <#if tableInfo.columnDtos?exists && tableInfo.columnDtos?size gt 0>
    <#assign a=0/>
    <#list tableInfo.columnDtos as columnDTO >
        <#if a == 0>
    /**
     * ${columnDTO.columnComment}
     */
    @TableId(type = IdType.AUTO)
    private ${columnDTO.javaType} ${columnDTO.columnName};
            <#else>
    /**
     * ${columnDTO.columnComment}
     */
    private ${columnDTO.javaType} ${columnDTO.columnName};
        </#if>
        <#assign a++/>
    </#list>
    </#if>

}
