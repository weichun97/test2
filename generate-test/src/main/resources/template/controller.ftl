package ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>${dir?replace("/", ".")};

import com.itran.fgoc.common.core.api.Response;
import com.itran.fgoc.common.mybatisplus.FgocPage;
import com.itran.fgoc.common.mybatisplus.PageParam;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.param.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}PageParam;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.param.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}SaveParam;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.param.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}UpdateParam;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.vo.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}PageVO;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.vo.${tableInfo.tableName?lower_case}.${tableInfo.tableNameCamelCase}VO;
import ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>service.${tableInfo.tableNameCamelCase}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author chun
 * @date 2020/9/4
 * @description ${tableInfo.tableComment}
 */
@RequestMapping("${tableInfo.tableName?replace("_", "-")}")
@RestController
@Api(value = "${tableInfo.tableNameCamelCase}Controller", tags = "${tableInfo.tableComment}")
public class ${tableInfo.tableNameCamelCase}Controller {

    @Autowired
    private ${tableInfo.tableNameCamelCase}Service service;

    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取")
    public Response<${tableInfo.tableNameCamelCase}VO> ${tableInfo.tableNameCamelCase?uncap_first}(@PathVariable Long id) {
        return Response.success(service.${tableInfo.tableNameCamelCase?uncap_first}(id));
    }

    @GetMapping("page")
    @ApiOperation(value = "分页查询")
    public Response<FgocPage<${tableInfo.tableNameCamelCase}PageVO>> ${tableInfo.tableNameCamelCase?uncap_first}Page(PageParam page, ${tableInfo.tableNameCamelCase}PageParam param) {
        return Response.success(service.${tableInfo.tableNameCamelCase?uncap_first}Page(page, param));
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public Response save(@Validated @RequestBody ${tableInfo.tableNameCamelCase}SaveParam param) {
        service.save(param);
        return Response.success();
    }

    @PutMapping("{id}")
    @ApiOperation(value = "修改")
    public Response update(@Validated @RequestBody ${tableInfo.tableNameCamelCase}UpdateParam param, @PathVariable Long id) {
        service.update(param, id);
        return Response.success();
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据id删除")
    public Response deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return Response.success();

    }
}
