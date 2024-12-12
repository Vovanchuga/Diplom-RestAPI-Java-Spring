package com.Demeshkan.Kyrsach.Repository;

import com.Demeshkan.Kyrsach.Model.TypeMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeMenuRepository extends JpaRepository<TypeMenu, Integer> {
}