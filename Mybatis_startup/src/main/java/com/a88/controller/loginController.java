package com.a88.controller;

import com.a88.Pojo.employee;
import com.a88.Pojo.result;
import com.a88.service.inter.empService;
import com.a88.utils.Jwtutil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class loginController {

    @Autowired
    private empService empService;


    @PostMapping("/login")
    public result login(@RequestBody employee emp) {
        log.info("employee: {}", emp);
        employee e =  empService.login(emp);

        // login successful, generate JWT, give JWT
        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("FullName", e.getFullName());
            claims.put("Username", e.getUsername());

            String jwt = Jwtutil.generateJwt(claims); // jwt 包含了當前登錄的員工信息
            return result.success(jwt);
        }


        return result.error("username or password is incorrect");
    }

}
