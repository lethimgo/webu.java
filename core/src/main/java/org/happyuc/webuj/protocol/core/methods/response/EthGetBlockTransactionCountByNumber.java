package org.happyuc.webuj.protocol.core.methods.response;

import java.math.BigInteger;

import org.happyuc.webuj.protocol.core.Response;
import org.happyuc.webuj.utils.Numeric;

/**
 * huc_getBlockTransactionCountByNumber.
 */
public class HucGetBlockTransactionCountByNumber extends Response<String> {
    public BigInteger getTransactionCount() {
        return Numeric.decodeQuantity(getResult());
    }
}
