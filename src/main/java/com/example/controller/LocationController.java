package com.example.controller;

import com.example.entities.Contact;
import com.example.entities.Location;
import com.example.entities.Student;
import com.example.exeption.ContactNotFound;
import com.example.exeption.LocationNotFound;
import com.example.exeption.StudentNotFound;
import com.example.service.LocationService;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    LocationService locationService;
    @Autowired
    StudentService studentService;

    @PostMapping//done
    public Location createLocation(@RequestBody Location location) {
        return locationService.createLocation(location);
    }

    @PutMapping("/edit_location")//done
    public String updateLocation(@RequestParam Long idLocation,
                                @RequestBody Location locationDetails){
        if (!locationService.existsLocation(idLocation)) {
            throw new LocationNotFound("Location with id " + idLocation + " Not Found.");
        }
        locationService.updateLocation(idLocation,locationDetails);
        return "Update of Student : "+idLocation+" Completed";
    }

    @GetMapping("/find_all_location")//done
    public ResponseEntity<List<Location>> getAllLocations() throws LocationNotFound{
        List<Location> locations = locationService.getAllLocations();
        if (!locations.isEmpty()) {
            return ResponseEntity.ok(locations);
        } else {
            throw new LocationNotFound("No Location Found. ");
        }
    }

    @GetMapping("/{idLocation}")//done
    public ResponseEntity<Location> getLocationById(@PathVariable Long idLocation) {
        Location location = locationService.getLocationById(idLocation);
        if (location != null) {
            return ResponseEntity.ok(location);
        } else {
            throw new LocationNotFound("Location with Id : " + idLocation + " Not Found. ");
        }

        /*return locationService.getLocationById(idLocation).orElse(null);*/
    }

    @DeleteMapping("/{idLocation}")//done
    public ResponseEntity<String> deleteLocation(@PathVariable Long idLocation) {
        Location location = locationService.getLocationById(idLocation);
        if (location != null) {
            locationService.deleteLocation(idLocation);
            return ResponseEntity.ok("Location with Id : "+idLocation+" Deleted. ");
        } else {
            throw new LocationNotFound("Location with Id : " + idLocation + " Not Found. ");
        }


    }

    // Endpoint to add location to a student
    @PostMapping("/{idStudent}/location")
    public Student setStudentLocation(@PathVariable Long idStudent, @RequestBody Location location) {
        return locationService.setStudentLocation(idStudent, location);
    }

}

