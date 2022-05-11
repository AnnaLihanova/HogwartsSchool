package ru.hogwarts.school;

import org.apache.commons.lang3.StringUtils;
import ru.hogwarts.school.service.StudentService;

public class Test {
     public static void main(String[] args) {
        String name = "anna lihanova";
        System.out.println(StringUtils.isAlpha(name));
        System.out.println(StringUtils.capitalize(name));
        System.out.println(StringUtils.deleteWhitespace(name));
        System.out.println();
        System.out.println(StringUtils.substringAfterLast(name, " "));
        System.out.println(StringUtils.isAllUpperCase(name));
        System.out.println(StringUtils.isAlphanumericSpace(name));
        System.out.println(StringUtils.isWhitespace(name));
    }


}