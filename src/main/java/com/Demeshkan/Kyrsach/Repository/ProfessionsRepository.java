package com.Demeshkan.Kyrsach.Repository;

import com.Demeshkan.Kyrsach.Model.Professions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionsRepository extends JpaRepository<Professions, Integer> {
}