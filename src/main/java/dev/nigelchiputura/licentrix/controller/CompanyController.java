package dev.nigelchiputura.licentrix.controller;

import dev.nigelchiputura.licentrix.model.Company;
import dev.nigelchiputura.licentrix.model.User;
import dev.nigelchiputura.licentrix.repository.UserRepository;
import dev.nigelchiputura.licentrix.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    private UserRepository userRepository;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("{id}")
    public Company getCompany(
            @PathVariable Integer id
    ) {
        return companyService.getCompanyById(id);
    }

    @PostMapping
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        Company savedCompany = companyService.addCompany(company);
        return ResponseEntity.ok(savedCompany);
    }

    @PutMapping("{id}")
    public Company updateCompany(
            @PathVariable Integer id,
            @RequestBody Company updatedCompany
    ) {
      return companyService.updateCompany(id, updatedCompany);
    }

    @DeleteMapping("{id}")
    public void deleteCompany(
            @PathVariable Integer id
    ) {
        companyService.deleteCompany(id);
    }
}
