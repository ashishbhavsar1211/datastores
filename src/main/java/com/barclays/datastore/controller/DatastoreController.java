package com.barclays.datastore.controller;


import com.barclays.datastore.model.MortgageForm;
import com.barclays.datastore.model.Mortgagelist;
import com.barclays.datastore.service.StoreMortgageAppDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public Mortgagelist getAllForms() {
        Mortgagelist mortgagelist = new Mortgagelist();
        mortgagelist.setMortgageFormList(storeMortgageAppDataService.fetchMortgageApp());
        return mortgagelist;
    }

    @GetMapping(value = "/getMortgageById/{id}", produces = "application/json")
    public ResponseEntity<MortgageForm> getMortgageById(@PathVariable("id") String id) {
        MortgageForm mfById = storeMortgageAppDataService.getById(id);
        return new ResponseEntity<MortgageForm>(mfById,HttpStatus.OK);
    }
}
