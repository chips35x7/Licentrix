package dev.nigelchiputura.licentrix.controller;

import dev.nigelchiputura.licentrix.model.License;
import dev.nigelchiputura.licentrix.service.LicenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/licenses")
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
    public void addLicense(License newLicense) {
        licenseService.addLicense(newLicense);
    }

    @PutMapping("{id}")
    public void updateLicense(Integer id, License updatedLicense) {
        licenseService.updateLicense(id, updatedLicense);
    }

    @DeleteMapping
    public void deleteLicense(Integer id) {
        licenseService.deleteLicense(id);
    }
}
