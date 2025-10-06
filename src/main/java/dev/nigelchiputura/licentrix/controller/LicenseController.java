package dev.nigelchiputura.licentrix.controller;

import dev.nigelchiputura.licentrix.model.License;
import dev.nigelchiputura.licentrix.service.LicenseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/licenses")
@PreAuthorize("hasRole('ADMIN')")
public class LicenseController {
    public final LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping
    public List<License> getLicenses() {
        return licenseService.getAllLicenses();
    }

    @GetMapping("{id}")
    public License getLicense(Integer id) {
        return licenseService.getLicenseById(id);
    }

    @PostMapping
    public License addLicense(License newLicense) {
        return licenseService.addLicense(newLicense);
    }

    @PutMapping("{id}")
    public License updateLicense(Integer id, License updatedLicense) {
        return licenseService.updateLicense(id, updatedLicense);
    }

    @DeleteMapping
    public void deleteLicense(Integer id) {
        licenseService.deleteLicense(id);
    }
}
