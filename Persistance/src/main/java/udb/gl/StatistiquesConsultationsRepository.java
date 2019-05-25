package udb.gl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface StatistiquesConsultationsRepository extends JpaRepository<StatistiquesConsultationsJournalieres, Long> {

    @Override
    Optional<StatistiquesConsultationsJournalieres> findById(Long aLong);

    Optional<StatistiquesConsultationsRepository> findByCreatedAt(Date date);

}
