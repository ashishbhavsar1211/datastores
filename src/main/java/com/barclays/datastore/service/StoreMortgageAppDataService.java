package com.barclays.datastore.service;

import com.barclays.datastore.model.MortgageForm;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
@Scope("application")
public class StoreMortgageAppDataService {

    LinkedList<MortgageForm> mortgageFormsLists = new LinkedList<>();

    public void storeMortgageData(MortgageForm mortgageForm) {

        mortgageFormsLists.add(mortgageForm);
    }


    public LinkedList<MortgageForm> fetchMortgageApp() {
        return mortgageFormsLists;
    }

    public MortgageForm getById(String id) {

        return null;
    }
}
