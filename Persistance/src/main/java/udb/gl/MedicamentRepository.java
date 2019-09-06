package udb.gl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicaments, Long> {
    Medicaments findByLibelle(String libelle);
    Medicaments findById(long id);
}
