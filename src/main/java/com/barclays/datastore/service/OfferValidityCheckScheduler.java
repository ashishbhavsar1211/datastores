package com.barclays.datastore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class OfferValidityCheckScheduler {

    private static final Logger logger = LoggerFactory.getLogger(OfferValidityCheckScheduler.class);

    @Autowired
    private StoreMortgageAppDataService storeMortgageAppDataService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduleTaskWithCronExpression() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        logger.info("Daily Scheduler Started:" + strDate  );
        storeMortgageAppDataService.updateOfferExpiredAppData();
        logger.info("Job executed.");
    }
}
