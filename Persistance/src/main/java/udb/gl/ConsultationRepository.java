package udb.gl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    @Override
    Optional<Consultation> findById(Long aLong);

    int countAllByDate(Date date);

    Optional<List<Consultation>> findAllByDate(Date date);
}
