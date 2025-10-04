package dev.nigelchiputura.licentrix.repository;

import java.util.List;
import dev.nigelchiputura.licentrix.model.LicenseApplication;
import dev.nigelchiputura.licentrix.model.LicenseApplicationStatus;
import dev.nigelchiputura.licentrix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseApplicationRepository extends JpaRepository<LicenseApplication,Integer> {
    List<LicenseApplication> findByApplicant(User applicant);
    List<LicenseApplication> findByStatus(LicenseApplicationStatus status);
}