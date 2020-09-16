package ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>${dir?replace("/", ".")};

import com.itran.fgoc.common.mybatisplus.FgocPage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.param.${tableInfo.tableNameCamelCase?lower_case}.${tableInfo.tableNameCamelCase}PageParam;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.po.${tableInfo.tableNameCamelCase};
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.vo.${tableInfo.tableNameCamelCase?lower_case}.${tableInfo.tableNameCamelCase}PageVO;

/**
 * @author chun
 * @date 2020/8/12 15:51
 */
@Mapper
public interface ${tableInfo.tableNameCamelCase}Mapper extends BaseMapper<${tableInfo.tableNameCamelCase}> {

    FgocPage<${tableInfo.tableNameCamelCase}PageVO> ${tableInfo.tableNameCamelCase?uncap_first}Page(FgocPage fgocPage, ${tableInfo.tableNameCamelCase}PageParam param);
}
