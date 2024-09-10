package studycaseaplikasiapi.springbeaidil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDTO {
    @Size(max = 200)
    private Long id;
    @Size(max = 200)
    private String namaBarang;
    @Size(max = 200)
    private int jumlahStok;
    @Size(max = 200)
    private String nomorSeri;
    @Size(max = 200)
    private String additionalInfo;  // stored as JSON

    @JsonIgnore
    private MultipartFile gambarBarang;

    @Size(max = 200)
    private String createdBy;
    @Size(max = 200)
    private String updatedBy;
}