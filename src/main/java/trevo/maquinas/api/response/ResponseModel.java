package trevo.maquinas.api.response;

import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@NoArgsConstructor
public class ResponseModel {
    private String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    private String message;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

