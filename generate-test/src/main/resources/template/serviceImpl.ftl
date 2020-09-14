package ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>${dir?replace("/", ".")};

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>common.core.api.SqlCode;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>common.core.exception.ApiException;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>common.mybatisplus.FgocPage;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>common.mybatisplus.PageParam;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>common.mybatisplus.ems.YesOrNoEnum;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.param.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}PageParam;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.param.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}SaveParam;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.param.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}UpdateParam;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.po.${tableInfo.tableNameCamelCase};
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.vo.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}sVO;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.vo.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}VO;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.vo.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}PageVO;
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

    @Override
    public void save(${tableInfo.tableNameCamelCase}SaveParam param) {
        ${tableInfo.tableNameCamelCase} ${tableInfo.tableName?lower_case} = maps.paramToPo(param);
        ${tableInfo.tableName?lower_case}.setCreateTime(new Date());
        int i = baseMapper.insert(${tableInfo.tableName?lower_case});
        if (i < 1) {
            throw new ApiException(SqlCode.INSERT_ERROR);
        }
    }

    @Override
    public void update(${tableInfo.tableNameCamelCase}UpdateParam param, Long id) {
        ${tableInfo.tableNameCamelCase} ${tableInfo.tableName?lower_case} = maps.paramToPo(param);
        ${tableInfo.tableName?lower_case}.setId(id);
        ${tableInfo.tableName?lower_case}.setUpdateTime(new Date());
        baseMapper.updateById(${tableInfo.tableName?lower_case});
    }

    @Override
    public FgocPage<${tableInfo.tableNameCamelCase}PageVO> ${tableInfo.tableName?lower_case}Page(PageParam page, ${tableInfo.tableNameCamelCase}PageParam param) {
        FgocPage fgocPage = baseMapper.${tableInfo.tableName?lower_case}Page(FgocPage.getPage(page), param);
        if (!CollectionUtil.isEmpty(fgocPage.getRecords())) {
            fgocPage.setRecords(maps.poToPageVo(fgocPage.getRecords()));
        }
        return fgocPage;

    }

    @Nullable
    @Override
    public ${tableInfo.tableNameCamelCase}VO ${tableInfo.tableName?lower_case}(Long id) {
        ${tableInfo.tableNameCamelCase} ${tableInfo.tableName?lower_case} = this.getById(id);
        if (${tableInfo.tableName?lower_case} != null) {
            return maps.poToVo(${tableInfo.tableName?lower_case});
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        this.removeById(id);
    }
}
