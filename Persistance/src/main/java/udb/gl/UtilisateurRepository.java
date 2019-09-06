package udb.gl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findById(Long id);
    //Optional<Utilisateur> updatePasswordWhereId(String password,long id);
    Optional<Utilisateur>  findByEmail(String email);
    Optional<Utilisateur> findByUsernameOrEmail(String username , String email);
    Optional<Utilisateur> findByUsername(String username);
    Utilisateur findByRoleAndService(RoleName role, ServiceName service);

    List<Utilisateur> findByIdIn(List<Long> userIds);
    List<Utilisateur> findAllByRole(Role role);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);




}
