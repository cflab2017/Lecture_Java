package com.codingnow.lecture.modern22hw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilsTest {

    @Test
    void reverse_정상() {
        assertEquals("avaJ", StringUtils.reverse("Java"));
    }

    @Test
    void reverse_빈문자열() {
        assertEquals("", StringUtils.reverse(""));
    }

    @Test
    void reverse_null_은_null() {
        assertNull(StringUtils.reverse(null));
    }

    @Test
    void palindrome_단어() {
        assertTrue(StringUtils.isPalindrome("level"));
    }

    @Test
    void palindrome_공백_대소문자_무시() {
        assertTrue(StringUtils.isPalindrome("A man a plan a canal Panama"));
    }

    @Test
    void palindrome_아닌_경우() {
        assertFalse(StringUtils.isPalindrome("Hello"));
    }
}
