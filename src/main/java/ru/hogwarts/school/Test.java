package ru.hogwarts.school;

import org.apache.commons.lang3.StringUtils;
import ru.hogwarts.school.service.StudentService;

public class Test {
    public static void main(String[] args) {
//        String name = "anna lihanova";
//        System.out.println(StringUtils.isAlpha(name));
//        System.out.println(StringUtils.capitalize(name));
//        System.out.println(StringUtils.deleteWhitespace(name));
//        System.out.println();
//        System.out.println(StringUtils.substringAfterLast(name, " "));
//        System.out.println(StringUtils.isAllUpperCase(name));
//        System.out.println(StringUtils.isAlphanumericSpace(name));
//        System.out.println(StringUtils.isWhitespace(name));

        Test c = new Test();
        System.out.println(c.vozvrat("шалаш"));
        System.out.println("Является палиндромом = " + c.isPalindrome("шалаш"));
    }

    public Boolean vozvrat(String a) {
        String b = "";
        String c = a.replace(" ", "");
        char[] mass = c.toCharArray();
        char[] mass2 = new char[mass.length];
        for (int i = mass.length - 1; i >= 0; ) {
            for (int j = 0; j < mass2.length; j++) {
                mass2[j] = mass[i];
                i--;
                b += mass2[j];
            }
        }
//        System.out.println(b);
//        System.out.println(c);
        return b.equalsIgnoreCase(c);
    }

    public boolean isPalindrome(String str) {
        return str.equalsIgnoreCase(new StringBuilder(str).reverse().toString());
    }
}