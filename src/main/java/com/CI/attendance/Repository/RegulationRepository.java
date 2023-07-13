package com.CI.attendance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CI.attendance.Model.MetaRegulation;


@Repository
public interface RegulationRepository extends JpaRepository<MetaRegulation, Integer>{

}
