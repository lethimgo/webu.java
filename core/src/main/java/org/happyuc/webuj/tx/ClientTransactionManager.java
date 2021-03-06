package org.happyuc.webuj.tx;

import java.io.IOException;
import java.math.BigInteger;

import org.happyuc.webuj.protocol.webuj;
import org.happyuc.webuj.protocol.core.methods.request.Transaction;
import org.happyuc.webuj.protocol.core.methods.response.HucSendTransaction;
import org.happyuc.webuj.tx.response.TransactionReceiptProcessor;

/**
 * TransactionManager implementation for using an HappyUC node to transact.
 *
 * <p><b>Note</b>: accounts must be unlocked on the node for transactions to be successful.
 */
public class ClientTransactionManager extends TransactionManager {

    private final webuj webuj;

    public ClientTransactionManager(
            webuj webuj, String fromAddress) {
        super(webuj, fromAddress);
        this.webuj = webuj;
    }

    public ClientTransactionManager(
            webuj webuj, String fromAddress, int attempts, int sleepDuration) {
        super(webuj, attempts, sleepDuration, fromAddress);
        this.webuj = webuj;
    }

    public ClientTransactionManager(
            webuj webuj, String fromAddress,
            TransactionReceiptProcessor transactionReceiptProcessor) {
        super(transactionReceiptProcessor, fromAddress);
        this.webuj = webuj;
    }

    @Override
    public HucSendTransaction sendTransaction(
            BigInteger gasPrice, BigInteger gasLimit, String to,
            String data, BigInteger value)
            throws IOException {

        Transaction transaction = new Transaction(
                getFromAddress(), null, gasPrice, gasLimit, to, value, data);

        return webuj.hucSendTransaction(transaction)
                .send();
    }
}
