package com.CI.attendance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CI.attendance.Model.MetaProgrammeOutcome;

@Repository
public interface ProgrammeOutcomeRepository extends JpaRepository<MetaProgrammeOutcome, Integer>{

}
