package com.barclays.datastore.service;

import com.barclays.datastore.model.MortgageForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        ListIterator<MortgageForm> mortgageFormListIterator = mortgageFormsLists.listIterator();
        boolean formAdded = Boolean.FALSE;
        while (mortgageFormListIterator.hasNext()) {
            MortgageForm form = mortgageFormListIterator.next();
            if (form.getMortgageId().equalsIgnoreCase(mortgageForm.getMortgageId()) &&
                    form.getVersion().equals(mortgageForm.getVersion())) {
                formAdded = Boolean.TRUE;
                form.setMortgageId(mortgageForm.getMortgageId());
                form.setVersion(mortgageForm.getVersion());
                form.setOfferId(mortgageForm.getOfferId());
                form.setOfferExpired(mortgageForm.getOfferExpired());
                form.setCreatedDate(mortgageForm.getCreatedDate());
                form.setOfferDate(mortgageForm.getOfferDate());

            }
        }
        if (!formAdded) {
            mortgageFormsLists.add(mortgageForm);
        }
        logger.info("List size : " + mortgageFormsLists);
    }


    public LinkedList<MortgageForm> fetchMortgageApp() {
        Collections.sort(mortgageFormsLists, MortgageForm.offerDateComparator);
        return mortgageFormsLists;
    }

    public MortgageForm getById(String id) {
        MortgageForm latestMortgageForm = null;
        for (MortgageForm mortgageForm : mortgageFormsLists) {
            if (mortgageForm.getMortgageId().equalsIgnoreCase(id)) {
                if (null == latestMortgageForm) {
                    latestMortgageForm = mortgageForm;
                } else if (mortgageForm.getVersion() > latestMortgageForm.getVersion()) {
                    latestMortgageForm = mortgageForm;
                }
            }
        }
        return latestMortgageForm;
    }


    public void updateOfferExpiredAppData() {
        logger.info("Job started-->");
        ListIterator<MortgageForm> mortgageFormListIterator = mortgageFormsLists.listIterator();
        while (mortgageFormListIterator.hasNext()) {
            long days = ChronoUnit.DAYS.between(LocalDate.now(),
                    mortgageFormListIterator.next().getOfferDate());
            if (days > 0) {
                mortgageFormListIterator.next().setOfferExpired(true);
                logger.info("OfferExpired updated for MortgageId"+mortgageFormListIterator.next().getMortgageId());
            }
        }
    }
}
