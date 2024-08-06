package com.api.product.stock.control.infra;

import com.api.product.stock.control.product.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.springframework.context.i18n.LocaleContextHolder;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    private static final String TEMPLATE_NAME = "registration";
    private static final String IMAGE = "templates/img/img.png";
    private static final String PNG_MIME = "image/png";
    private static final String MAIL_SUBJECT = "Seja bem vindo(a)!";

    private final Environment environment;
    private final JavaMailSender mailSender;
    private final TemplateEngine htmlTemplateEngine;

    public EmailService(Environment environment, JavaMailSender mailSender, TemplateEngine htmlTemplateEngine) {
        this.environment = environment;
        this.mailSender = mailSender;
        this.htmlTemplateEngine = htmlTemplateEngine;
    }

    /*
     * @throws MessagingException se houver um erro ao enviar o e-mail
     * @throws UnsupportedEncodingException se houver um erro com a codificação de caracteres
    */
    public void sendMail(Product product) throws MessagingException, UnsupportedEncodingException {
        // Gera a URL de confirmação (em uma aplicação real, isso seria gerado dinamicamente)
        String confirmationUrl = "generated_confirmation_url";
        // Recupera propriedades de e-mail do ambiente
        String mailFrom = environment.getProperty("spring.mail.properties.mail.smtp.from");
        String mailFromName = environment.getProperty("mail.from.name", "Identity");

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper email = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        email.setTo(product.getSupplier().getEmail());
        email.setSubject(MAIL_SUBJECT);
        email.setFrom(new InternetAddress(mailFrom, mailFromName));

        final Context ctx = new Context(LocaleContextHolder.getLocale());
        ctx.setVariable("email", product.getSupplier().getEmail());
        ctx.setVariable("name", product.getName());
        ctx.setVariable("logo", IMAGE);
        ctx.setVariable("url", confirmationUrl);

        // Processa o template HTML com o contexto fornecido
        final String htmlContent = this.htmlTemplateEngine.process(TEMPLATE_NAME, ctx);
        email.setText(htmlContent, true);

        ClassPathResource clr = new ClassPathResource(IMAGE);
        email.addInline("logo", clr, PNG_MIME);

        mailSender.send(mimeMessage);
    }
}
