package com.chasermanager.chasers;

import com.chasermanager.domain.models.Switcher;
import com.chasermanager.services.SwitcherService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChaserRunner {
    private final SwitcherService switcherService;

    private static final long twoMin = 60000 * 2;
    private static final long fiveMin = 60000 * 5;
    private static final long fifteenMin = 60000 * 15;
    private static final long halfHour = 60000 * 30;
    private static final long oneHour = 60000 * 60;
    private static final long twoHour = 60000 * 60 * 2;


    @Scheduled(fixedDelay = twoMin)
    void twoMin() {
        List<Switcher> switchers = switcherService.findAllByPeriodicity(twoMin);
        run(switchers);
    }

    @Scheduled(fixedDelay = fiveMin)
    void fiveMin() {
        List<Switcher> switchers = switcherService.findAllByPeriodicity(fiveMin);
        run(switchers);
    }

    @Scheduled(fixedDelay = fifteenMin)
    void fifteenMin() {
        List<Switcher> switchers = switcherService.findAllByPeriodicity(fifteenMin);
        run(switchers);
    }

    @Scheduled(fixedDelay = halfHour)
    void halfHour() {
        List<Switcher> switchers = switcherService.findAllByPeriodicity(halfHour);
        run(switchers);
    }

    @Scheduled(fixedDelay = oneHour)
    void oneHour() {
        List<Switcher> switchers = switcherService.findAllByPeriodicity(oneHour);
        run(switchers);
    }

    @Scheduled(fixedDelay = twoHour)
    void twoHour() {
        List<Switcher> switchers = switcherService.findAllByPeriodicity(twoHour);
        run(switchers);
    }

    private void run(List<Switcher> switchers) {
        switchers.forEach(switcher -> {
            try {
                switcherService.run(switcher);
            } catch (IOException | MessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
