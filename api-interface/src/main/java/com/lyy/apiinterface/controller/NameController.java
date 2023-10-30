package com.lyy.apiinterface.controller;


import com.lyy.apiclientsdk.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/name")
public class NameController {

    @PostMapping("/user")
    public String getName(@RequestBody User user) {
        return "你的名字是" + user.getName();
    }
}
