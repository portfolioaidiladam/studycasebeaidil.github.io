package studycaseaplikasiapi.springbeaidil.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import studycaseaplikasiapi.springbeaidil.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

}