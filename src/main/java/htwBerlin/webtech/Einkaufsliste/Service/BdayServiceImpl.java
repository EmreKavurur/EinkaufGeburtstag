package htwBerlin.webtech.Einkaufsliste.Service;

import htwBerlin.webtech.Einkaufsliste.DAO.Bday;
import htwBerlin.webtech.Einkaufsliste.Repository.BdayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BdayServiceImpl implements BdayService{

    private final BdayRepository bdayRepository;

    @Autowired
    public BdayServiceImpl(BdayRepository theBdayRepository){bdayRepository = theBdayRepository;}

    @Override
    public List<Bday> findAll() {return bdayRepository.findAll();}

    @Override
    public Bday findById(int id) {
        Optional<Bday> result = bdayRepository.findById(id);

        Bday theBday;

        if (result.isPresent()) {
            theBday = result.get();
        } else {
            throw new RuntimeException("Bday id: " + id + " not found");
        }
        return theBday;
    }

    @Override
    public Bday save(Bday theBday) {return bdayRepository.save(theBday);}

    @Override
    public void deleteById(int id) {}
}
