package dev.nigelchiputura.licentrix.dto;

public class LicenseResponse {
    private Long id;
    private String licenseType;
    private int validityYears;
    private double licenseFee;
    private Long companyId;
    private String companyName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public int getValidityYears() {
        return validityYears;
    }

    public void setValidityYears(int validityYears) {
        this.validityYears = validityYears;
    }

    public double getLicenseFee() {
        return licenseFee;
    }

    public void setLicenseFee(double licenseFee) {
        this.licenseFee = licenseFee;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
