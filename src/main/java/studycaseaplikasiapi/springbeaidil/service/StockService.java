package studycaseaplikasiapi.springbeaidil.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studycaseaplikasiapi.springbeaidil.entity.Stock;
import studycaseaplikasiapi.springbeaidil.model.StockDTO;
import studycaseaplikasiapi.springbeaidil.repository.StockRepository;
import java.io.IOException;

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


    /*public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }*/

    public Stock createStock(StockDTO stockDTO) throws Exception {
        if (!stockDTO.getGambarBarang().getContentType().equals("image/jpeg") &&
                !stockDTO.getGambarBarang().getContentType().equals("image/png")) {
            throw new IllegalArgumentException("Only JPG and PNG images are allowed");
        }
        Stock stock = new Stock();
        stock.setNamaBarang(stockDTO.getNamaBarang());
        stock.setJumlahStok(stockDTO.getJumlahStok());
        stock.setNomorSeri(stockDTO.getNomorSeri());
        stock.setAdditionalInfo(new ObjectMapper().writeValueAsString(stockDTO.getAdditionalInfo()));
        stock.setGambarBarang(stockDTO.getGambarBarang().getBytes());
        stock.setCreatedBy(stockDTO.getCreatedBy());
        stock.setCreatedAt(LocalDateTime.now());

        return stockRepository.save(stock);
    }

    public List<Stock> listStock() {
        return stockRepository.findAll();
    }

    public Optional<Stock> getStockDetail(Long id) {
        return stockRepository.findById(id);
    }

    public Stock updateStock(Long id, Stock stockDetails) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
        stock.setNamaBarang(stockDetails.getNamaBarang());
        stock.setJumlahStok(stockDetails.getJumlahStok());
        stock.setNomorSeri(stockDetails.getNomorSeri());
        stock.setUpdatedBy(stockDetails.getUpdatedBy());
        return stockRepository.save(stock);
    }

    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }
}
