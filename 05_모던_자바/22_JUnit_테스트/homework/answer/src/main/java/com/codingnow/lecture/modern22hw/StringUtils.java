package com.codingnow.lecture.modern22hw;

/** 문자열 유틸. */
public class StringUtils {
    /** 문자열을 뒤집어 반환. */
    public static String reverse(String s) {
        if (s == null) return null;
        return new StringBuilder(s).reverse().toString();
    }

    /** 회문 여부 (대소문자 무시, 공백 무시). */
    public static boolean isPalindrome(String s) {
        if (s == null) return false;
        String t = s.replace(" ", "").toLowerCase();
        return t.equals(reverse(t));
    }
}
