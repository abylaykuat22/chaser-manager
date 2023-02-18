package com.chasermanager.services.impl;

import com.chasermanager.domain.dto.SwitcherCreate;
import com.chasermanager.domain.enums.Periodicity;
import com.chasermanager.domain.enums.SwitcherStatus;
import com.chasermanager.domain.models.Source;
import com.chasermanager.domain.models.Switcher;
import com.chasermanager.domain.models.Url;
import com.chasermanager.domain.models.User;
import com.chasermanager.mapper.SwitcherMapper;
import com.chasermanager.repositories.SwitcherRepository;
import com.chasermanager.repositories.UserRepository;
import com.chasermanager.services.PreparationService;
import com.chasermanager.services.SourceService;
import com.chasermanager.services.SwitcherService;
import com.chasermanager.services.UrlService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SwitcherServiceImpl implements SwitcherService {
    private final SwitcherRepository switcherRepository;
    private final UserRepository userRepository;
    private final UrlService urlService;
    private final SourceService sourceService;
    private final PreparationService preparationService;

    @Override
    public void create(String sourceName, String link, Periodicity periodicity) {
        Source source = sourceService.findByName(sourceName);
        User user = userRepository.findByEmail("abylaykuat@gmail.com");
        boolean sourceExist = sourceService.isExist(source);
        if (sourceExist) {
            Url url = urlService.create(link, source);
            Switcher switcher = new Switcher();
            switcher.setUser(user);
            switcher.setUrl(url);
            switcher.setPeriodicity(Periodicity.calculate(periodicity));
            switcher.setStatus(SwitcherStatus.STOP);
            if (!isExist(switcher)){
                switcherRepository.save(switcher);
            }
        }
    }

    private boolean isExist(Switcher switcher) {
        List<Switcher> switchers = switcherRepository.findAll();
        List<SwitcherCreate> dtos = SwitcherMapper.INSTANCE.toCreateList(switchers);
        SwitcherCreate dto = SwitcherMapper.INSTANCE.toCreate(switcher);
        return dtos.contains(dto);
    }


    @Override
    public void setStatus(SwitcherStatus status, Long id) throws IOException, MessagingException {
        Switcher switcher = switcherRepository.findByEmailAndUrlId("abylaykuat@gmail.com", id);
        switcher.setStatus(status);
        switcherRepository.save(switcher);
        run(switcher);
    }

    @Override
    public void update(Long id, Periodicity periodicity) {
        Switcher switcher = switcherRepository.getReferenceById(id);
        switcher.setPeriodicity(Periodicity.calculate(periodicity));
        switcherRepository.save(switcher);
    }

    @Override
    public List<Switcher> findAllByPeriodicity(long periodicity) {
        return switcherRepository.findAllByPeriodicity(periodicity);
    }

    @Override
    public void run(Switcher switcher) throws IOException, MessagingException {
        if (switcher.getUrl().getSource().getName().equals("Победа-63")){
            preparationService.preparePobeda63(switcher);
        }
    }
}
