package com.Demeshkan.Kyrsach.Repository;

import com.Demeshkan.Kyrsach.Model.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends JpaRepository<Events, Integer> {
}