package dev.nigelchiputura.licentrix.repository;

import dev.nigelchiputura.licentrix.model.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LicenseRepository
        extends JpaRepository<License, Integer> {
//    @Query("select l from License l where l.issueDate is not null and l.issueDate + l.validityYears * INTERVAL '1 year' < :date")
    // HQL won't support interval like that easily; you'll write a native query or compute expiry in Java
}
