package com.hromov.cruise.controller;

import com.hromov.cruise.model.Cruise;
import com.hromov.cruise.model.Ship;
import com.hromov.cruise.model.Station;
import com.hromov.cruise.service.CruiseService;
import com.hromov.cruise.service.ShipService;
import com.hromov.cruise.service.StationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/cruises")
@RequiredArgsConstructor
public class CruiseController {
    private final CruiseService cruiseService;
    private final ShipService shipService;
    private final StationService stationService;
    private final RestTemplate restTemplate;
    @Value("${spring.datasource.url}")
    private String jdbc;

    @GetMapping
    public ModelAndView loadFindCruisePage() {
        List<Cruise> cruiseList = cruiseService.getCruiseList();
        ModelAndView model = new ModelAndView("findCruise");
        model.addObject("cruiseList", cruiseList);
        log.info("Find cruise page was loaded" + jdbc);
        return model;
    }

    @GetMapping(value = "{cruiseId}")
    public Cruise loadCruiseByIdRest(@PathVariable long cruiseId, @Value("${server.port}") int port) {
        log.info("dgdgdfgdgdfgdfgdg", cruiseId);
        return restTemplate.getForObject("http://localhost:" + port + "/rest-data/CrUiSeS/{cruiseId}",
                Cruise.class, cruiseId);
    }

    @GetMapping(value = "/noRest/{cruiseId}")
    public Cruise loadCruiseById(@PathVariable long cruiseId) {
        log.info("Requested cruise with id '{}'", cruiseId);
        return cruiseService.findCruiseById(cruiseId);
    }

    @GetMapping(value = "/add_cruise")
    public ModelAndView loadAddCruisePage() {
        List<Ship> shipList = shipService.getShipList();
        List<Station> stationList = stationService.getStationList();
        ModelAndView model = new ModelAndView("addCruise");
        model.addObject("shipList", shipList);
        model.addObject("stationList", stationList);
        log.info("Add cruise page was loaded");
        return model;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/add_cruise")
    public void addCruise(@Valid @RequestBody Cruise cruise) {
        cruiseService.createCruise(cruise);
        log.info("Cruise {} was created", cruise);
    }

    @GetMapping(value = "/edit_cruise/{cruiseId}")
    public ModelAndView loadEditCruisePage(@PathVariable long cruiseId) {
        Cruise cruise = cruiseService.findCruiseById(cruiseId);
        List<Ship> shipList = shipService.getShipList();
        List<Station> stationList = stationService.getStationList();
        ModelAndView model = new ModelAndView("addCruise");
        model.addObject("cruise", cruise);
        model.addObject("shipList", shipList);
        model.addObject("stationList", stationList);
        log.info("Edit cruise '{}' page was loaded", cruise);
        return model;
    }

    @PutMapping(value = "/edit_cruise")
    public void editCruise(@Valid @RequestBody Cruise cruise) {
        cruiseService.updateCruise(cruise);
        log.info("Cruise {} was edited", cruise);
    }

    @DeleteMapping(value = "/delete_cruise")
    public void deleteCruise(@RequestParam long cruiseId) {
        cruiseService.deleteCruise(cruiseId);
        log.info("Cruise {} was deleted", cruiseId);
    }
}
