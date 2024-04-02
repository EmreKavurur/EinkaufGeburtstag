package htwBerlin.webtech.Einkaufsliste;

import htwBerlin.webtech.Einkaufsliste.DAO.Bday;
import htwBerlin.webtech.Einkaufsliste.DAO.Item;
import htwBerlin.webtech.Einkaufsliste.Repository.BdayRepository;
import htwBerlin.webtech.Einkaufsliste.Repository.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ItemControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BdayRepository bdayRepository;

    private Bday testBday;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setup() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/item");
        // Create a Bday entity only if there isn't one already
        if (bdayRepository.count() == 0) {
            Bday bday = new Bday();
            bday.setName("Test Bday");
            testBday = bdayRepository.save(bday);
        } else {
            testBday = bdayRepository.findAll().get(0);
        }
    }

    @AfterEach
    void clearDatabase(@Autowired JdbcTemplate jdbcTemplate) {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "item");
        // Optionally keep the bday table intact between tests
        // JdbcTestUtils.deleteFromTables(jdbcTemplate, "bday");
    }


    @Test
    public void testDeleteItem() {
        // Create and save an item directly to be deleted
        Item item = new Item();
        item.setName("Item to Delete");
        item.setBday(testBday);
        item = itemRepository.save(item);

        int sizeBeforeDelete = itemRepository.findAll().size();

        restTemplate.delete(baseUrl + "/items/{id}", item.getId());

        int sizeAfterDelete = itemRepository.findAll().size();

        assertEquals(1, sizeBeforeDelete);
        assertEquals(0, sizeAfterDelete);
    }
}
