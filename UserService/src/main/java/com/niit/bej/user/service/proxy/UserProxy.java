package com.niit.bej.user.service.proxy;

import com.niit.bej.user.service.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-auth-proxy")
public interface UserProxy {
    @PostMapping("home/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto);

    @PutMapping("home/updateUser/{email}")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @PathVariable String email);
}
