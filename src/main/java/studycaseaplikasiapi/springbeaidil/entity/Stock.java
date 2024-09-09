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

    private String namaBarang;
    private int jumlahStok;
    private String nomorSeri;

    @Lob
    private byte[] gambarBarang;  // atau kita dapat menyimpan jalur gambar sebagai String

   // @Column(columnDefinition = "jsonb")
    private String additionalInfo;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
