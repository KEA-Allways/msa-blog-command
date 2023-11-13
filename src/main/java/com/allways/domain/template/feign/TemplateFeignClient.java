package com.allways.domain.template.feign;

import com.allways.domain.template.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msa-user-command", url ="http://api/users" )
public interface TemplateFeignClient {
    @GetMapping("/{seq}")
    User getUserById(@PathVariable Long seq);
}
