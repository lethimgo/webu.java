package org.happyuc.webuj.protocol.admin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import org.happyuc.webuj.protocol.webujService;
import org.happyuc.webuj.protocol.admin.methods.response.NewAccountIdentifier;
import org.happyuc.webuj.protocol.admin.methods.response.PersonalListAccounts;
import org.happyuc.webuj.protocol.admin.methods.response.PersonalUnlockAccount;
import org.happyuc.webuj.protocol.core.JsonRpc2_0webuj;
import org.happyuc.webuj.protocol.core.Request;
import org.happyuc.webuj.protocol.core.methods.request.Transaction;
import org.happyuc.webuj.protocol.core.methods.response.HucSendTransaction;

/**
 * JSON-RPC 2.0 factory implementation for common Parity and Ghuc.
 */
public class JsonRpc2_0Admin extends JsonRpc2_0webuj implements Admin {

    public JsonRpc2_0Admin(webujService webujService) {
        super(webujService);
    }
    
    public JsonRpc2_0Admin(webujService webujService, long pollingInterval,
            ScheduledExecutorService scheduledExecutorService) {
        super(webujService, pollingInterval, scheduledExecutorService);
    }

    @Override
    public Request<?, PersonalListAccounts> personalListAccounts() {
        return new Request<>(
                "personal_listAccounts",
                Collections.<String>emptyList(),
                webujService,
                PersonalListAccounts.class);
    }

    @Override
    public Request<?, NewAccountIdentifier> personalNewAccount(String password) {
        return new Request<>(
                "personal_newAccount",
                Arrays.asList(password),
                webujService,
                NewAccountIdentifier.class);
    }   

    @Override
    public Request<?, PersonalUnlockAccount> personalUnlockAccount(
            String accountId, String password,
            BigInteger duration) {
        List<Object> attributes = new ArrayList<>(3);
        attributes.add(accountId);
        attributes.add(password);
        
        if (duration != null) {
            // Parity has a bug where it won't support a duration
            // See https://github.com/ethcore/parity/issues/1215
            attributes.add(duration.longValue());
        } else {
            // we still need to include the null value, otherwise Parity rejects request
            attributes.add(null);
        }
        
        return new Request<>(
                "personal_unlockAccount",
                attributes,
                webujService,
                PersonalUnlockAccount.class);
    }
    
    @Override
    public Request<?, PersonalUnlockAccount> personalUnlockAccount(
            String accountId, String password) {
        
        return personalUnlockAccount(accountId, password, null);
    }
    
    @Override
    public Request<?, HucSendTransaction> personalSendTransaction(
            Transaction transaction, String passphrase) {
        return new Request<>(
                "personal_sendTransaction",
                Arrays.asList(transaction, passphrase),
                webujService,
                HucSendTransaction.class);
    }
    
}
