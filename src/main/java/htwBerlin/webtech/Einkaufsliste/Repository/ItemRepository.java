package htwBerlin.webtech.Einkaufsliste.Repository;

import htwBerlin.webtech.Einkaufsliste.DAO.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    List<Item> findByBdayId(int bdayId);
}
