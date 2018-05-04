package org.happyuc.webuj.protocol.core.methods.response;

import org.happyuc.webuj.protocol.core.Response;

/**
 * huc_protocolVersion.
 */
public class HucProtocolVersion extends Response<String> {
    public String getProtocolVersion() {
        return getResult();
    }
}
