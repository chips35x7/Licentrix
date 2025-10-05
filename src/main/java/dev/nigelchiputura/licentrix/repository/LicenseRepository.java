package dev.nigelchiputura.licentrix.repository;

import dev.nigelchiputura.licentrix.model.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License, Integer> {}
