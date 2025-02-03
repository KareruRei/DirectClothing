package general;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

final public class Date {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private LocalDate theDate;


    public Date(LocalDate myDate) {this.theDate = myDate;}
    public String toString() {return theDate.format(formatter);}


    public static Date now() {return new Date(LocalDate.now());}

    public static Date create(int year, int month, int day) {
        return new Date(LocalDate.of(year, month, day));
    }

    public void setDate(LocalDate date) {this.theDate = date;}
    public LocalDate getDate() {return theDate;}
    public DateTimeFormatter getFormatter() {return formatter;}
}