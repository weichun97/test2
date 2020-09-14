package ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>${dir?replace("/", ".")};

import com.baomidou.mybatisplus.extension.service.IService;
import com.itran.fgoc.common.mybatisplus.FgocPage;
import com.itran.fgoc.common.mybatisplus.PageParam;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.param.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}PageParam;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.param.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}SaveParam;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.param.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}UpdateParam;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.po.${tableInfo.tableNameCamelCase};
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.vo.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}VO;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.vo.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}PageVO;

/**
 * @author chun
 * @date 2020/9/4
 * @description
 */
public interface ${tableInfo.tableNameCamelCase}Service extends IService<${tableInfo.tableNameCamelCase}> {

    /**
     * 保存数据
     *
     * @param param
     */
    void save(${tableInfo.tableNameCamelCase}SaveParam param);

    /**
     * 修改数据
     *
     * @param param
     * @param id
     */
    void update(${tableInfo.tableNameCamelCase}UpdateParam param, Long id);

    /**
     * 分页查询
     *
     * @param page
     * @param param
     * @return
     */
    FgocPage<${tableInfo.tableNameCamelCase}PageVO> ${tableInfo.tableName?lower_case}Page(PageParam page, ${tableInfo.tableNameCamelCase}PageParam param);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    ${tableInfo.tableNameCamelCase}VO ${tableInfo.tableName?lower_case}(Long id);


    /**
     * 根据id删除数据
     *
     * @param id
     * @return
     */
    void deleteById(Long id);
}
