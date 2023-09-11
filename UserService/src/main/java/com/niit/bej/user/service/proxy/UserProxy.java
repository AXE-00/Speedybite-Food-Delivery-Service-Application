package com.niit.bej.user.service.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-auth-proxy")
public interface UserProxy {
}
