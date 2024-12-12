package com.Demeshkan.Kyrsach.Repository;

import com.Demeshkan.Kyrsach.Model.Menu;
import com.Demeshkan.Kyrsach.Model.ReplSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Query("select r from Menu r where r.MenuDate = ?1")
    List<Menu> findByDayDate(Date MenuDate);
    @Query("select r from Menu r where r.MenuDate=:MenuDate AND r.typeMenu.typeMenuID=:typeMenuID AND r.cook.employeeID=:employeeID")
    Menu findBySpecDayDate(Date MenuDate, Integer typeMenuID, Integer employeeID);
}