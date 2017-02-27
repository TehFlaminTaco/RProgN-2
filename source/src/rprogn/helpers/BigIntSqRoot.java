package rprogn.helpers;

import java.math.BigDecimal;
import java.math.BigInteger;

// Credit to Stack Overflow User Ilya_Gazman

public class BigIntSqRoot {
    private static final int PRECISION = 64;
    private static BigInteger multiplier = BigInteger.valueOf(10).pow(PRECISION * 2);
    private static BigDecimal root = BigDecimal.valueOf(10).pow(PRECISION);
    private static BigInteger two = BigInteger.valueOf(2L);

    public static BigDecimal bigDecimalSqRootFloor(BigInteger x)
            throws IllegalArgumentException {
        BigInteger result = bigIntSqRootFloor(x.multiply(multiplier));
        //noinspection BigDecimalMethodWithoutRoundingCalled
        return new BigDecimal(result).divide(root);
    }

    public static BigInteger bigIntSqRootFloor(BigInteger x)
            throws IllegalArgumentException {
        if (checkTrivial(x)) {
            return x;
        }
        if (x.bitLength() < 64) { // Can be cast to long
            double sqrt = Math.sqrt(x.longValue());
            return BigInteger.valueOf(Math.round(sqrt));
        }
        // starting with y = x / 2 avoids magnitude issues with x squared
        BigInteger y = x.divide(two);
        BigInteger value = x.divide(y);
        while (y.compareTo(value) > 0) {
            y = value.add(y).divide(two);
            value = x.divide(y);
        }
        return y;
    }

    public static BigInteger bigIntSqRootCeil(BigInteger x)
            throws IllegalArgumentException {
        BigInteger y = bigIntSqRootFloor(x);
        if (x.compareTo(y.multiply(y)) == 0) {
            return y;
        }
        return y.add(BigInteger.ONE);
    }

    private static boolean checkTrivial(BigInteger x) {
        if (x == null) {
            throw new NullPointerException("x can't be null");
        }
        if (x.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Negative argument.");
        }

        return x.equals(BigInteger.ZERO) || x.equals(BigInteger.ONE);
    }
}