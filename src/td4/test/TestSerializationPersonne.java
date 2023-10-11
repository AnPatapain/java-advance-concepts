package td4.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import td4.domain.Genre;
import td4.domain.Personne;
import td4.SerializeDeserializeManager;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestSerializationPersonne {
    /**
     * On souhaite construire les objets suivants (seuls les
     * prénoms sont donnés) :
     * Alice, Bob, Charlie, Dana, Enzo, Fatima
     * Les relations sont les suivantes :
     * Alice et Bob sont les parents de Charlie
     * Charlie et Fatima sont les parents de Dana et Enzo
     * Question 1—
     * Proposez un code permettant de sérialiser une
     * Personne, sans gérer les liens de parenté. Testez le
     * fonctionnement de ce code.
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
    public void test() {
        // Serialize
        SerializeDeserializeManager.serialize(alice, "alice.ser");
        SerializeDeserializeManager.serialize(bob, "bob.ser");
        SerializeDeserializeManager.serialize(charlie, "charlie.ser");
        SerializeDeserializeManager.serialize(dana, "dana.ser");
        SerializeDeserializeManager.serialize(enzo, "enzo.ser");
        SerializeDeserializeManager.serialize(fatima, "fatima.ser");

        // Deserialize
        alice = bob = charlie = dana = enzo = fatima = null;

        alice = (Personne) SerializeDeserializeManager.deserialize("alice.ser");
        bob = (Personne) SerializeDeserializeManager.deserialize("bob.ser");
        charlie = (Personne) SerializeDeserializeManager.deserialize("charlie.ser");
        dana = (Personne) SerializeDeserializeManager.deserialize("dana.ser");
        enzo = (Personne) SerializeDeserializeManager.deserialize("enzo.ser");
        fatima = (Personne) SerializeDeserializeManager.deserialize("fatima.ser");

        Assert.assertEquals(alice.getNomPrenom(), "Alice Alice");
        Assert.assertEquals(bob.getNomPrenom(), "Bob Bob");
        Assert.assertEquals(charlie.getNomPrenom(), "Charlie Charlie");
        Assert.assertEquals(dana.getNomPrenom(), "Dana Dana");
        Assert.assertEquals(enzo.getNomPrenom(), "Enzo Enzo");
        Assert.assertEquals(fatima.getNomPrenom(), "Fatima Fatima");

        Assert.assertEquals(alice.getNSS(), "1234567890");
    }

}
