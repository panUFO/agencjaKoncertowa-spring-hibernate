import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.agencja.domain.Klub;
import com.agencja.domain.Zespol;
import com.agencja.service.KlubManager;
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
public class KlubManagerTest {

    @Autowired
    KlubManager klubManager;



    private final String MIASTO_1 = "Gdansk";
    private final String NAZWA_1 = "B90";
    private final int ILOSCM_1 = 2000;

    private final String MIASTO_2 = "Warszawa";
    private final String NAZWA_2 = "Progresja";
    private final int ILOSCM_21 = 1500;


    @Test
    public void addKlubTest() {
        Klub klub = new Klub();
        klub.setMiasto(MIASTO_1);
        klub.setNazwa(NAZWA_1);
        klub.setIlosc_miejsc(ILOSCM_1);
        klubManager.addKlub(klub);

        List<Klub> allKlubs = klubManager.getAllKlubs();
        Klub retrieviedKlubs = allKlubs.get(allKlubs.size() - 1);

        assertEquals(MIASTO_1, retrieviedKlubs.getMiasto());
        assertEquals(NAZWA_1, retrieviedKlubs.getNazwa());
        assertEquals(ILOSCM_1, retrieviedKlubs.getIlosc_miejsc());
    }




    @Test
    public void getKlubByIDTest() {
        Klub fklub = klubManager.getAllKlubs().get(0);
        Klub receivedKlub = klubManager.getKlubByID(fklub.getIdKlub());
        assertEquals(fklub.getIdKlub(), receivedKlub.getIdKlub());
    }


    @Test
    public void updateKlubTest() {
        Klub updateKlub = klubManager.getAllKlubs().get(0);

        updateKlub.setMiasto(MIASTO_1);
        updateKlub.setNazwa(NAZWA_1);
        updateKlub.setIlosc_miejsc(ILOSCM_1);

        klubManager.updateKlub(updateKlub);
        assertEquals(klubManager.getAllKlubs().get(0).getMiasto(), MIASTO_1);
        assertEquals(klubManager.getAllKlubs().get(0).getNazwa(), NAZWA_1);
        assertEquals(klubManager.getAllKlubs().get(0).getIlosc_miejsc(), ILOSCM_1);
    }


    @Test
    public void deleteKlubTest() {
        List<Klub> Klublist = klubManager.getAllKlubs();
        int n = Klublist.size();

        Klub retrievedKlub = klubManager.getAllKlubs().get(klubManager.getAllKlubs().size() -1);
        klubManager.deleteKlub(retrievedKlub);

        assertEquals(n - 1, klubManager.getAllKlubs().size());

        List<Klub>KlubafterDel = klubManager.getAllKlubs();
        assertTrue(KlubafterDel.contains(n));
        for (Klub k : KlubafterDel){
            assertTrue(Klublist.contains(k));
        }

    }

}
