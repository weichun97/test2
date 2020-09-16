package ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>${dir?replace("/", ".")};

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.po.${tableInfo.tableNameCamelCase};
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>mapper.${tableInfo.tableNameCamelCase}Mapper;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>maps.${tableInfo.tableNameCamelCase}Maps;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>service.${tableInfo.tableNameCamelCase}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author chun
 * @date 2020/9/4
 * @description
 */
@Service
public class ${tableInfo.tableNameCamelCase}ServiceImpl extends ServiceImpl<${tableInfo.tableNameCamelCase}Mapper, ${tableInfo.tableNameCamelCase}> implements ${tableInfo.tableNameCamelCase}Service {

    @Autowired
    private ${tableInfo.tableNameCamelCase}Maps maps;
}
