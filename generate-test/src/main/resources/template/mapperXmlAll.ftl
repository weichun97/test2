<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>${dir?replace("/", ".")}.${tableInfo.tableNameCamelCase}Mapper">

    <select id="${tableInfo.tableNameCamelCase?uncap_first}Page" resultType="${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.vo.${tableInfo.tableNameCamelCase?lower_case}.${tableInfo.tableNameCamelCase}PageVO">
        select
        <#list tableInfo.columnDtos as columnDTO >
            t1.${columnDTO.oldColumnName}<#if columnDTO_has_next>,</#if>
        </#list>
        from ${tableInfo.tableName} t1
        where 1=1
        order by t1.create_time desc
    </select>
</mapper>