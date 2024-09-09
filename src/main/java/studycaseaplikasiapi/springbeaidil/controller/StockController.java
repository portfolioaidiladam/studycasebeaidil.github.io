package studycaseaplikasiapi.springbeaidil.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studycaseaplikasiapi.springbeaidil.entity.Stock;
import studycaseaplikasiapi.springbeaidil.model.StockDTO;
import studycaseaplikasiapi.springbeaidil.service.StockService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@Slf4j
public class StockController {

    @Autowired
    private StockService stockService;

    /*@PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody StockDTO stockDTO,
                                            //  @RequestParam ("gambar") MultipartFile gambar
                                             @RequestPart(name = "gambar") MultipartFile gambar
                                            ) {
        // validate MIME Type (JPG, PNG)
        if (!gambar.getContentType().equals("images/jpeg") && !gambar.getContentType().equals("images/png")) {
            return ResponseEntity.badRequest().body(null);
        }

        Stock stock = Stock.builder()
                .namaBarang(stockDTO.getNamaBarang())
                .jumlahStok(stockDTO.getJumlahStok())
                .nomorSeri(stockDTO.getNomorSeri())
                //.gambarBarang(gambar.getBytes())
                .additionalInfo(stockDTO.getAdditionalInfo())
                .createdBy(stockDTO.getCreatedBy())
                .build();
        return ResponseEntity.ok(stockService.createStock(stock));
    }*/

    @PostMapping("/create")
    public ResponseEntity<Stock> createStock(@ModelAttribute StockDTO stockDTO) throws IOException {
        Stock createdStock = null;
        try {
            createdStock = stockService.createStock(stockDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("Created stock: {}", createdStock);
        return new ResponseEntity<>(createdStock, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Stock> listStock() {
        return stockService.listStock();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockDetail(@PathVariable Long id) {
        return stockService.getStockDetail(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id, @RequestBody StockDTO stockDTO) {
        Stock stock = Stock.builder()
                .namaBarang(stockDTO.getNamaBarang())
                .jumlahStok(stockDTO.getJumlahStok())
                .nomorSeri(stockDTO.getNomorSeri())
                .additionalInfo(stockDTO.getAdditionalInfo())
                .updatedBy(stockDTO.getUpdatedBy())
                .build();
        return ResponseEntity.ok(stockService.updateStock(id, stock));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
