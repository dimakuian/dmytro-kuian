package com.epam.spring.cargo_delivery.service.repository;

import com.epam.spring.cargo_delivery.service.model.Locality;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocalityRepository extends JpaRepository<Locality, Double> {

  //  @Query(value = "SELECT a.name AS from_city, b.name AS to_city," +
//      "       ROUND(111.111 * DEGREES(ACOS(LEAST(1.0, COS(RADIANS(a.lat))\n" +
//      "                   * COS(RADIANS(b.lat))\n" +
//      "                   * COS(RADIANS(a.lng - b.lng))\n" +
//      "                   + SIN(RADIANS(a.lat))\n" +
//      "                   * SIN(RADIANS(b.lat)))))) + 40 AS distance_in_km\n" +
//      "FROM locality AS a\n" +
//      "         JOIN locality AS b ON a.id <> b.id\n" +
//      "WHERE a.id = ?1\n" +
//      "  AND b.id = ?2")
  Optional<Double> calcDistanceBetweenTwoLocality(long localityOneId, long localityTwoId);
}
