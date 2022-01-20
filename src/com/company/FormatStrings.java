package com.company;

import java.util.ArrayList;
import java.util.List;

public class FormatStrings {

    // Объявляем константы
    private static final int ROW_LENGTH = 16;
    private static final String RIGHT = "RIGHT";
    private static final String LEFT = "LEFT";
    private static final String[] DIRECTION_ARRAY = new String[]{LEFT, RIGHT, LEFT};
    private static final String[][] STRING_ARRAY = new String[][]{
            {"Hello", "world"},
            {"Brad", "came", "to", "dinner", "with", "us"},
            {"He", "loves", "tacos"}
    };
    // Объявляем список куда будем записывать отформатированные строки
    private static final List<String> RESULT_LIST = new ArrayList<>();

    public static void main(String[] args) {
        // Заполняем первую строку * в количестве 16 символов + 2 по бокам, в java у String есть функция repeat, формирует строку повторяя
        // переданную в параметры строку N раз
        RESULT_LIST.add("*".repeat(ROW_LENGTH + 2));
        // Проходим по массиву массивов
        for (int i = 0; i < STRING_ARRAY.length; i++) {
            // Определяем сторону форматирования в зависимости от индекса элемента который содержит массив слов
            String direction = DIRECTION_ARRAY[i];
            // Создаем стринг билдер для соединения строк
            StringBuilder sb = new StringBuilder();
            // Проходим последовательно по внутренним массивам
            for (int j = 0; j < STRING_ARRAY[i].length; j++) {
                // в условии if вызываем функцию addWord, в параметры передаем созданный экзеемпляр стринг билдера, и слово
                // если функция addWord вернет false то зайдем в блок if
                if (!addWord(sb, STRING_ARRAY[i][j])) {
                    // если попали в этот блок, значит строка достаточно длинная и надо ее отформатировать и передать в результирующий список
                    // форматируем с помощью функции formatRow, в параметры передаем направление и строку (из стрингбилдера)
                    RESULT_LIST.add(formatRow(direction, sb.toString().trim()));
                    // создаем новый стрингбилдер для новой строки, используем ту же ссылку/переменную
                    sb = new StringBuilder();
                    // декремент j, что бы следующая итерация опять началась с элемента массива который был в текущей итерации
                    j--;
                }
            }
            // в случае если строка сразу влезла в 16 символов, добавляем здесь после прохождения по циклу в результирующий список
            RESULT_LIST.add(formatRow(direction, sb.toString().trim()));
        }
        // заполняем последнию строку *
        RESULT_LIST.add("*".repeat(ROW_LENGTH + 2));

        for (String row : RESULT_LIST) {
            System.out.println(row);
        }
    }

    public static boolean addWord(StringBuilder sb, String word) {
        // проверяем влазит ли строка, длину строки из стрингбилдера складываем с следующим словом
        if (sb.toString().length() + word.length() <= ROW_LENGTH) {
            // если усовие истинно добавляем к строке в стринг билдере пробел и следующее слово
            sb.append(" ").append(word);
            return true;
        }
        // если условие не выполняется возвращаем false
        return false;
    }

    public static String formatRow(String direction, String sentence) {
        StringBuilder sb = new StringBuilder();
        // вычесляем сколько нам необходимо пробелов для заполнения строки
        int spaceCount = ROW_LENGTH - sentence.length();
        // если форматирование слева
        if (direction.equals(LEFT)) {
            // добовляем *
            sb.append("*");
            // потом предложение
            sb.append(sentence);
            // потом заполняем пробелами
            sb.append(" ".repeat(spaceCount));
            // добовляем *
            sb.append("*");
        } else {
            // добовляем *
            sb.append("*");
            // потом заполняем пробелами
            sb.append(" ".repeat(spaceCount));
            // потом предложение
            sb.append(sentence);
            // добовляем *
            sb.append("*");
        }
        //возвращаем отформатированную строку
        return sb.toString();
    }

}


