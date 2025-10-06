package dev.nigelchiputura.licentrix.controller;

import dev.nigelchiputura.licentrix.service.ReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/reports")
@PreAuthorize("hasRole('ADMIN')")
public class AdminReportController {

    private final ReportService reportService;

    public AdminReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/licenses/csv")
    public ResponseEntity<byte[]> downloadCsv() throws Exception {
        byte[] data = reportService.generateLicenseCsv();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=licenses.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(data);
    }

    @GetMapping("/licenses/pdf")
    public ResponseEntity<byte[]> downloadPdf() throws Exception {
        byte[] data = reportService.generateLicensePdf();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=licenses.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(data);
    }
}
