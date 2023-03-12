package com.chasermanager.services.impl;

import com.chasermanager.domain.dto.SwitcherCreate;
import com.chasermanager.domain.dto.SwitcherView;
import com.chasermanager.domain.enums.Periodicity;
import com.chasermanager.domain.enums.SwitcherStatus;
import com.chasermanager.domain.models.Switcher;
import com.chasermanager.domain.models.Url;
import com.chasermanager.domain.models.User;
import com.chasermanager.exceptions.AlreadyExistsException;
import com.chasermanager.exceptions.NotFoundException;
import com.chasermanager.mapper.SwitcherMapper;
import com.chasermanager.repositories.SwitcherRepository;
import com.chasermanager.repositories.UserRepository;
import com.chasermanager.services.PreparationService;
import com.chasermanager.services.SwitcherService;
import com.chasermanager.services.UrlService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SwitcherServiceImpl implements SwitcherService {
    private final SwitcherRepository switcherRepository;
    private final UserRepository userRepository;
    private final UrlService urlService;
    private final PreparationService preparationService;

    @Override
    public Switcher create(SwitcherCreate switcherCreate) throws NotFoundException, AlreadyExistsException {
        User user = userRepository.findByEmail("marat_bh@mail.ru");
        Url url = urlService.create(switcherCreate.getLink(), switcherCreate.getSource());
        boolean isExist = switcherRepository.existsByUserAndUrl(user, url);
        if (isExist) throw new AlreadyExistsException("You already have a chaser on this link");
        Switcher switcher = new Switcher();
        switcher.setUser(user);
        switcher.setUrl(url);
        switcher.setName(switcherCreate.getName());
        switcher.setPeriodicity(Periodicity.mapToNumbers(switcherCreate.getPeriodicity()));
        switcher.setStatus(SwitcherStatus.STOP);
        return switcherRepository.save(switcher);
    }

    @Override
    public SwitcherView findById(Long id) {
        Switcher switcher = findByIdOrThrowException(id);
        return SwitcherMapper.INSTANCE.toView(switcher);
    }

    @Override
    public void update(Long id, SwitcherStatus status, Periodicity periodicity) {
        Switcher switcher = findByIdOrThrowException(id);
        if (status != null) switcher.setStatus(status);
        if (periodicity != null) switcher.setPeriodicity(Periodicity.mapToNumbers(periodicity));
        switcherRepository.save(switcher);
    }

    private Switcher findByIdOrThrowException(Long id) {
        return switcherRepository.findById(id).orElseThrow(() -> new NotFoundException("Chaser by id №" + id + " not found"));
    }

    @Override
    public List<Switcher> findAllByPeriodicity(long periodicity) {
        return switcherRepository.findAllByPeriodicity(periodicity);
    }

    @Override
    public void run(Switcher switcher) throws IOException, MessagingException {
        if (switcher.getUrl().getSource().getName().equals("Победа-63")) {
            preparationService.preparePobeda63(switcher);
        }
    }

    @Override
    public void delete(Long id) {
        switcherRepository.deleteById(id);
    }

    @Override
    public Page<SwitcherView> findAllByCurrentUser(Pageable pageable) {
        User user = userRepository.findByEmail("marat_bh@mail.ru");
        return switcherRepository.findAllByUser(user, pageable)
                .map(SwitcherMapper.INSTANCE::toView);
    }
}
