package com.Demeshkan.Kyrsach.Repository;

import com.Demeshkan.Kyrsach.Model.Groups;
import com.Demeshkan.Kyrsach.Model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GroupsRepository extends JpaRepository<Groups, Integer> {
    @Query("select r from Groups r where r.Name = ?1")
    Groups findByGroupName(String Name);
}