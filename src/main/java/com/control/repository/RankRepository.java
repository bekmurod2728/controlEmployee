package com.control.repository;

import com.control.entity.Employee;
import com.control.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<Rank,Long> {
}
