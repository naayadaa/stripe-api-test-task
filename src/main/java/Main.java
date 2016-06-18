import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.*;
import com.stripe.net.RequestOptions;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Naya on 17.06.2016.
 */
public class Main {
    public static final String PLATFORM_SECRET_KEY = "sk_test_oNUjHVHmp4BSaa1SzgMO4BYS";

    public static void main(String[] args){

        Stripe.apiKey = PLATFORM_SECRET_KEY;
        try {
            /*RequestOptions requestOptions = RequestOptions.builder().setApiKey(PLATFORM_SECRET_KEY).build();
            Account account = Account.retrieve(requestOptions);
            System.out.println(account);*/

            StripeAPIExecutor stripeAPIExecutor = new StripeAPIExecutor();
            stripeAPIExecutor.deleteAllCustomers();

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
            tokenParams3.put("card", cardParams2);

            Customer nayaCustomer = stripeAPIExecutor.createCustomer(tokenParams, "Test customer Naya naya@example.com");
            Customer vanyaCustomer = stripeAPIExecutor.createCustomer(tokenParams2, "Test customer Vanya vanya@example.com");

            Card secondCard = (Card) stripeAPIExecutor.createCard(tokenParams3, nayaCustomer);

            Charge charge1 = stripeAPIExecutor.createCharge(tokenParams, 5000, "usd", "Charge for Naya");
            Charge charge2 = stripeAPIExecutor.createCharge(tokenParams2, 3000, "usd", "Charge for Vanya");
            Charge charge3 = stripeAPIExecutor.createCharge(tokenParams2, 8000, "usd", "Charge for second Naya's csrd");


        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (CardException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }
}
