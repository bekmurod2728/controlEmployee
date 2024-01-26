package com.control.controller;

import com.control.entity.Rank;
import com.control.entity.User;
import com.control.service.RankService;
import com.control.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public String add(@RequestBody User user){

        return service.addUser(user);
    }
    @PutMapping("/update")
    public String update(@RequestBody User user){
        return service.updateUser(user);
    }
//    @GetMapping("/show")
//    public List<Rank> show(){
//
//        return service.getAll();
//    }

    @DeleteMapping("/delete/{id}")
    public String del(@PathVariable Long id)
    {
        return service.deleteUser(id);
    }
}
