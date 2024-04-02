package htwBerlin.webtech.Einkaufsliste.Controller;

import htwBerlin.webtech.Einkaufsliste.DAO.Bday;
import htwBerlin.webtech.Einkaufsliste.Service.BdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bday")
public class BdayController {

    private BdayService bdayService;

    @Autowired
    public BdayController(BdayService theBdayService){this.bdayService = theBdayService;}

    @GetMapping("/bdays")
    public List<Bday> findAll(){ return bdayService.findAll(); }

    @PostMapping("/bday")
    public Bday addBday(@RequestBody Bday theBday){return bdayService.save(theBday);}

    @GetMapping("/bday/{bdayId}")
    public Bday getBday(@PathVariable int bdayId){

        Bday bday = bdayService.findById(bdayId);

        if(bday == null){
            throw new RuntimeException("Bday id: " + bdayId + " not found");
        }

        return bday;
    }

    @DeleteMapping("/bday/{bdayId}")
    public String deleteBday(@PathVariable int bdayId){

        Bday checkBdayID = bdayService.findById(bdayId);

        if(checkBdayID == null){
            throw new RuntimeException("Bday id: " + bdayId + " not found");
        }
        bdayService.deleteById(bdayId);
        return "Deleted bdayId: " + bdayId;
    }
}

