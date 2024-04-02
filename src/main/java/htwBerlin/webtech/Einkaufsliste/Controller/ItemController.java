package htwBerlin.webtech.Einkaufsliste.Controller;

import htwBerlin.webtech.Einkaufsliste.DAO.Bday;
import htwBerlin.webtech.Einkaufsliste.DAO.Item;
import htwBerlin.webtech.Einkaufsliste.Service.BdayService;
import htwBerlin.webtech.Einkaufsliste.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private BdayService bdayService;
    private ItemService itemService;

    @Autowired
    public ItemController(BdayService bdayService, ItemService itemService) {
        this.bdayService = bdayService;
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public List<Item> findAll(){ return itemService.findAll(); }


    @PostMapping("/items")
    public Item addItem(@RequestBody Item item, @RequestParam int bdayId) {
        Bday bday = bdayService.findById(bdayId);
        if (bday == null) {
            throw new RuntimeException("Bday id: " + bdayId + " not found");
        }
        item.setBday(bday);
        return itemService.save(item);
    }

    @GetMapping("/item/{itemId}")
    public Item getItem(@PathVariable int itemId){

        Item item = itemService.findById(itemId);

        if(item == null){
            throw new RuntimeException("Item id: " + itemId + " not found");
        }

        return item;
    }

    @PutMapping("/items/{itemId}")
    public Item updateItem(@PathVariable int itemId, @RequestBody Item itemDetails) {
        Item item = itemService.findById(itemId);
        if (item == null) {
            throw new RuntimeException("Item id not found - " + itemId);
        }

        // Update item properties here
        item.setName(itemDetails.getName());
        // Add other fields to update as necessary

        return itemService.save(item);
    }

    @GetMapping("/itemsByBday/{bdayId}")
    public List<Item> findItemsByBdayId(@PathVariable int bdayId) {
        return itemService.findByBdayId(bdayId);
    }

    @DeleteMapping("/items/{itemId}")
    public String deleteItem(@PathVariable int itemId){

        Item checkItemID = itemService.findById(itemId);

        if(checkItemID == null){
            throw new RuntimeException("Item id: " + itemId + " not found");
        }
        itemService.deleteById(itemId);
        return "Deleted itemId: " + itemId;
    }
}