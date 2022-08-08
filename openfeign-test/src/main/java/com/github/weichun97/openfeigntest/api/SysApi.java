package com.github.weichun97.openfeigntest.api;

import com.github.weichun97.openfeigntest.api.impl.SysApiImpl;
import com.github.weichun97.openfeigntest.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "fgoc-sys-service", fallback = SysApiImpl.class, configuration = FeignConfiguration.class)
public interface SysApi {

    @DeleteMapping("{id}")
    String deleteById(@PathVariable("id") Long id);
}
