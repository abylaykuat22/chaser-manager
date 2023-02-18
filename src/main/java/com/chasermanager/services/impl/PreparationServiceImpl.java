package com.chasermanager.services.impl;

import com.chasermanager.domain.models.Item;
import com.chasermanager.domain.models.Source;
import com.chasermanager.domain.models.Switcher;
import com.chasermanager.repositories.SourceRepository;
import com.chasermanager.repositories.UserRepository;
import com.chasermanager.services.ItemService;
import com.chasermanager.services.PreparationService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PreparationServiceImpl implements PreparationService {
    private final ItemService itemService;
    private final UserRepository userRepository;
    private final EmailSenderService emailSenderService;
    private final SourceRepository sourceRepository;

    @Override
    public void preparePobeda63(Switcher switcher) throws IOException, MessagingException {
        var document = Jsoup.connect(switcher.getUrl().getLink()).get();

        Elements elements = document.getElementsByClass("card is-lazy");
        Elements content;
        Elements images;
        List<Item> items = new ArrayList<>();

        for (Element element : elements) {
            content = element.getElementsByClass("card-content");
            images = element.getElementsByClass("card-images");

            Item item = new Item();
            item.setName(getName(content));
            item.setDescription(getDescription(content));
            item.setPrice(getPrice(content));
            item.setLink(getLink(content));
            item.setImg(getImg(images));
            item.setUser(userRepository.getReferenceById(switcher.getId()));
            item.setSource(sourceRepository.findByName("Победа-63"));

            Item newItem = itemService.create(item, switcher.getUser().getId());

            if (newItem != null)
                items.add(item);
        }


        String text = emailSenderService.createMessage(items);
        String email = switcher.getUser().getEmail();
        String subject = Source.generateSubject("Победа-63");

        if (text != null) emailSenderService.send(email, text, subject);
    }

    private String getName(Elements content) {
        return Objects.requireNonNull(content.select("meta").first()).attr("content");
    }

    private String getDescription(Elements content) {
        return Objects.requireNonNull(content.select("meta").next()).attr("content");
    }

    private String getPrice(Elements content) {
        Elements data;
        String price;
        String currency;
        for (Element element : content) {
            data = element.getElementsByClass("card-price");
            price = data.attr("content");
            currency = Objects.requireNonNull(data.select("span").first()).text();
            return price +" "+currency;
        }
        return null;
    }

    private String getLink(Elements content) {
        StringBuilder https = new StringBuilder();
        https.append("https:");
        for (Element element : content) {
            return String.valueOf(https.append(element.getElementsByClass("card-title").attr("href")));
        }
        return null;
    }

    private String getImg(Elements images) {
        StringBuilder link = new StringBuilder();
        link.append("https://xn---63-5cdesg4ei.xn--p1ai/");
        for (Element element : images) {
            return String.valueOf(link.append(Objects.requireNonNull(element.getElementsByClass("card-images-img").select("img").first()).attr("data-img-load")));
        }
        return null;
    }
}