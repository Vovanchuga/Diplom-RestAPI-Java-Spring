package com.Demeshkan.Kyrsach.Repository;

import com.Demeshkan.Kyrsach.Model.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishesRepository extends JpaRepository<Dishes, Integer> {
}