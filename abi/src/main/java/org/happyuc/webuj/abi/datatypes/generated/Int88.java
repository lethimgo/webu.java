package org.happyuc.webuj.abi.datatypes.generated;

import java.math.BigInteger;
import org.happyuc.webuj.abi.datatypes.Int;

/**
 * Auto generated code.
 * <p><strong>Do not modifiy!</strong>
 * <p>Please use org.happyuc.webuj.codegen.AbiTypesGenerator in the 
 * <a href="https://github.com/happyuc-project/webu.java/tree/master/codegen">codegen module</a> to update.
 */
public class Int88 extends Int {
    public static final Int88 DEFAULT = new Int88(BigInteger.ZERO);

    public Int88(BigInteger value) {
        super(88, value);
    }

    public Int88(long value) {
        this(BigInteger.valueOf(value));
    }
}
