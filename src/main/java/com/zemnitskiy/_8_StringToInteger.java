package com.zemnitskiy;
/*
https://leetcode.com/problems/string-to-integer-atoi/description/
Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.

The algorithm for myAtoi(string s) is as follows:

Whitespace: Ignore any leading whitespace (" ").
Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if neither present.
Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached. If no digits were read, then the result is 0.
Rounding: If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then round the integer to remain in the range. Specifically, integers less than -231 should be rounded to -231, and integers greater than 231 - 1 should be rounded to 231 - 1.
Return the integer as the final result.
*/

public class _8_StringToInteger {
    public static void main(String[] args) {
        System.out.println(_8_StringToInteger.myAtoi("42"));
        System.out.println(_8_StringToInteger.myAtoi(" -042"));
        System.out.println(_8_StringToInteger.myAtoi("1337c0d3"));
        System.out.println(_8_StringToInteger.myAtoi("+1"));
    }

    public static int myAtoi(String s) {
        if (s.isBlank()) return 0;
        s = s.trim();
        boolean isNegate = s.charAt(0) == '-';
        s = s.replaceFirst("^[+-]", "");
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                sb.append(c);
            } else {
                break;
            }
        }
        if (sb.isEmpty()) return 0;
        int res;
        try {
            res = Integer.parseInt(sb.toString());
        } catch (NumberFormatException e) {
            return isNegate ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        return isNegate ? -res : res;
    }
}
