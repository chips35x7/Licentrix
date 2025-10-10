package dev.nigelchiputura.licentrix.service;

import dev.nigelchiputura.licentrix.model.Company;
import dev.nigelchiputura.licentrix.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class GeoJsonService {

    private final CompanyRepository companyRepo;

    public GeoJsonService(CompanyRepository companyRepo) {
        this.companyRepo = companyRepo;
    }

    public Map<String, Object> getCompaniesAsGeoJson() {
        List<Company> companies = companyRepo.findAll();

        Map<String, Object> featureCollection = new LinkedHashMap<>();
        featureCollection.put("type", "FeatureCollection");

        List<Map<String, Object>> features = new ArrayList<>();

        for (Company company : companies) {
            if (company.getLatitude() != null && company.getLongitude() != null) {
                Map<String, Object> feature = new LinkedHashMap<>();
                feature.put("type", "Feature");

                Map<String, Object> geometry = new LinkedHashMap<>();
                geometry.put("type", "Point");
                geometry.put("coordinates", Arrays.asList(company.getLongitude(), company.getLatitude())); // GeoJSON uses [lon, lat]
                feature.put("geometry", geometry);

                Map<String, Object> props = new LinkedHashMap<>();
                props.put("id", company.getId());
                props.put("name", company.getName());
                props.put("email", company.getEmail());
                feature.put("properties", props);

                features.add(feature);
            }
        }

        featureCollection.put("features", features);
        return featureCollection;
    }
}
