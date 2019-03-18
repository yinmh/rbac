package com.westos.rbac.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author :ymh
 */
public class RandomCodeUtil {
    public static void main(String[] args) {
        System.out.println(randomCode());

    }

    public static String randomCode() {
        List<Character> chars = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'M', 'O', 'N','P','E','R');
        Collections.shuffle(chars);
        Collections.shuffle(chars);
        String s ="";
        for (int i = 0; i < 4; i++) {
            s+= chars.get(i);
        }
        return s;
    }
}
