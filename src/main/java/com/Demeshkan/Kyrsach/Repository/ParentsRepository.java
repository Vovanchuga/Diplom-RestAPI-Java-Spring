package com.Demeshkan.Kyrsach.Repository;

import com.Demeshkan.Kyrsach.Model.Parents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentsRepository extends JpaRepository<Parents, Integer> {
}