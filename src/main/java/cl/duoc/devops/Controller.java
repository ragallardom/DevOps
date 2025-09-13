package cl.duoc.devops;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class Controller {

    private static final DateTimeFormatter HORA_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter FECHA_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/")
    public String getHora(@RequestParam(value = "nombre", required = false) String nombre) {
        LocalDateTime now = LocalDateTime.now();
        String saludo = "Hola Mundo";
        if (nombre != null && !nombre.isBlank()) {
            String seguro = HtmlUtils.htmlEscape(nombre.trim());
            saludo = "Hola, " + seguro;
        }
        return saludo + "\nLa hora en este momento es: " + now.format(HORA_FORMATTER);
    }

    @GetMapping("/fecha")
    public String getFecha() {
        LocalDate today = LocalDate.now();
        return "La fecha de hoy es: " + today.format(FECHA_FORMATTER);
    }

    @GetMapping("/timestamp")
    public String getTimestamp() {
        long epochSeconds = Instant.now().getEpochSecond();
        return "Segundos que han pasado desde 2000-01-01: " + epochSeconds;
    }

    @GetMapping("/sum")
    public String sum(
            @RequestParam("a") int a,
            @RequestParam("b") int b) {
        int resultado = a + b;
        return "La suma es: " + resultado;
    }

}
