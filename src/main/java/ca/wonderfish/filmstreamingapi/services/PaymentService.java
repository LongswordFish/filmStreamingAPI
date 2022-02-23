package ca.wonderfish.filmstreamingapi.services;

import ca.wonderfish.filmstreamingapi.repositories.ChargeRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    private String API_SECRET_KEY = "sk_test_51KDKOlHWJmqNhsMxSn0N8AiJAKb17Gyx9qLLEUnquzTl9TCJDMMYutgVHshVmEGsFUdv2AMRd0i3ucvP6ecWQR7V00sDIHoamW";

    private ChargeRepository chargeRepository;

    public String createCharge(String token_id, double amount)
    {
        System.out.println("token is "+token_id);
        String id = null;
        Stripe.apiKey = API_SECRET_KEY;
        try{
            Map chargeParams = new HashMap<>();
            chargeParams.put("amount", (int)(amount*100));
            chargeParams.put("currency", "USD");
            chargeParams.put("description", "Charge for ");
            chargeParams.put("source", token_id);
            Charge charge = Charge.create(chargeParams);

        }
        catch(StripeException e)
        {
            throw new RuntimeException("Unable to process the charge", e);
        }
        return id;
    }
}
