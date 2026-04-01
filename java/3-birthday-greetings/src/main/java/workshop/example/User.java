package workshop.example;

import java.time.LocalDate;

public record User(String vorname,
                   String name,
                   LocalDate birthday,
                   String email) {

}
