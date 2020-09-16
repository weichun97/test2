package ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>${dir?replace("/", ".")};

import com.baomidou.mybatisplus.extension.service.IService;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.po.${tableInfo.tableNameCamelCase};

/**
 * @author chun
 * @date 2020/9/4
 * @description
 */
public interface ${tableInfo.tableNameCamelCase}Service extends IService<${tableInfo.tableNameCamelCase}> {

}
