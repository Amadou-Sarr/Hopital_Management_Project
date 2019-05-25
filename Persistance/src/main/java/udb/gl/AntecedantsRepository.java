package udb.gl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntecedantsRepository extends JpaRepository<Antecedants,Long> {

     Antecedants findByAntecedant(String antecedantName);

}
