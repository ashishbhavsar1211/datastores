package com.barclays.datastore.controller;


import com.barclays.datastore.model.MortgageForm;
import com.barclays.datastore.service.StoreMortgageAppDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;

@RestController
@RequestMapping("/datastore")
public class DatastoreController {


    @Autowired
    private StoreMortgageAppDataService storeMortgageAppDataService;

    @PostMapping(value = "/submit", produces = "application/json")
    public void submit(@RequestBody MortgageForm mortgageform, HttpServletRequest request) {
        //TODO
        storeMortgageAppDataService.storeMortgageData(mortgageform);
    }

    @GetMapping(value = "/getMortgage", produces = "application/json")
    public LinkedList<MortgageForm> getAllForms() {
        return storeMortgageAppDataService.fetchMortgageApp();
    }

    @GetMapping(value = "/getMortgageById/{id}", produces = "application/json")
    public MortgageForm getMortgageById(@PathVariable String id) {
        return storeMortgageAppDataService.getById(id);
    }
}
