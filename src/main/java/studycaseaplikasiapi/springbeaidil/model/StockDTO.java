package studycaseaplikasiapi.springbeaidil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDTO {
    private Long id;
    private String namaBarang;
    private int jumlahStok;
    private String nomorSeri;
    private String additionalInfo;  // stored as JSON

    @JsonIgnore
    private MultipartFile gambarBarang;

    private String createdBy;
    private String updatedBy;
}