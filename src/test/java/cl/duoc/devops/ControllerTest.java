package cl.duoc.devops;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET / should greet the world when no name is provided")
    void getHoraWithoutNameReturnsGenericGreeting() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hola Mundo")))
                .andExpect(content().string(containsString("La hora en este momento es:")));
    }

    @Test
    @DisplayName("GET / should escape and include the provided name")
    void getHoraWithNameEscapesAndUsesProvidedValue() throws Exception {
        mockMvc.perform(get("/").param("nombre", "Pepe"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hola, Pepe")))
                .andExpect(content().string(containsString("La hora en este momento es:")));
    }

    @Test
    @DisplayName("GET /fecha should include today's date in the response")
    void getFechaReturnsFormattedDate() throws Exception {
        mockMvc.perform(get("/fecha"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("La fecha de hoy es:")));
    }

    @Test
    @DisplayName("GET /timestamp should include the unix epoch seconds")
    void getTimestampReturnsEpochSecondsMessage() throws Exception {
        mockMvc.perform(get("/timestamp"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Segundos que han pasado desde 2000-01-01:")));
    }

    @Test
    @DisplayName("GET /sum should return the sum of the provided numbers")
    void sumReturnsExpectedValue() throws Exception {
        mockMvc.perform(get("/sum").param("a", "4").param("b", "5"))
                .andExpect(status().isOk())
                .andExpect(content().string("La suma es: 9"));
    }
}
