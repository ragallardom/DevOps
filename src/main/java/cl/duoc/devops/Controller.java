package cl.duoc.devops;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class Controller {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String hola() {
        String ahora = LocalDateTime.now().format(FMT);
        return "Hola Mundo\nLa hora en este momento es: " + ahora;
    }
}
