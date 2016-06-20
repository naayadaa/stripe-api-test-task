import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.*;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Naya on 17.06.2016.
 */
public class Main {

    public static void main(String[] args) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        if(args.length < 1)
            throw new IllegalArgumentException("specify your secret key");

        Stripe.apiKey = args[0];

        StripeAPIExecutor stripeAPIExecutor = new StripeAPIExecutor();

        Map<String, Object> tokenParams = new HashMap<String, Object>();
        Map<String, Object> cardParams = new HashMap<String, Object>();
        cardParams.put("number", "4242424242424242");
        cardParams.put("exp_month", 6);
        cardParams.put("exp_year", 2017);
        cardParams.put("cvc", "314");
        tokenParams.put("card", cardParams);

        Map<String, Object> tokenParams2 = new HashMap<String, Object>();
        Map<String, Object> cardParams2 = new HashMap<String, Object>();
        cardParams2.put("number", "4242424242424242");
        cardParams2.put("exp_month", 12);
        cardParams2.put("exp_year", 2017);
        cardParams2.put("cvc", "123");
        tokenParams2.put("card", cardParams2);

        Map<String, Object> tokenParams3 = new HashMap<String, Object>();
        Map<String, Object> cardParams3 = new HashMap<String, Object>();
        cardParams3.put("number", "4242424242424242");
        cardParams3.put("exp_month", 8);
        cardParams3.put("exp_year", 2017);
        cardParams3.put("cvc", "123");
        tokenParams3.put("card", cardParams3);

        Customer nayaCustomer = stripeAPIExecutor.createCustomer(tokenParams, "Test customer Naya naya@example.com");
        stripeAPIExecutor.createCustomer(tokenParams2, "Test customer Vanya vanya@example.com");

        stripeAPIExecutor.createCard(tokenParams3, nayaCustomer);

        stripeAPIExecutor.createCharge(tokenParams, 5000, "usd", "Charge for Naya");
        stripeAPIExecutor.createCharge(tokenParams2, 3000, "usd", "Charge for Vanya");
        stripeAPIExecutor.createCharge(tokenParams3, 8000, "usd", "Charge for second Naya's csrd");
    }
}
