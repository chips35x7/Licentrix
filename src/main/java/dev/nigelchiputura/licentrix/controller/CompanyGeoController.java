package dev.nigelchiputura.licentrix.controller;

import dev.nigelchiputura.licentrix.service.GeoJsonService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/companies")
@PreAuthorize("hasRole('ADMIN')")
public class CompanyGeoController {

    private final GeoJsonService geoJsonService;

    public CompanyGeoController(GeoJsonService geoJsonService) {
        this.geoJsonService = geoJsonService;
    }

    @GetMapping("/geojson")
    public ResponseEntity<?> getCompaniesGeoJson() {
        return ResponseEntity.ok(geoJsonService.getCompaniesAsGeoJson());
    }
}
