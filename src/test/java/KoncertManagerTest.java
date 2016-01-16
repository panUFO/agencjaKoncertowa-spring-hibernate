import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.agencja.domain.Klub;
import com.agencja.domain.Koncert;
import com.agencja.service.KlubManager;
import com.agencja.service.KoncertManager;

import com.agencja.service.ZespolManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class KoncertManagerTest {

    @Autowired
    ZespolManager zespolManager;

    @Autowired
    KlubManager klubManager;

    @Test
    public void addKoncertTest() {

    }

    @Test
    public void getKoncertByIDTest() {

    }


    @Test
    public void updateKoncertTest() {

    }

    @Test
    public void deleteKoncertTest() {

    }



}
