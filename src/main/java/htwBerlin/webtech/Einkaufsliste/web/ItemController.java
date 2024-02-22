package htwBerlin.webtech.Einkaufsliste.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DBSS")
public class ItemController {


    private ItemService itemService;

    @Autowired
    public ItemController(ItemService theItemService){this.itemService = theItemService;}

    @GetMapping("/items")
    public List<Item> findAll(){
        List<Item> purple =  itemService.findAll();
        System.out.println(purple.get(0).getName());
        return purple;
    }


    @PostMapping("/items")
    public Item addItem(@RequestBody Item item){

      //  item.setId(0);     wird automatisch gesettet (Methode evtl trotzdem notwendig)

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

    @PutMapping("/items")
    public Item updateItem(@RequestBody Item item){

        return itemService.save(item);
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