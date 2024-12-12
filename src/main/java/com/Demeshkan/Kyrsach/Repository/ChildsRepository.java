package com.Demeshkan.Kyrsach.Repository;

import com.Demeshkan.Kyrsach.Model.Childs;
import com.Demeshkan.Kyrsach.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildsRepository extends JpaRepository<Childs, Integer> {
    @Query("select e from Childs e where e.group.groupsID = ?1")
    List<Childs> findByChildsGroup(Integer groupsID);
}