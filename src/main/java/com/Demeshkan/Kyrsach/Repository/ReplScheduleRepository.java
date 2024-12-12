package com.Demeshkan.Kyrsach.Repository;

import com.Demeshkan.Kyrsach.Model.Groups;
import com.Demeshkan.Kyrsach.Model.ReplSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ReplScheduleRepository extends JpaRepository<ReplSchedule, Integer> {
    //@Query("select g from Groups g where g.tutor.userID = ?1")

    @Query("select r from ReplSchedule r where r.DayDate = ?1")
    List<ReplSchedule> findByDayDate(Date dayDate);
}