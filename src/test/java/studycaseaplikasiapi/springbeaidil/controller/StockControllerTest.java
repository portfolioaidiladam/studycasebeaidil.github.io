package studycaseaplikasiapi.springbeaidil.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import studycaseaplikasiapi.springbeaidil.model.StockDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@Slf4j
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

/*
    @Test
    public void testCreateStock() throws Exception {
        String stockJson = "{ \"namaBarang\": \"Laptop\", \"jumlahStok\": 10, \"nomorSeri\": \"1234567890\" }";
        mockMvc.perform(post("/api/stocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stockJson))
                .andExpect(status().isOk());
    }
*/

   /* @Test
    public void testCreateStock() throws Exception {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setNamaBarang("Test Item");
        stockDTO.setJumlahStok(10);
        stockDTO.setNomorSeri("SN1234");

        mockMvc.perform(
                         post("/api/stocks/create")
                         //multipart("/upload/profile")
                        //.file(new MockMultipartFile("gambar", "gambar.png", "gambar/png",
                         //       getClass().getResourceAsStream("/images/gambar.png")))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stockDTO))
                        //.andExpect(status().isCreated())
        );
    }*/

    @Test
    public void testCreateStock() throws Exception {
        MockMultipartFile image = new MockMultipartFile("gambarBarang", "gambar.png", "images/png", "dummy".getBytes());

        StockDTO stockDTO = new StockDTO(null, "Barang A", 100, "12345", null, image, "admin", null);

        mockMvc.perform(multipart("/api/stock/create")
                        .file("gambarBarang", image.getBytes())
                        .param("namaBarang", "Barang A")
                        .param("jumlahStok", "100")
                        .param("nomorSeri", "12345")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stockDTO))
                        .param("createdBy", "admin"))
                .andExpect(status().isCreated())
                .andDo(result -> log.info("Response: {}", result.getResponse().getContentAsString()));
    }
}
