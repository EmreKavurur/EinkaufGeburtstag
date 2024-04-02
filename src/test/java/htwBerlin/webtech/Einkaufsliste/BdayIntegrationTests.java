package htwBerlin.webtech.Einkaufsliste;

import htwBerlin.webtech.Einkaufsliste.DAO.Bday;
import htwBerlin.webtech.Einkaufsliste.Repository.BdayRepository;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BdayIntegrationTests {

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	private static RestTemplate restTemplate;

	@Autowired
	private BdayRepository bdayRepository;

	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}

	@AfterEach
	void clearDatabase(@Autowired JdbcTemplate jdbcTemplate) {
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "item"); // Clear items first due to FK constraints
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "bday");
	}

	@BeforeEach
	public void setup() {
		baseUrl = baseUrl.concat(":").concat(port + "").concat("/bday");
	}

	@Test
	public void testAddBday() {
		Bday newBday = new Bday();
		newBday.setName("John's Birthday");
		Bday response = restTemplate.postForObject(baseUrl + "/bday", newBday, Bday.class);
		assertEquals("John's Birthday", response.getName());
	}

}

