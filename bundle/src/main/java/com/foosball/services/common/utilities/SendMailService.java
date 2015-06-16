package com.foosball.services.common.utilities;

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.ComponentContext;
import org.slf4j.LoggerFactory;

import java.io.File;

@Component(immediate = true, label = "Foosball Send Email Service", description = "Sends an email with the supplied parameters", metatype = true)
@Service(value = SendMailService.class)

public class SendMailService {

    private org.slf4j.Logger log = LoggerFactory.getLogger(SendMailService.class);

    @Reference
    MessageGatewayService messageGatewayService;


    protected void activate(ComponentContext context) {

    }

    protected void deactivate(ComponentContext context) {

    }

    public boolean sendEmail(String emailAddresses, String emailBody, File file, String reportType, String subject) {

        // Get the emailMessageGateway to use in sending the message
        MessageGateway<Email> emailMessageGateway = messageGatewayService.getGateway(Email.class);

        // If the gateway is null, we can't send, so return false
        if (emailMessageGateway == null) {
            return false;
        }

        try {

            // Create the attachment using given params
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(file.getPath());
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription(reportType);
            attachment.setName(reportType);

            // Create the email using given params and the attachment created above
            MultiPartEmail email = new MultiPartEmail();
            email.setTLS(true);
            email.attach(attachment);
            email.setSubject(subject);
            email.setMsg(emailBody);

            // Parse the email addresses to send to
            String[] emails = emailAddresses.split(",");
            for (String emailAdd : emails) {

                // Send the emails
                log.info("Sending email to " + emailAdd);
                email.addTo(emailAdd);
            }
            emailMessageGateway.send(email);
        } catch (EmailException ex) {
            log.error("EMAIL FAILED: " + ex.getMessage());
            return false;
        }

        return true;
    }

    public boolean sendEmail(String emailAddresses, String emailBody, String subject) {

        // Get the emailMessageGateway to use in sending the message
        MessageGateway<Email> emailMessageGateway = messageGatewayService.getGateway(Email.class);

        // If the gateway is null, we can't send, so return false
        if (emailMessageGateway == null) {
            return false;
        }

        try {

            // Create the email using given params and the attachment created above
            MultiPartEmail email = new MultiPartEmail();
            email.setTLS(true);
            email.setSubject(subject);
            email.setMsg(emailBody);

            // Parse the email addresses to send to
            String[] emails = emailAddresses.split(",");
            for (String emailAdd : emails) {

                // Send the emails
                log.info("Sending email to " + emailAdd);
                email.addTo(emailAdd);
            }
            emailMessageGateway.send(email);
        } catch (EmailException ex) {
            log.error("EMAIL FAILED: " + ex.getMessage());
            return false;
        }

        return true;
    }
}
