package dev.nigelchiputura.licentrix.dto;

public class LicenseRequest {
    private Integer id;
    private String licenseType;
    private int validityYears;
    private Long companyId;

    public Integer getId() {
        return id;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public int getValidityYears() {
        return validityYears;
    }

    public Long getCompanyId() {
        return companyId;
    }
}