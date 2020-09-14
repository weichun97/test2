package ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>${dir?replace("/", ".")};

import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.param.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}SaveParam;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.param.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}UpdateParam;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.po.${tableInfo.tableNameCamelCase};
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.vo.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}PageVO;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.vo.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}VO;

import org.mapstruct.Mapper;
import java.util.List;

/**
 * @author chun
 * @date 2020/8/12 16:16
 */
@Mapper(componentModel = "spring")
public interface ${tableInfo.tableNameCamelCase}Maps {

    /**
     * param转换po
     *
     * @param param the param
     * @return ${tableInfo.tableName?lower_case} ${tableInfo.tableName?lower_case}
     */
    ${tableInfo.tableNameCamelCase} saveParamToPo(${tableInfo.tableNameCamelCase}SaveParam param);

    /**
     * param转换po
     *
     * @param param the param
     * @return ${tableInfo.tableName?lower_case} ${tableInfo.tableName?lower_case}
     */
    ${tableInfo.tableNameCamelCase} updateParamToPo(${tableInfo.tableNameCamelCase}UpdateParam param);

    /**
     * Po to vo ${tableInfo.tableName?lower_case} vo.
     *
     * @param ${tableInfo.tableName?lower_case} the ${tableInfo.tableName?lower_case}
     * @return the ${tableInfo.tableName?lower_case} vo
     */
    ${tableInfo.tableNameCamelCase}VO poToVo(${tableInfo.tableNameCamelCase} ${tableInfo.tableName?lower_case});

    /**
     * Po to page vo ${tableInfo.tableName?lower_case} page vo.
     *
     * @param ${tableInfo.tableName?lower_case} the ${tableInfo.tableName?lower_case}
     * @return the ${tableInfo.tableName?lower_case} page vo
     */
    ${tableInfo.tableNameCamelCase}PageVO poToPageVo(${tableInfo.tableNameCamelCase} ${tableInfo.tableName?lower_case});

    /**
     * Po to page vo list.
     *
     * @param ${tableInfo.tableName?lower_case}s the ${tableInfo.tableName?lower_case}s
     * @return the list
     */
    List<${tableInfo.tableNameCamelCase}PageVO> poToPageVo(List<${tableInfo.tableNameCamelCase}> ${tableInfo.tableName?lower_case}s);
}
