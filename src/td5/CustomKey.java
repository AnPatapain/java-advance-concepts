package td5;

import java.io.Serializable;
import java.math.BigInteger;

public class CustomKey implements Serializable {
    private BigInteger modulus;
    private BigInteger exponent;

    private int modulusBitCounts;
    private int exponentBitCounts;

    public CustomKey(BigInteger modulus, BigInteger exponent) {
        this.modulus = modulus;
        this.exponent = exponent;
        this.modulusBitCounts = modulus.toByteArray().length;
        this.exponentBitCounts = exponent.toByteArray().length;
    }

    public BigInteger getExponent() {
        return exponent;
    }

    public BigInteger getModulus() {
        return modulus;
    }

    public int getExponentBitCounts() {
        return exponentBitCounts;
    }

    public int getModulusBitCounts() {
        return modulusBitCounts;
    }
}
