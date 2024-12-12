package com.Demeshkan.Kyrsach.Repository;

import com.Demeshkan.Kyrsach.Model.EveryDaySchedule;
import com.Demeshkan.Kyrsach.Model.Parents;
import com.Demeshkan.Kyrsach.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EveryDayScheduleRepository extends JpaRepository<EveryDaySchedule, Integer> {

    @Query("select e from EveryDaySchedule e where e.group.groupsID = ?1")
    List<EveryDaySchedule> findByGroup(Integer groupsID);

}