package com.Demeshkan.Kyrsach.Repository;

import com.Demeshkan.Kyrsach.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("select e from Employee e where e.profession.professionID = ?1")
    List<Employee> findByEmployeeProfession(Integer professionID);
}