package com.hromov.cruise.controller;

import com.hromov.cruise.model.Cruise;
import com.hromov.cruise.model.Ship;
import com.hromov.cruise.model.Station;
import com.hromov.cruise.service.CruiseService;
import com.hromov.cruise.service.ShipService;
import com.hromov.cruise.service.StationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class CruiseController {

    private CruiseService cruiseService;
    private ShipService shipService;
    private StationService stationService;

    public CruiseController(CruiseService cruiseService, ShipService shipService, StationService stationService) {
        this.cruiseService = cruiseService;
        this.shipService = shipService;
        this.stationService = stationService;
    }

    @RequestMapping(value = "/find_cruise", method = RequestMethod.GET)
    public ModelAndView loadCruises() {
        List<Cruise> cruiseList = cruiseService.getCruiseList();
        ModelAndView model = new ModelAndView("findCruise");
        model.addObject("cruiseList", cruiseList);
        return model;
    }

    @RequestMapping(value = "/add_cruise", method = RequestMethod.GET)
    public ModelAndView loadAddCruise() {
        List<Ship> shipList = shipService.getShipList();
        List<Station> stationList = stationService.getStationList();
        ModelAndView model = new ModelAndView("addCruise");
        model.addObject("shipList", shipList);
        model.addObject("stationList", stationList);
        return model;
    }
}
