package trevo.maquinas.api.response;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ResponseModelToken {
    private String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    private Object token;

    public ResponseModelToken(Object token) {
        this.setToken(token);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }
}
