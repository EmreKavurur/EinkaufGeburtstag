package htwBerlin.webtech.Einkaufsliste.Service;

import htwBerlin.webtech.Einkaufsliste.DAO.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAll();

    Item findById(int theId);

    List<Item> findByBdayId(int bdayId);

    Item save(Item theItem);

    void deleteById(int theId);
}
