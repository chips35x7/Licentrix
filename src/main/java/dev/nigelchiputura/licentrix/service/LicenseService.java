package dev.nigelchiputura.licentrix.service;

import dev.nigelchiputura.licentrix.model.License;
import dev.nigelchiputura.licentrix.repository.LicenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicenseService {

    private final LicenseRepository licenseRepository;

    public LicenseService(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    public List<License> getAllLicenses() {
        return licenseRepository.findAll();
    }

    public License getLicenseById(Integer id) {
        return licenseRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("License with id " + id + " not found!")
        );
    }

    public License addLicense(License newLicense) {
        return licenseRepository.save(newLicense);
    }

    public License updateLicense(Integer id, License updatedLicense) {
        License existingLicense = licenseRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("License with id " + id + " not found!")
        );

        existingLicense.setLicenseType(updatedLicense.getLicenseType());
        existingLicense.setIssueDate(updatedLicense.getIssueDate());
        existingLicense.setValidityYears(updatedLicense.getValidityYears());
        existingLicense.setApplicationFee(updatedLicense.getApplicationFee());
        existingLicense.setLicenseFee(updatedLicense.getLicenseFee());
        existingLicense.setAnnualFrequencyFee(updatedLicense.getAnnualFrequencyFee());
        existingLicense.setCompany(updatedLicense.getCompany());
        existingLicense.setAnnualContributionFee(updatedLicense.getAnnualContributionFee());

        return licenseRepository.save(existingLicense);
    }

    public void deleteLicense(Integer id) {
        boolean licenseExists = licenseRepository.existsById(id);

        if (!licenseExists) {
            throw new IllegalStateException("License with id " + id + " not found!");
        }
        licenseRepository.deleteById(id);
    }
}
