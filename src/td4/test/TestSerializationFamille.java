package td4.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import td4.domain.Famille;
import td4.domain.Genre;
import td4.domain.Personne;
import td4.SerializeDeserializeManager;

import java.util.*;

public class TestSerializationFamille {
    /**
     * Alice, Bob, Charlie, Dana, Enzo, Fatima
     * Alice et Bob sont les parents de Charlie
     * Charlie et Fatima sont les parents de Dana et Enzo
     */
    private static Personne alice, bob, charlie, dana, enzo, fatima;
    @BeforeClass
    public static void init() {
        alice = new Personne("1234567890", new GregorianCalendar(1980, Calendar.JANUARY, 1),
                "Alice", "Alice", Genre.Femme, null, null, null);
        bob = new Personne("1234567891", new GregorianCalendar(1980, Calendar.JANUARY, 1),
                "Bob", "Bob", Genre.Homme, null, null, null);
        charlie = new Personne("1234567892", new GregorianCalendar(1980, Calendar.JANUARY, 1),
                "Charlie", "Charlie", Genre.Homme, null, null, null);
        dana = new Personne("1234567893", new GregorianCalendar(1980, Calendar.JANUARY, 1),
                "Dana", "Dana", Genre.Femme, null, null, null);
        enzo = new Personne("1234567894", new GregorianCalendar(1980, Calendar.JANUARY, 1),
                "Enzo", "Enzo", Genre.Homme, null, null, null);
        fatima = new Personne("1234567895", new GregorianCalendar(1980, Calendar.JANUARY, 1),
                "Fatima", "Fatima", Genre.Femme, null, null, null);
    }

    @Test
    public void testSerializationFamille() {

        Famille famille = new Famille();
        famille.addPersonneDansFamille(alice, bob, charlie, dana, enzo, fatima);

        // Alice et Bob sont les parents de Charlie
        famille.addEnfant(alice.getNSS(), charlie.getNSS());
        famille.addEnfant(bob.getNSS(), charlie.getNSS());

        // Charlie et Fatima sont les parents de Enzo et Dana
        famille.addEnfant(charlie.getNSS(), dana.getNSS(), enzo.getNSS());

        famille.addEnfant(fatima.getNSS(), dana.getNSS(), enzo.getNSS());

        // Serialize
        SerializeDeserializeManager.serialize(famille, "famille.ser");

        // Deserialize
        famille = (Famille) SerializeDeserializeManager.deserialize("famille.ser");

        HashMap<String, List<String>> map = new HashMap<>();
        map.putIfAbsent("1234567890", new ArrayList<>());
        map.putIfAbsent("1234567891", new ArrayList<>());
        map.putIfAbsent("1234567892", new ArrayList<>());
        map.putIfAbsent("1234567895", new ArrayList<>());

        // Alice et Bob sont les parents de Charlie
        map.get("1234567890").add("1234567892");
        map.get("1234567891").add("1234567892");

        // Charlie et Fatima sont les parents de Enzo et Dana
        map.get("1234567892").add("1234567893");
        map.get("1234567892").add("1234567894");

        map.get("1234567895").add("1234567893");
        map.get("1234567895").add("1234567894");

        Assert.assertEquals(famille.getEnfantDe(), map);
    }
}
