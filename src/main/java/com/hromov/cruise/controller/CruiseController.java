package com.hromov.cruise.controller;

import com.hromov.cruise.exception.CruiseNotFoundException;
import com.hromov.cruise.model.Cruise;
import com.hromov.cruise.model.Ship;
import com.hromov.cruise.model.Station;
import com.hromov.cruise.service.CruiseService;
import com.hromov.cruise.service.ShipService;
import com.hromov.cruise.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/cruises")
@RequiredArgsConstructor
public class CruiseController {
    private final CruiseService cruiseService;
    private final ShipService shipService;
    private final StationService stationService;

    @GetMapping(value = "/")
    public ModelAndView loadFindCruisePage() {
        List<Cruise> cruiseList = cruiseService.getCruiseList();
        ModelAndView model = new ModelAndView("findCruise");
        model.addObject("cruiseList", cruiseList);
        return model;
    }

    @GetMapping(value = "/{cruiseId}")
    public Cruise loadCruiseById(@PathVariable long cruiseId) {
        try {
            return cruiseService.findCruiseById(cruiseId);
        } catch (CruiseNotFoundException e) {
            throw new RuntimeException(e);
        }
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
}
