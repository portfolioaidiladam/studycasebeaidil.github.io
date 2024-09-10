package studycaseaplikasiapi.springbeaidil.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDTOResponse {
    private String namaBarang;
    private int jumlahStok;
    private String nomorSeri;
    private String createdBy;
    private Long id;

}
