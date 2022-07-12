package com.epam.spring.cargo_delivery.service.repository;

import com.epam.spring.cargo_delivery.service.model.Locality;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalityRepository extends JpaRepository<Locality, Long> {

  @Query(value = "SELECT ROUND(111.111 *DEGREES(ACOS(LEAST(1.0, COS(RADIANS(a.latitude)) "
      + "* COS(RADIANS(b.latitude)) * COS(RADIANS(a.longitude - b.longitude))\n"
      + "+ SIN(RADIANS(a.latitude)) * SIN(RADIANS(b.latitude)))))) + 40 AS distance_in_km\n"
      + "FROM delivery.`locality` AS a JOIN delivery.`locality` AS b ON a.id <> b.id\n"
      + "WHERE a.id = ?1 AND b.id = ?2", nativeQuery = true)
  Optional<Double> calcDistanceBetweenTwoLocality(long localityOneId, long localityTwoId);
}
