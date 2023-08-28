package com.example.service;

import com.example.entities.Location;
import com.example.entities.Student;
import com.example.exeption.LocationNotFound;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    public Location createLocation(Location location);

    public ResponseEntity<Location> updateLocation(Long idLocation, Location locationDetails);

    public List<Location> getAllLocations();

    public Location getLocationById(Long idLocation);

    public String deleteLocation(Long idLocation);
    public Student setStudentLocation(Long idStudent, Location location);

    //... Verification
    boolean existsLocation(Long idLocation);
}
