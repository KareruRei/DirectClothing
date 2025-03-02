package com.directclothing.service.general;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

final public class Date {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private LocalDate date;


    public Date(LocalDate myDate) {this.date = myDate;}
    public String toString() {return date.format(formatter);}


    public static Date now() {return new Date(LocalDate.now());}

    public static Date create(int year, int month, int day) {
        return new Date(LocalDate.of(year, month, day));
    }

    public void setDate(LocalDate date) {this.date = date;}
    public LocalDate getDate() {return date;}
    public DateTimeFormatter getFormatter() {return formatter;}

    public int getIntValue() {      // returns an int which represents the amount of months total in the date
        return date.getMonthValue() + date.getYear() * 12;
    }
}