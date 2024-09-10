package studycaseaplikasiapi.springbeaidil.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import studycaseaplikasiapi.springbeaidil.entity.Stock;
import studycaseaplikasiapi.springbeaidil.model.StockDTO;
import studycaseaplikasiapi.springbeaidil.model.StockDTOResponse;
import studycaseaplikasiapi.springbeaidil.model.UpdateStockDTO;
import studycaseaplikasiapi.springbeaidil.service.StockService;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@Slf4j
public class StockController {

    @Autowired
    private StockService stockService;



    /*public ResponseEntity<Stock> createStock(@ModelAttribute StockDTO stockDTO) throws IOException {
        Stock createdStock = null;
        try {
            createdStock = stockService.createStock(stockDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("Created stock: {}", createdStock);
        return new ResponseEntity<>(createdStock, HttpStatus.CREATED);
    }
*/
    @PostMapping("/create")
    public ResponseEntity<StockDTOResponse> createStock(@ModelAttribute StockDTO stockDTO) throws Exception {
        log.info("Creating new stock: {}", stockDTO);
        StockDTOResponse createdStock = stockService.createStock(stockDTO);
        return ResponseEntity.ok(createdStock);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("gambar") MultipartFile gambar) {
        if (gambar == null || gambar.isEmpty()) {
            return ResponseEntity.badRequest().body("File is missing");
        }

        String contentType = gambar.getContentType();
        if (contentType == null) {
            return ResponseEntity.badRequest().body("Content type is missing");
        }
        return null;
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



   /* @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id, @RequestBody StockDTO stockDTO) {
        Stock stock = Stock.builder()
                .namaBarang(stockDTO.getNamaBarang())
                .jumlahStok(stockDTO.getJumlahStok())
                .nomorSeri(stockDTO.getNomorSeri())
                .additionalInfo(stockDTO.getAdditionalInfo())
                .updatedBy(stockDTO.getUpdatedBy())
                .build();
        return ResponseEntity.ok(stockService.updateStock(id, stock));
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<StockDTOResponse> updateStock(@PathVariable Long id, @ModelAttribute UpdateStockDTO updateStockDTO) {
        log.info("Updating stock ID: {}", id);
        StockDTOResponse updatedStock = stockService.updateStock(id, updateStockDTO);
        return ResponseEntity.ok(updatedStock);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
