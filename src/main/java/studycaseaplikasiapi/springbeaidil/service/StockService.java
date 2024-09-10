package studycaseaplikasiapi.springbeaidil.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import studycaseaplikasiapi.springbeaidil.entity.Stock;
import studycaseaplikasiapi.springbeaidil.model.StockDTO;
import studycaseaplikasiapi.springbeaidil.model.StockDTOResponse;
import studycaseaplikasiapi.springbeaidil.model.UpdateStockDTO;
import studycaseaplikasiapi.springbeaidil.repository.StockRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Transactional
    public StockDTOResponse createStock(StockDTO stockDTO) throws Exception {

        Stock stock = new Stock();
        stock.setNamaBarang(stockDTO.getNamaBarang());
        stock.setJumlahStok(stockDTO.getJumlahStok());
        stock.setNomorSeri(stockDTO.getNomorSeri());
        stock.setAdditionalInfo(new ObjectMapper().writeValueAsString(stockDTO.getAdditionalInfo()));
        stock.setGambarBarang(stockDTO.getGambarBarang().getBytes());
        stock.setCreatedBy(stockDTO.getCreatedBy());
        stock.setCreatedAt(LocalDateTime.now());

        stockRepository.save(stock);

        return toStockResponse(stock);

    }

    private StockDTOResponse toStockResponse(Stock stock) {
        return StockDTOResponse.builder()
                .namaBarang(stock.getNamaBarang())
                .jumlahStok(stock.getJumlahStok())
                .nomorSeri(stock.getNomorSeri())
                .createdBy(stock.getCreatedBy())
                .id(stock.getId())
                .build();
    }

    private void validateImage(MultipartFile image) {
        if (image != null) {
            String contentType = image.getContentType();
            if (!"images/jpeg".equals(contentType) && !"images/png".equals(contentType)) {
                throw new IllegalArgumentException("Only JPG and PNG images are allowed");
            }
        }
    }

    public List<Stock> listStock() {
        return stockRepository.findAll();
    }

    public Optional<Stock> getStockDetail(Long id) {
        return stockRepository.findById(id);
    }
    @Transactional
    public StockDTOResponse updateStock(Long id, UpdateStockDTO stockDetails) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
        stock.setNamaBarang(stockDetails.getNamaBarang());
        stock.setJumlahStok(stockDetails.getJumlahStok());
        stock.setNomorSeri(stockDetails.getNomorSeri());
        stock.setAdditionalInfo(stockDetails.getAdditionalInfo());
        stock.setUpdatedBy(stockDetails.getUpdatedBy());
        stockRepository.save(stock);
        return toStockResponse(stock);

    }

    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }
}
