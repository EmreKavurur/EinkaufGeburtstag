package htwBerlin.webtech.Einkaufsliste.Repository;

import htwBerlin.webtech.Einkaufsliste.DAO.Bday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BdayRepository extends JpaRepository<Bday,Integer> {
}
