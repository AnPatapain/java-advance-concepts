package td4.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Famille implements Serializable {
    private ArrayList<Personne> famille;

    // Key est le numero sociale d'une personne, value est les numeros sociales des enfants
    private HashMap<String, List<String>> enfantDe;

    public Famille() {
        this.famille = new ArrayList<>();
        this.enfantDe = new HashMap<>();
    }

    public void addPersonneDansFamille(Personne ...personnes) {
        Collections.addAll(famille, personnes);
    }

    public void addEnfant(String parent, String ...enfants) {
        enfantDe.putIfAbsent(parent, new ArrayList<>());

        for(String enfant : enfants) {
            enfantDe.get(parent).add(enfant);
        }
    }

    public ArrayList<Personne> getFamille() {
        return famille;
    }

    public HashMap<String, List<String>> getEnfantDe() {
        return enfantDe;
    }
}
