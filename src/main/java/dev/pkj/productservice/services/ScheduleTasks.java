package dev.pkj.productservice.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTasks {

    private ProductService productService;

    public ScheduleTasks(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    @Scheduled(cron = "0 * * * * *")
    public void execute() {
        productService.deleteAll();
    }


}
