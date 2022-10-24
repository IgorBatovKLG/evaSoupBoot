package ru.batov.evasoupboot.services;

public class DateService {

    public String toCorrectStringDate(String date) {
        String dateCorrect = "Дата ведена некорректно";
        if (date.length()==10) {
            char[] chars = date.toCharArray();
            char[] news = new char[10];
            char dash = '-';
            news[0] = chars[6]; // 2
            news[1] = chars[7]; // 0
            news[2] = chars[8]; // 2
            news[3] = chars[9]; // 2
            news[4] = dash; //.
            news[5] = chars[3]; //1
            news[6] = chars[4]; //0
            news[7] = dash; //.
            news[8] = chars[0]; //2
            news[9] = chars[1]; //3
            dateCorrect = new String(news);
        }
        return dateCorrect;
    }
}
