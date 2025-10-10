package dev.nigelchiputura.licentrix.controller;

import dev.nigelchiputura.licentrix.model.License;
import dev.nigelchiputura.licentrix.service.LicenseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/licenses")
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
    public License getLicense(@PathVariable Integer id) {
        return licenseService.getLicenseById(id);
    }

    @PostMapping
    public License addLicense(@RequestBody License newLicense) {
        return licenseService.addLicense(newLicense);
    }

    @PutMapping("{id}")
    public License updateLicense(@PathVariable Integer id, @RequestBody License updatedLicense) {
        return licenseService.updateLicense(id, updatedLicense);
    }

    @DeleteMapping("{id}")
    public void deleteLicense(@PathVariable Integer id) {
        licenseService.deleteLicense(id);
    }
}
