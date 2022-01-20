package com.company;

import java.util.ArrayList;
import java.util.List;

public class FormatStrings {

    private static final int ROW_LENGTH = 16;
    private static final String RIGHT = "RIGHT";
    private static final String LEFT = "LEFT";
    private static final String[] DIRECTION_ARRAY = new String[]{LEFT, RIGHT, LEFT};
    private static final String[][] STRING_ARRAY = new String[][]{
            {"Hello", "world"},
            {"Brad", "came", "to", "dinner", "with", "us"},
            {"He", "loves", "tacos"}
    };
    private static final List<String> RESULT_LIST = new ArrayList<>();

    public static void main(String[] args) {
        RESULT_LIST.add("*".repeat(ROW_LENGTH + 2));
        for (int i = 0; i < STRING_ARRAY.length; i++) {
            String direction = DIRECTION_ARRAY[i];
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < STRING_ARRAY[i].length; j++) {
                if (!addWord(sb, STRING_ARRAY[i][j])) {
                    RESULT_LIST.add(formatRow(direction, sb.toString().trim()));
                    sb = new StringBuilder();
                }
            }
            RESULT_LIST.add(formatRow(direction, sb.toString().trim()));
        }
        RESULT_LIST.add("*".repeat(ROW_LENGTH + 2));

        for (String row : RESULT_LIST) {
            System.out.println(row);
        }
    }

    public static boolean addWord(StringBuilder sb, String word) {
        if (sb.toString().length() + word.length() <= ROW_LENGTH) {
            sb.append(" ").append(word);
            return sb.toString().length() <= ROW_LENGTH;
        }
        return false;
    }

    public static String formatRow(String direction, String sentence) {
        StringBuilder sb = new StringBuilder();
        int spaceCount = ROW_LENGTH - sentence.length();
        if (direction.equals(LEFT)) {
            sb.append("*");
            sb.append(sentence);
            sb.append(" ".repeat(spaceCount));
            sb.append("*");
        } else {
            sb.append("*");
            sb.append(" ".repeat(spaceCount));
            sb.append(sentence);
            sb.append("*");
        }
        return sb.toString();
    }

}


