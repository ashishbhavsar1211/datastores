package com.barclays.datastore.service;

import com.barclays.datastore.model.MortgageForm;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;

@RunWith(SpringJUnit4ClassRunner.class)
public class StoreMortgageAppDataServiceTest {

    private StoreMortgageAppDataService storeMortgageAppDataService;

    private LinkedList<MortgageForm> mortgageFormLinkedList;

    @BeforeEach
    public void setup() {

        mortgageFormLinkedList = new LinkedList<>();

        MortgageForm form = new MortgageForm();
        form.setOfferExpired(false);
        form.setVersion(1);
        form.setMortgageId("m1");
        form.setOfferDate(LocalDate.of(2021, 01, 22));
        form.setCreatedDate(LocalDate.of(2020, 02, 01));
        form.setTemdate(new Date());
        mortgageFormLinkedList.add(form);

        MortgageForm mortgageForm1 = new MortgageForm();
        mortgageForm1.setOfferExpired(false);
        mortgageForm1.setVersion(1);
        mortgageForm1.setMortgageId("m2");
        mortgageForm1.setOfferDate(LocalDate.of(2021, 10, 12));
        mortgageForm1.setCreatedDate(LocalDate.of(2020, 05, 01));
        mortgageFormLinkedList.add(mortgageForm1);

        MortgageForm mortgageForm2 = new MortgageForm();
        mortgageForm2.setOfferExpired(false);
        mortgageForm2.setVersion(1);
        mortgageForm2.setMortgageId("m2");
        mortgageForm2.setOfferDate(LocalDate.of(2021, 9, 12));
        mortgageForm2.setCreatedDate(LocalDate.of(2020, 05, 01));
        mortgageFormLinkedList.add(mortgageForm2);

        MortgageForm mortgageForm3 = new MortgageForm();
        mortgageForm3.setOfferExpired(false);
        mortgageForm3.setVersion(2);
        mortgageForm3.setMortgageId("m2");
        mortgageForm3.setOfferDate(LocalDate.of(2020, 7, 12));
        mortgageForm3.setCreatedDate(LocalDate.of(2020, 11, 01));
        mortgageFormLinkedList.add(mortgageForm3);
        storeMortgageAppDataService = new StoreMortgageAppDataService(mortgageFormLinkedList);
    }

    @Test
    public void storeMortgageDataTest() {

        MortgageForm mortgageForm5 = new MortgageForm();
        mortgageForm5.setOfferExpired(false);
        mortgageForm5.setVersion(3);
        mortgageForm5.setMortgageId("m2");
        mortgageForm5.setOfferDate(LocalDate.of(2021, 7, 12));
        mortgageForm5.setCreatedDate(LocalDate.of(2020, 11, 01));
        storeMortgageAppDataService.storeMortgageData(mortgageForm5);
        Assert.assertEquals(true, mortgageFormLinkedList.contains(mortgageForm5));
    }

    @Test
    public void getAllFormTest() {
        LinkedList<MortgageForm> mortgageForms = storeMortgageAppDataService.fetchMortgageApp();
        Assert.assertNotNull(mortgageForms);
    }

    @Test
    public void getByIdTest() {
        MortgageForm m1 = storeMortgageAppDataService.getById("m1");
        Assert.assertNotNull(m1);
    }

     @Test
    public void offerExpiredTest()  {
            storeMortgageAppDataService.updateOfferExpiredAppData();
            Assert.assertEquals(true,mortgageFormLinkedList.get(3).getOfferExpired());
    }
}
