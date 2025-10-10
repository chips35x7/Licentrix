package dev.nigelchiputura.licentrix.controller;

import dev.nigelchiputura.licentrix.model.License;
import dev.nigelchiputura.licentrix.service.LicenseFeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/licenses")
@PreAuthorize("hasRole('ADMIN')")
public class LicenseToolsController {

    private final LicenseFeeService licenseFeeService;

    public LicenseToolsController(LicenseFeeService licenseFeeService) {
        this.licenseFeeService = licenseFeeService;
    }

    @GetMapping("/compare/{id1}/{id2}")
    public ResponseEntity<?> compareLicenses(@PathVariable Integer id1, @PathVariable Integer id2) {
        boolean equal = licenseFeeService.areEqual(id1, id2);
        return ResponseEntity.ok(equal ? "Licenses are equal" : "Licenses are different");
    }

    @PutMapping("/{id}/recalculate-fee")
    public ResponseEntity<License> recalcFee(@PathVariable Integer id) {
        License updated = licenseFeeService.updateFee(id);
        return ResponseEntity.ok(updated);
    }
}
