package htwBerlin.webtech.Einkaufsliste.web;

import java.util.List;

public interface ItemService {
    List<Item> findAll();

    Item findById(int id);

    Item save(Item item);

    void deleteById(int id);
}
