package dev.nigelchiputura.licentrix.repository;

import dev.nigelchiputura.licentrix.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository
        extends JpaRepository<Company, Integer> {
}
