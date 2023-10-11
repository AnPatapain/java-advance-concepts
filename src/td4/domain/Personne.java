package td4.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Vector;

public class Personne implements Serializable {
    private String numeroSecu = "";
    private Calendar dateDeNaissance;
    private String nom;
    private String prenom;
    private Genre genre;

    private Personne parent1;
    private Personne parent2;

    private Vector<Personne> enfants;

    public Personne(){}

    public Personne(String _numeroSecu, Calendar _dateDeNaissance, String _nom, String _prenom, Genre _genre) {
        this(_numeroSecu, _dateDeNaissance, _nom, _prenom, _genre, null, null, null);
    }
    public Personne(String _numeroSecu, Calendar _dateDeNaissance,
                    String _nom, String _prenom, Genre _genre,
                    Personne _parent1, Personne _parent2) {
        this(_numeroSecu, _dateDeNaissance, _nom, _prenom, _genre, _parent1, _parent2, null);
    }
    public Personne(String _numeroSecu, Calendar _dateDeNaissance,
                    String _nom, String _prenom,
                    Genre _genre, Personne[] _enfants) {
        this(_numeroSecu, _dateDeNaissance, _nom, _prenom, _genre, null, null, _enfants);
    }
    public Personne(String _numeroSecu, Calendar _dateDeNaissance,
                    String _nom, String _prenom, Genre _genre,
                    Personne _parent1, Personne _parent2,
                    Personne[] _enfants) {
        this();
        this.numeroSecu = _numeroSecu;
        this.dateDeNaissance = _dateDeNaissance;
        this.nom = _nom;
        this.prenom = _prenom;
        this.genre = _genre;
        this.parent1 = _parent1;
        this.parent2 = _parent2;

        if(_enfants != null) {
            this.enfants = new Vector<Personne>();
            Collections.addAll(this.enfants, _enfants);
        }
    }

    public String getNomPrenom() {
        return nom + " " +  prenom;
    }

    public int getAge(Calendar dateDuJour) {
        return dateDuJour.get(Calendar.YEAR) - dateDeNaissance.get(Calendar.YEAR);
    }

    public String getNSS() {
        return this.numeroSecu;
    }

    public void addEnfant(Personne _e) {
        this.enfants.add(_e);
    }

    public Personne[] getEnfants() {
        Personne[] enfantsArray = new Personne[this.enfants.size()];
        this.enfants.copyInto(enfantsArray);
        return enfantsArray;
    }
}
