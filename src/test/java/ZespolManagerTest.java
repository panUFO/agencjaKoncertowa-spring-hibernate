import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.agencja.domain.Zespol;
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
public class ZespolManagerTest {

    @Autowired
    ZespolManager zespolManager;

    Zespol zespol = new Zespol();
    Zespol firstToDelete, secondToDelete;

    private final String NAZWA_1 = "Kylesa";
    private final String KRAJ_1 = "USA";

    private final String NAZWA_2 = "Ufomammut";
    private final String KRAJ_2 = "ITA";


    @Before
    public void addTestZespols()
    {
        firstToDelete = zespolManager.addZespol(new Zespol("Kyuss", "USA"));
        secondToDelete = zespolManager.addZespol(new Zespol("Ufomammut", "ITA"));

    }
    @After
    public void deleteTestZespols()
    {
        zespolManager.deleteZespol(firstToDelete);
        zespolManager.deleteZespol(secondToDelete);
    }



    @Test
    public void addZespolTest(){
        Zespol zespol = new Zespol();
        zespol.setNazwa(NAZWA_1);
        zespol.setKraj(KRAJ_1);
        zespolManager.addZespol(zespol);

        List<Zespol> allZespols = zespolManager.getAllZespols();
        Zespol retrieviedZespols = allZespols.get(allZespols.size()-1);

        assertEquals(NAZWA_1, retrieviedZespols.getNazwa());
        assertEquals(KRAJ_1, retrieviedZespols.getKraj());

    }


    @Test
    public void getZespolByIDTest() {
        Zespol fzespol = zespolManager.getAllZespols().get(0);
        Zespol receivedZespol = zespolManager.getZespolByID(fzespol.getIdZespol());
        assertEquals(fzespol.getIdZespol(), receivedZespol.getIdZespol());
    }


    @Test
    public void updateZespolTest() {
        Zespol updateZespol = zespolManager.getAllZespols().get(0);

        updateZespol.setNazwa(NAZWA_1);
        updateZespol.setKraj(KRAJ_1);

        zespolManager.updateZespol(updateZespol);
        assertEquals(zespolManager.getAllZespols().get(0).getNazwa(), NAZWA_1);
        assertEquals(zespolManager.getAllZespols().get(0).getKraj(), KRAJ_1);
    }


    @Test
    public void deleteZespolTest() {
        List<Zespol> Zespollist = zespolManager.getAllZespols();
        int n = Zespollist.size();

        Zespol retrievedZespol = zespolManager.getAllZespols().get(zespolManager.getAllZespols().size() -1);
        zespolManager.deleteZespol(retrievedZespol);

        assertEquals(n - 1, zespolManager.getAllZespols().size());

        List<Zespol>ZespolafterDel = zespolManager.getAllZespols();
        assertTrue(ZespolafterDel.contains(n));
        for (Zespol z : ZespolafterDel){
            assertTrue(Zespollist.contains(z));
        }

    }



}
