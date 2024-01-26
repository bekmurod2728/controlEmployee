package com.control.service;

import com.control.entity.Rank;
import com.control.repository.EmployeeRepository;
import com.control.repository.RankRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankService {
    private final RankRepository rankRepository;
    private final EmployeeRepository employeeRepository;

    public RankService(RankRepository rankRepository, EmployeeRepository employeeRepository) {
        this.rankRepository = rankRepository;
        this.employeeRepository = employeeRepository;
    }

    public String addRank(Rank rank){
        Rank rank1=new Rank(rank.getEmployeeRank());
        rankRepository.save(rank1);
        return "saved";
    }

    public String deleteRank(Long id) {
        Rank rank = rankRepository.findById(id).orElse(null);
        if (rank != null){
            if (employeeRepository.findByRank(rank).isEmpty()) {
                rankRepository.deleteById(id);
                return "deleted";
            }
            return "this rank bind to employee";
        }
        return "dont deleted";
    }

    public List<Rank> getAll(){
        return rankRepository.findAll();
    }
}
