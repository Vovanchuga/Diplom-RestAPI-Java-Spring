package com.Demeshkan.Kyrsach.Repository;

import com.Demeshkan.Kyrsach.Model.Employee;
import com.Demeshkan.Kyrsach.Model.Parents;
import com.Demeshkan.Kyrsach.Model.ReplSchedule;
import com.Demeshkan.Kyrsach.Model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    /*@Query("select u from Users u where u.userType.TypeID = ?1")
    Page<Users> findByuserType(Integer typeId, Pageable pageable);

    //List<Tag> findTagsByTutorialsId(Long tutorialId);

    //@Query("select u from Users u where u.userAndEvent = ?1")
    @Query("select u from Users u inner join u.userAndEvent userAndEvent where userAndEvent.eventID = ?1")
    Page<Users> findUsersByEvent(Integer eventID, Pageable pageable);*/

    @Query("select u from Users u where u.Email = ?1")
    Users findByEmail(String email);

    @Query("select u from Users u where u.Login = ?1")
    Users findByLogin(String login);

    @Query("select p from Parents p where p.user.userID = ?1")
    Parents findByParentsUser(Integer userID);

    @Query("select e from Employee e where e.user.userID = ?1")
    Employee findByEmployeeUser(Integer userID);
}
