package studycaseaplikasiapi.springbeaidil.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nama_barang", nullable = false)
    private String namaBarang;
    @Column(name = "jumlah_stok", nullable = false)
    private int jumlahStok;
    @Column(name = "nomor_seri", nullable = false)
    private String nomorSeri;

    @Lob
    @Column(name = "gambar_barang")
    private byte[] gambarBarang;  // atau kita dapat menyimpan jalur gambar sebagai String

    @Column(name = "additional_info", columnDefinition = "jsonb")
    private String additionalInfo;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;
}
