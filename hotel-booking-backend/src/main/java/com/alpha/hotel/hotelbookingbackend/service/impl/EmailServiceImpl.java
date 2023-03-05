package com.alpha.hotel.hotelbookingbackend.service.impl;

import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;
import com.alpha.hotel.hotelbookingbackend.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Value( "${email.server.username}" )
    private String emailServerUsername;

    @Value( "${email.server.password}" )
    private String emailServerPassword;

    @Value( "${email.server.host}" )
    private String emailServerHost;

    @Value( "${email.server.port}" )
    private String emailServerPort;

    @Value( "${email.server.auth}" )
    private String emailServerAuth;

    @Value( "${email.server.starttls.enable}" )
    private String emailServerTtlsEnable;

    @Value( "${email.internal.address}" )
    private String emailInternalAddress;

    @Override
    public void sendEmail(String toAddress, String content, String topic) throws HotelBookingException {
        logger.debug("Sending email to:{} with topic:{}", toAddress, topic);
        Properties prop = new Properties();
        prop.put("mail.smtp.host", emailServerHost);
        prop.put("mail.smtp.port", emailServerPort);
        prop.put("mail.smtp.auth", emailServerAuth);
        prop.put("mail.smtp.starttls.enable", emailServerTtlsEnable); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailServerUsername, emailServerPassword);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailInternalAddress));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toAddress)
            );
            message.setSubject("Email confirmation " + topic);
            message.setContent(
                    "<style>    .button {        background-color: #FFB201;     " +
                            "   border: none;        color: white;        padding: 15px 32px;      " +
                            "  text-align: center;        text-decoration: none;        display: inline-block;    " +
                            "    font-size: 16px;        margin: 4px 2px;        cursor: pointer;        opacity: .8; " +
                            "   }    .button:hover {        opacity: 1;    }</style><div style=\"max-width: 800px; " +
                            "margin: auto\">    <div style=\"background-color: #FFF; text-align: center;\">    " +
                            "    <h1 style=\"color: #35B7E8!important; padding-top: 40px;\">Email Confirmation for "
                            + topic + " !</h1>    </div>    <div style=\"text-align: center; max-width: 700px; " +
                            "margin: 40px auto; color: #868686 \"> " + content +
                            "   </div>    <div style=\"text-align: center; max-width: 700px; margin: 40px auto; " +
                            "color: #868686 \">        <p style=\"font-size: 17px;\">          " +
                            "  <br>Yours sincerely,<br>            ADL DTE Team<br>       " +
                            "     <br>Contact Information:<br>            E-mail : " + emailInternalAddress + "<br>    " +
                            "    </p>    </div></div>",
                    "text/html");

            Transport.send(message);

            logger.info("Successfully sent the email");

        }
        catch ( Exception e ) {
            logger.error("Failed to send the email confirmation of:{}", topic);
            throw new HotelBookingException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Failed to send the email confirmation of:%s", topic));
        }
    }
}
