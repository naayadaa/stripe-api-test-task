import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.*;
import com.stripe.net.RequestOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Naya on 17.06.2016.
 */
public class Main {
    public static final String PLATFORM_SECRET_KEY = "sk_test_oNUjHVHmp4BSaa1SzgMO4BYS";

    public static void main(String[] args){

        Stripe.apiKey = PLATFORM_SECRET_KEY;
        RequestOptions requestOptions = RequestOptions.builder().setApiKey(PLATFORM_SECRET_KEY).build();
        try {
            /*Account account = Account.retrieve(requestOptions);
            System.out.println(account);*/

            Map<String, Object> tokenParams = new HashMap<String, Object>();
            Map<String, Object> cardParams = new HashMap<String, Object>();
            cardParams.put("number", "4242424242424242");
            cardParams.put("exp_month", 6);
            cardParams.put("exp_year", 2017);
            cardParams.put("cvc", "314");
            tokenParams.put("card", cardParams);

            Token token = Token.create(tokenParams);

            Map<String, Object> customerParams = new HashMap<String, Object>();
            customerParams.put("description", "Test customer Naya test@example.com");
            customerParams.put("source", token.getId());

            Customer customer = Customer.create(customerParams);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("source", Token.create(tokenParams).getId());

            System.out.println(customer.getSources().create(params));

            Map<String, Object> chargeParams = new HashMap<String, Object>();
            chargeParams.put("amount", 400);
            chargeParams.put("currency", "usd");
            chargeParams.put("source", Token.create(tokenParams).getId());
            chargeParams.put("description", "Charge for Naya test@example.com");

            Charge.create(chargeParams);

            Map<String, Object> listCustomerParams = new HashMap<String, Object>();
            customerParams.put("limit", 20);
            List<Customer> customerCollection = Customer.list(listCustomerParams).getData();

            for(Customer cu : customerCollection){
                Customer.retrieve(cu.getId()).delete();
            }


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
