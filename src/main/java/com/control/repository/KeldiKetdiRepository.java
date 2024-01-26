package com.control.repository;

import com.control.entity.Employee;
import com.control.entity.KeldiKetdi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeldiKetdiRepository extends JpaRepository<KeldiKetdi,Long> {
}
