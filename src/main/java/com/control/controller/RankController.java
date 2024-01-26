package com.control.controller;

import com.control.dto.SalaryDto;
import com.control.entity.Rank;
import com.control.entity.Salary;
import com.control.service.RankService;
import com.control.service.SalaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rank")
public class RankController {
    private final RankService service;

    public RankController(RankService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public String add(@RequestBody Rank rank){

        return service.addRank(rank);
    }
    @GetMapping("/show")
    public List<Rank> show(){

        return service.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public String del(@PathVariable Long id)
    {
        return service.deleteRank(id);
    }
}
