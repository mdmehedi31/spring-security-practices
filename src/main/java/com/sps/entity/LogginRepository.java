package com.sps.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LogginRepository extends JpaRepository<LoggingEntity, Integer> {
}
