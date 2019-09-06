package udb.gl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RendezVousRepository  extends JpaRepository<RendezVous, Long> {

    @Override
    Optional<RendezVous> findById(Long aLong);

    RendezVous findByDate(Date date);

    List<RendezVous> findAllByDate(Date date);

    List<RendezVous> findAllByDateAndAndUtilisateur(Date date, Utilisateur utilisateur);

    List<RendezVous> findAllByDateBetween(Date firstDate, Date lastDate);

    int countAllByDate(Date date);
}
