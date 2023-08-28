package com.example.service;

import com.example.entities.Location;
import com.example.entities.Student;
import com.example.exeption.LocationNotFound;
import com.example.exeption.StudentNotFound;
import com.example.repository.LocationRepository;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImp implements LocationService{

    @Autowired
    LocationRepository locationRepository;
    @Autowired
    StudentRepository studentRepository;

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public ResponseEntity<Location> updateLocation(Long idLocation, Location locationDetails) {
        Location updateLocation = locationRepository.findById(idLocation).get();

        updateLocation.setAddress1(locationDetails.getAddress1());
        updateLocation.setAddress2(locationDetails.getAddress2());
        updateLocation.setCity(locationDetails.getCity());
        updateLocation.setRegion(locationDetails.getRegion());
        updateLocation.setZipCode(locationDetails.getZipCode());

        locationRepository.save(updateLocation);
        return ResponseEntity.ok(updateLocation);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Long idLocation) {

        return locationRepository.findById(idLocation)
                .orElseThrow(()-> new LocationNotFound("Location with Id : "+idLocation+" Not Found. "));
    }


    public String deleteLocation(Long idLocation) {
        locationRepository.deleteById(idLocation);
        return "Location "+idLocation+"deleted";
    }

    public Student setStudentLocation(Long idStudent, Location location) {
        Student student = studentRepository.findById(idStudent).orElse(null);
        if (student != null) {
            location.setStudent(student);
            locationRepository.save(location);
            student.setLocation(location);
            return studentRepository.save(student);
        }
        return null; // or throw an exception if idStudent is not valid
    }

    //... Verification
    @Override
    public boolean existsLocation(Long idLocation) {
        return locationRepository.existsById(idLocation);
    }

}

