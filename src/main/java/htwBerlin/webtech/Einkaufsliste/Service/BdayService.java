package htwBerlin.webtech.Einkaufsliste.Service;

import htwBerlin.webtech.Einkaufsliste.DAO.Bday;

import java.util.List;

public interface BdayService {
    List<Bday> findAll();

    Bday findById(int theId);

    Bday save(Bday theBday);

    void deleteById(int theId);
}

