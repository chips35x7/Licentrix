package dev.nigelchiputura.licentrix.service;

import dev.nigelchiputura.licentrix.model.Company;
import dev.nigelchiputura.licentrix.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public Company getCompanyById(Integer id) {
        return companyRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Company with id "+ id + " not found!")
        );
    }

    public void updateCompany(Integer id, Company updatedCompany) {
        Company existingCompany = companyRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Company with id "+ id + " not found!")
        );

        existingCompany.setName(updatedCompany.getName());
        existingCompany.setEmail(updatedCompany.getEmail());
        existingCompany.setGpsCoordinates(updatedCompany.getGpsCoordinates());
        existingCompany.setLicenses(updatedCompany.getLicenses());

        companyRepository.save(existingCompany);
    }

    public void deleteCompany(Integer id) {
        boolean companyExists = companyRepository.existsById(id);

        if (!companyExists) {
            throw new IllegalStateException("Company with id "+ id + " not found!");
        }
        companyRepository.deleteById(id);
    }
}
