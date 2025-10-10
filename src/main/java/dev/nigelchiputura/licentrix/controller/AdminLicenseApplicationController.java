package dev.nigelchiputura.licentrix.controller;

import dev.nigelchiputura.licentrix.model.*;
import dev.nigelchiputura.licentrix.repository.LicenseApplicationRepository;
import dev.nigelchiputura.licentrix.service.LicenseApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/licenses")
@PreAuthorize("hasRole('ADMIN')")
public class AdminLicenseApplicationController {
    private final LicenseApplicationService service;
    private final LicenseApplicationRepository appRepo;

    public AdminLicenseApplicationController(LicenseApplicationService service, LicenseApplicationRepository appRepo) {
        this.service = service;
        this.appRepo = appRepo;
    }

    @GetMapping("/applications")
    public ResponseEntity<List<LicenseApplication>> listAll() {
        return ResponseEntity.ok(appRepo.findAll());
    }

    @PatchMapping("/applications/{id}/approve")
    public ResponseEntity<License> approve(@PathVariable Integer id,
                                           @RequestParam(required = false) Integer validityYears) {
        License lic = service.approve(id, validityYears != null ? validityYears : 0);
        return ResponseEntity.ok(lic);
    }

    @PatchMapping("/applications/{id}/reject")
    public ResponseEntity<Void> reject(@PathVariable Integer id, @RequestParam String comment) {
        service.reject(id, comment);
        return ResponseEntity.noContent().build();
    }
}