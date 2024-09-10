package studycaseaplikasiapi.springbeaidil.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStockDTO {
    private String namaBarang;
    private int jumlahStok;
    private String nomorSeri;
    private String updatedBy;
    private Long id;
    private String additionalInfo;
}
