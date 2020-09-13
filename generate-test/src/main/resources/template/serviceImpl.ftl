package ${packageName}.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${packageName}.mapper.${tableInfo.tableNameCamelCase}Mapper;
import ${packageName}.entity.po.${tableInfo.tableNameCamelCase};
import ${packageName}.service.${tableInfo.tableNameCamelCase}Service;
import org.springframework.stereotype.Service;

/**
 * @author chun
 * @date 2020/8/12 15:54
 */
@Service
public class ${tableInfo.tableNameCamelCase}ServiceImpl extends ServiceImpl<${tableInfo.tableNameCamelCase}Mapper, ${tableInfo.tableNameCamelCase}> implements ${tableInfo.tableNameCamelCase}Service {


}
