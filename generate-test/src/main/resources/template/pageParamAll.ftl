package ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>${dir?replace("/", ".")}.${tableInfo.tableNameCamelCase?lower_case};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chun
 * @date 2020/9/4
 * @description ${tableInfo.tableComment}
 */
@Data
@ApiModel(value = "${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.param.${tableInfo.tableNameCamelCase?lower_case}.${tableInfo.tableNameCamelCase}PageParam")
public class ${tableInfo.tableNameCamelCase}PageParam implements Serializable{

    private static final long serialVersionUID = 1L;

<#if tableInfo.columnDtos?exists && tableInfo.columnDtos?size gt 0>
    <#list tableInfo.columnDtos as columnDTO >
    @ApiModelProperty(value = "${columnDTO.columnComment}", example = "")
    private ${columnDTO.javaType} ${columnDTO.columnName};

    </#list>
</#if>
}