package ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>${dir?replace("/", ".")};

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.po.${tableInfo.tableNameCamelCase};

/**
 * @author chun
 * @date 2020/8/12 15:51
 */
@Mapper
public interface ${tableInfo.tableNameCamelCase}Mapper extends BaseMapper<${tableInfo.tableNameCamelCase}> {

}