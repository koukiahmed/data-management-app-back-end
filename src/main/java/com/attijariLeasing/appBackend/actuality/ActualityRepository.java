package com.attijariLeasing.appBackend.actuality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActualityRepository extends JpaRepository<Actuality,Long> {
}
