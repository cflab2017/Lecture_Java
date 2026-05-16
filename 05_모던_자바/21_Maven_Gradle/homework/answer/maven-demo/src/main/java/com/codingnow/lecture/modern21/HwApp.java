package com.codingnow.lecture.modern21;

import org.apache.commons.lang3.StringUtils;

/** Commons Lang3 의 StringUtils.reverse 사용 데모. */
public class HwApp {
    public static void main(String[] args) {
        String s = "Hello, Java!";
        System.out.println("원본: " + s);
        System.out.println("뒤집기: " + StringUtils.reverse(s));
    }
}
