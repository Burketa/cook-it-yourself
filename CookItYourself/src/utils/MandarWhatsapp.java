/*
package utils;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

public class MandarWhatsapp {

    // Find your Account Sid and Token at twilio.com/console 

    public static final String ACCOUNT_SID = "";
    public static final String AUTH_TOKEN = "";

    public static void enviar(String msg) {
        if (ACCOUNT_SID != "" && AUTH_TOKEN != "") {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("whatsapp:+554399707381"),
                    new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                    msg)
                    .create();
        }
    }
}
*/
