package studycaseaplikasiapi.springbeaidil.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import studycaseaplikasiapi.springbeaidil.model.StockDTO;
import studycaseaplikasiapi.springbeaidil.model.StockDTOResponse;
import studycaseaplikasiapi.springbeaidil.model.UpdateStockDTO;
import studycaseaplikasiapi.springbeaidil.service.StockService;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@Slf4j

public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private StockService stockService;

    @Autowired
    private ObjectMapper objectMapper;

    private StockDTO stockDTO;
    private StockDTOResponse stockDTOResponse;
    private UpdateStockDTO updateStockDTO;

    @BeforeEach
    public void setUp() {
        stockDTO = StockDTO.builder()
                .namaBarang("Test Item")
                .jumlahStok(100)
                .nomorSeri("12345")
                .createdBy("admin")
                .build();

        stockDTOResponse = StockDTOResponse.builder()
                .namaBarang("Test Item")
                .jumlahStok(100)
                .nomorSeri("12345")
                .createdBy("admin")
                .id(1L)
                .build();
    }


    @Test
    public void testCreateStock() throws Exception {
        when(stockService.createStock(any(StockDTO.class))).thenReturn(stockDTOResponse);

        MockMultipartFile file = new MockMultipartFile("gambarBarang", "test.jpg", "image/jpeg", "some-image".getBytes());

        mockMvc.perform(multipart("/api/stock/create")
                        .file("gambarBarang", file.getBytes())
                        .param("namaBarang", stockDTO.getNamaBarang())
                        .param("jumlahStok", String.valueOf(stockDTO.getJumlahStok()))
                        .param("nomorSeri", stockDTO.getNomorSeri())
                        .param("createdBy", stockDTO.getCreatedBy()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.namaBarang").value(stockDTO.getNamaBarang()))
                .andExpect(jsonPath("$.jumlahStok").value(stockDTO.getJumlahStok()))
                .andExpect(jsonPath("$.nomorSeri").value(stockDTO.getNomorSeri()));
    }


    @Test
    public void testGetStockDetail() throws Exception {
        mockMvc.perform(get("/api/stock/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }
    @Test
    public void testUpdateStock() throws Exception {
        UpdateStockDTO updateStockDTO = UpdateStockDTO.builder()
                .namaBarang("Test Item")
                .jumlahStok(200)
                .nomorSeri("54321")
                .updatedBy("admin")
                .build();

        when(stockService.updateStock(Mockito.anyLong(), any(UpdateStockDTO.class)))
                .thenReturn(stockDTOResponse);

        mockMvc.perform(put("/api/stock/1")
                        .param("namaBarang", updateStockDTO.getNamaBarang())
                        .param("jumlahStok", String.valueOf(updateStockDTO.getJumlahStok()))
                        .param("nomorSeri", updateStockDTO.getNomorSeri())
                        .param("updatedBy", updateStockDTO.getUpdatedBy()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.namaBarang").value(stockDTOResponse.getNamaBarang()));
    }

    @Test
    public void testDeleteStock() throws Exception {
        mockMvc.perform(delete("/api/stock/1"))
                .andExpect(status().isNoContent());
    }

}
