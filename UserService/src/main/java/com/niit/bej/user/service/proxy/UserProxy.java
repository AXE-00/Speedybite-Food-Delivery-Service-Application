package com.niit.bej.user.service.proxy;

import com.niit.bej.user.service.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USER-AUTH-SERVICE")
public interface UserProxy {
    @PostMapping("api/v1/auth/register")
    public ResponseEntity<?> registerNewUser(@RequestBody UserDto userDto);

    @PutMapping("api/v1/auth/updateUser/{email}")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @PathVariable String email);
}
