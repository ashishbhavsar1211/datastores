package com.barclays.datastore.service;

import com.barclays.datastore.model.MortgageForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

@Service
@Scope("application")
public class StoreMortgageAppDataService {

    private static final Logger logger = LoggerFactory.getLogger(StoreMortgageAppDataService.class);

    LinkedList<MortgageForm> mortgageFormsLists = new LinkedList<>();

    public void storeMortgageData(MortgageForm mortgageForm) {
/*
        for (MortgageForm mf : mortgageFormsLists) {

            if (mf.getMortgageId().equalsIgnoreCase(mortgageForm.getMortgageId()) && mf.getVersion() >= mortgageForm.getVersion()) {
                // Todo  add the element in the list if the condition satisfy.
            }
        }*/

        ListIterator<MortgageForm> mortgageFormListIterator = mortgageFormsLists.listIterator();
        while (mortgageFormListIterator.hasNext()) {
            if (mortgageFormListIterator.next().getMortgageId().equalsIgnoreCase(mortgageForm.getMortgageId()) &&
                    mortgageFormListIterator.next().getVersion() >= mortgageForm.getVersion()) {

                mortgageFormListIterator.next().setMortgageId(mortgageForm.getMortgageId());
                mortgageFormListIterator.next().setVersion(mortgageForm.getVersion());
                mortgageFormListIterator.next().setOfferId(mortgageForm.getOfferId());
                mortgageFormListIterator.next().setOfferExpired(mortgageForm.getOfferExpired());
                mortgageFormListIterator.next().setCreatedDate(mortgageForm.getCreatedDate());
                mortgageFormListIterator.next().setOfferDate(mortgageForm.getOfferDate());

            }
        }
        mortgageFormsLists.add(mortgageForm);
        logger.info("List size : " + mortgageFormsLists);
    }


    public LinkedList<MortgageForm> fetchMortgageApp() {
        Collections.sort(mortgageFormsLists, MortgageForm.offerDateComparator);
        return mortgageFormsLists;
    }

    public MortgageForm getById(String id) {
        for (MortgageForm mortgageForm : mortgageFormsLists) {
            if (mortgageForm.getMortgageId().equalsIgnoreCase(id)) {
                return mortgageForm;
            }
        }

        return null;
    }


    public void updateOfferExpiredAppData() {

      /*  for (MortgageForm mf : mortgageFormsLists) {
            long days = ChronoUnit.DAYS.between(mf.getCreatedDate(), mf.getOfferDate());
            if (days > 0) {
                mf.setOfferExpired(true);
            }
        }*/

        // ListIterator
        ListIterator<MortgageForm> mortgageFormListIterator = mortgageFormsLists.listIterator();
        while (mortgageFormListIterator.hasNext()) {
            long days = ChronoUnit.DAYS.between(mortgageFormListIterator.next().getCreatedDate(),
                    mortgageFormListIterator.next().getOfferDate());
            if (days > 0) {
                mortgageFormListIterator.next().setOfferExpired(true);
            }
        }
    }
}
