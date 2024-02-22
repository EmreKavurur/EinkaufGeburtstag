package htwBerlin.webtech.Einkaufsliste.web;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {}
