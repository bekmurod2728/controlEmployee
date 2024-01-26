package com.control.repository;

import com.control.entity.Employee;
import com.control.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    public List<Employee> findByRank(Rank rank);
}
