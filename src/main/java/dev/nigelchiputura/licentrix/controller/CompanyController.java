package dev.nigelchiputura.licentrix.controller;

import dev.nigelchiputura.licentrix.model.Company;
import dev.nigelchiputura.licentrix.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;

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
    public void addCompany(
            @RequestBody Company company
            ) {
        companyService.addCompany(company);
    }

    @PutMapping("{id}")
    public void updateCompany(
            @PathVariable Integer id,
            @RequestBody Company updatedCompany
    ) {
      companyService.updateCompany(id, updatedCompany);
    }

    @DeleteMapping("{id}")
    public void deleteCompany(
            @PathVariable Integer id
    ) {
        companyService.deleteCompany(id);
    }
}
