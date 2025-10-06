package dev.nigelchiputura.licentrix.controller;

import dev.nigelchiputura.licentrix.model.*;
import dev.nigelchiputura.licentrix.repository.UserRepository;
import dev.nigelchiputura.licentrix.service.LicenseApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
public class LicenseApplicationController {

    private final LicenseApplicationService service;
    private final UserRepository userRepo;

    public LicenseApplicationController(LicenseApplicationService service, UserRepository userRepo) {
        this.service = service;
        this.userRepo = userRepo;
    }

    @PostMapping("apply")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<LicenseApplication> apply(@RequestBody Integer companyId,
                                                    @RequestBody String licenseType,
                                                    Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElseThrow();
        LicenseApplication app = service.apply(user.getId(), companyId, LicenseType.valueOf(licenseType));
        return ResponseEntity.ok(app);
    }

    @GetMapping("my-applications")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<LicenseApplication>> myApplications(Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElseThrow();
        return ResponseEntity.ok(service.getApplicationsByUser(user));
    }
}