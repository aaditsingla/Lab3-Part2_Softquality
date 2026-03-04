package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
    private String number="0";  // string containing the binary value '0' or '1'

    /**
     * A constructor that generates a binary object.
     *
     * @param number a String of the binary values. It should contain only zeros or ones with any length and order. otherwise, the value of "0" will be stored. Trailing zeros will be excluded and empty string will be considered as zero.
     */
    public Binary(String number) {
        if (number == null || number.isEmpty()) {
            this.number = "0";
            return;
        }

        // Validate the binary string (only '0' or '1' allowed)
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch != '0' && ch != '1') {
                this.number = "0";
                return;
            }
        }

        // Remove leading zeros
        int beg;
        for (beg = 0; beg < number.length(); beg++) {
            if (number.charAt(beg) != '0') {
                break;
            }
        }

        this.number = (beg == number.length()) ? "0" : number.substring(beg);

        if (this.number.isEmpty()) { // replace empty strings with a single zero
            this.number = "0";
        }
    }

    /**
     * Return the binary value of the variable
     *
     * @return the binary value in a string format.
     */
    public String getValue()
    {
        return this.number;
    }

    /**
     * Adding two binary variables.
     *
     * @param num1 The first addend object
     * @param num2 The second addend object
     * @return A binary variable with a value of num1+num2.
     */
    public static Binary add(Binary num1,Binary num2)
    {
        int ind1=num1.number.length()-1;
        int ind2=num2.number.length()-1;

        int carry=0;
        String num3="";

        while(ind1>=0 ||  ind2>=0 || carry!=0)
        {
            int sum=carry;

            if(ind1>=0){
                sum += (num1.number.charAt(ind1)=='1')? 1:0;
                ind1--;
            }
            if(ind2>=0){
                sum += (num2.number.charAt(ind2)=='1')? 1:0;
                ind2--;
            }

            carry=sum/2;
            sum=sum%2;
            num3 =( (sum==0)? "0":"1")+num3;
        }

        return new Binary(num3);
    }

    /**
     * Bitwise OR of two binary variables.
     *
     * @param num1 first operand
     * @param num2 second operand
     * @return num1 OR num2
     */
    public static Binary or(Binary num1, Binary num2)
    {
        String a = num1.number;
        String b = num2.number;

        int max = Math.max(a.length(), b.length());

        a = leftPadWithZeros(a, max);
        b = leftPadWithZeros(b, max);

        String out = "";
        for (int i = 0; i < max; i++) {
            char ca = a.charAt(i);
            char cb = b.charAt(i);
            out += (ca == '1' || cb == '1') ? "1" : "0";
        }

        return new Binary(out);
    }

    /**
     * Bitwise AND of two binary variables.
     *
     * @param num1 first operand
     * @param num2 second operand
     * @return num1 AND num2
     */
    public static Binary and(Binary num1, Binary num2)
    {
        String a = num1.number;
        String b = num2.number;

        int max = Math.max(a.length(), b.length());

        a = leftPadWithZeros(a, max);
        b = leftPadWithZeros(b, max);

        String out = "";
        for (int i = 0; i < max; i++) {
            char ca = a.charAt(i);
            char cb = b.charAt(i);
            out += (ca == '1' && cb == '1') ? "1" : "0";
        }

        return new Binary(out);
    }

    /**
     * Multiply two binary variables.
     * Uses repeated addition based on bits of the multiplier.
     *
     * @param num1 multiplicand
     * @param num2 multiplier
     * @return num1 * num2
     */
    public static Binary multiply(Binary num1, Binary num2)
    {
        // quick exits
        if (num1.number.equals("0") || num2.number.equals("0")) {
            return new Binary("0");
        }
        if (num1.number.equals("1")) {
            return new Binary(num2.number);
        }
        if (num2.number.equals("1")) {
            return new Binary(num1.number);
        }

        String a = num1.number;
        String b = num2.number;

        Binary result = new Binary("0");

        // iterate multiplier from right to left
        int shift = 0;
        for (int i = b.length() - 1; i >= 0; i--) {
            if (b.charAt(i) == '1') {
                Binary shifted = new Binary(a + repeatZeros(shift));
                result = add(result, shifted);
            }
            shift++;
        }

        return result;
    }

    // helpers
    private static String leftPadWithZeros(String s, int len) {
        if (s.length() >= len) return s;
        return repeatZeros(len - s.length()) + s;
    }

    private static String repeatZeros(int count) {
        String z = "";
        for (int i = 0; i < count; i++) z += "0";
        return z;
    }
}
