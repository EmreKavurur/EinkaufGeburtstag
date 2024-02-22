package htwBerlin.webtech.Einkaufsliste.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository theItemRepository) {
        itemRepository = theItemRepository;
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item findById(int id) {

        Optional<Item> result = itemRepository.findById(id);

        Item item;

        if (result.isPresent()) {
            item = result.get();
        } else {
            throw new RuntimeException("Item id: " + id + " not found");
        }
        return item;
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void deleteById(int id) {
        itemRepository.deleteById(id);
    }
}
