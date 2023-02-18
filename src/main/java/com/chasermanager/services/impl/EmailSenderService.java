package com.chasermanager.services.impl;

import com.chasermanager.domain.models.Item;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmailSenderService {
    private final JavaMailSender mailSender;

    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String toEmail,
                     String text,
                     String subject) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("chaser.manager@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(text, true);
        mailSender.send(message);
    }

    public String createMessage(List<Item> items) {
        Document text = Jsoup.parse(generateHtml());
        items.forEach(item -> Objects.requireNonNull(text.select("div").first()).append(generateContent(item)));
//        for (Item item : items) {
//            Element data = text.select("div").first();
//            assert data != null;
//            data.append(generateContent(item));
//        }
        return String.valueOf(text);
    }

    private String generateHtml() {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Title</title>
                </head>
                <body>
                <div>
                               
                </div>
                </body>
                </html>""";
    }

    private String generateContent(Item item) {
        return " <div>\n" +
                "   <div style=\"display:flex\">\n" +
                "     <div style=\"padding:5px; width:20%;\">\n" +
                "       <img style=\"border-radius:5px; width: 100%; height: 100%;\" src=\"" + item.getImg() + "\">\n" +
                "     </div>\n" +
                "     <div style=\"width:80%; padding:5px; font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;\">\n" +
                "       <div style=\"display: flex;\">\n" +
                "         <div>\n" +
                "           <h1 style=\"margin: 0px; color: rgb(94, 94, 226);\">Наименование:</h1>\n" +
                "         </div>\n" +
                "         &nbsp;&nbsp;\n" +
                "         <div>\n" +
                "           <h1 style=\"margin: 0px; color: rgb(94, 94, 226);\">" + item.getName() + "</h1>\n" +
                "         </div>\n" +
                "       </div>\n" +
                "       <div style=\"display: flex;\">\n" +
                "         <div>\n" +
                "           <h2>Цена: </h2>\n" +
                "         </div>\n" +
                "         &nbsp;&nbsp;\n" +
                "         <div>\n" +
                "           <h2>" + item.getPrice() + "</h2>\n" +
                "         </div>\n" +
                "       </div>\n" +
                "       <div style=\"display: flex;\">\n" +
                "         <div>\n" +
                "           <p>Описание: </p>\n" +
                "         </div>\n" +
                "         &nbsp;&nbsp;\n" +
                "         <div>\n" +
                "           <p>" + item.getDescription() + "</p>\n" +
                "         </div>\n" +
                "       </div>\n" +
                "       <div style=\"display: flex;\">\n" +
                "         <div>\n" +
                "           <p>Ссылка на источник: </p>\n" +
                "         </div>\n" +
                "         &nbsp;&nbsp;\n" +
                "         <div>\n" +
                "           <a href=" + item.getLink() + "><p style=\"font-style: oblique; color: blue;\">перейти по ссылке...</p></a>\n" +
                "         </div>\n" +
                "       </div>\n" +
                "     </div>\n" +
                "   </div>\n" +
                "   <hr style=\"margin-left: 12px; margin-right: 12px; margin-bottom: 24px; margin-top: 24px;\">\n" +
                " </div>";
    }
}
