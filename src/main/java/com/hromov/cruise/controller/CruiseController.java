package com.hromov.cruise.controller;

import com.hromov.cruise.model.Cruise;
import com.hromov.cruise.model.Ship;
import com.hromov.cruise.model.Station;
import com.hromov.cruise.service.CruiseService;
import com.hromov.cruise.service.ShipService;
import com.hromov.cruise.service.StationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/cruises")
@RequiredArgsConstructor
public class CruiseController {
    private final CruiseService cruiseService;
    private final ShipService shipService;
    private final StationService stationService;

    @GetMapping
    public ModelAndView loadFindCruisePage() {
        List<Cruise> cruiseList = cruiseService.getCruiseList();
        ModelAndView model = new ModelAndView("findCruise");
        model.addObject("cruiseList", cruiseList);
        return model;
    }

    @GetMapping(value = "{cruiseId}")
    public Cruise loadCruiseById(@PathVariable long cruiseId) {
        return cruiseService.findCruiseById(cruiseId);
    }

    @GetMapping(value = "/add_cruise")
    public ModelAndView loadAddCruisePage() {
        List<Ship> shipList = shipService.getShipList();
        List<Station> stationList = stationService.getStationList();
        ModelAndView model = new ModelAndView("addCruise");
        model.addObject("shipList", shipList);
        model.addObject("stationList", stationList);
        return model;
    }

    @PostMapping(value = "/add_cruise")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCruise(@Valid @RequestBody Cruise cruise) {
        cruiseService.createCruise(cruise);
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
        return model;
    }

    @PutMapping(value = "/edit_cruise")
    public void editCruise(@Valid @RequestBody Cruise cruise) {
        cruiseService.updateCruise(cruise);
    }

    @DeleteMapping(value = "/delete_cruise")
    public void deleteCruise(@RequestParam long cruiseId) {
        cruiseService.deleteCruise(cruiseId);
    }
}
