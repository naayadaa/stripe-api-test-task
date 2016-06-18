import com.stripe.exception.*;
import com.stripe.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Naya on 18.06.2016.
 */
public class StripeAPIExecutor {

    public Customer createCustomer(Map<String, Object> tokenParams, String description) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("description", description);
        customerParams.put("source", Token.create(tokenParams).getId());
        return Customer.create(customerParams);
    }

    public ExternalAccount createCard(Map<String, Object> tokenParams, Customer customer) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("source", Token.create(tokenParams).getId());

        return customer.getSources().create(params);
    }

    public Charge createCharge(Map<String, Object> tokenParams,int amount, String currency, String decription) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency",currency);
        chargeParams.put("source", Token.create(tokenParams).getId());
        chargeParams.put("description", decription);

        return Charge.create(chargeParams);
    }

    public void deleteAllCustomers() throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        Map<String, Object> listCustomerParams = new HashMap<String, Object>();
        List<Customer> customerCollection = Customer.list(listCustomerParams).getData();

        if (customerCollection.size()>0)
            for(Customer cu : customerCollection){
                Customer.retrieve(cu.getId()).delete();
            }
    }
}
