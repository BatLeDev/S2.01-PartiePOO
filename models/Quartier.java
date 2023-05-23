package models;

public class Quartier {
    private static HashMap<int,Quartier>quartierList=new HashMap<int,Quartier>();

    private int idQuartier;
    private String nomQuartier;
    private double lgPisteCyclable;

    public Quartier(id, nom, lgPisteCyclable) {
        if (id < 0 || !quartierList.containsKey(id)) {
            throw new illegalArgumentException("models.Quartier.constructor : l'id est invalide (<0 ou deja existant)");
        }
        if (nom == null || nom.isEmpty()) {
            throw new illegalArgumentException("models.Quartier.constructor : Le parametre nom n'est pas valide");
        }
        if (lgPisteCyclable<0){
            throw new illegalArgumentException("models.Quartier.constructor : La longueur de la piste doit être positive");
        }
        this.idQuartier = id;
        this.nomQuartier = nom;
        this.lgPisteCyclable = lgPisteCyclable;
        Quartier.quartierList.put(id, this);
        
    }

    // setters

    // getters
    public static Quartier getQuartierById(int id) {
        return quartierList.get(id);
    }

    // dropper
    public static Quartier delQuartierById(int id) {
        return quartierList.remove(id);
    }

    // prints
    public String toString() {
        String ret = this.nomQuartier + "#" + id + " : longueurPiste = " + lgPisteCyclable;
        return ret;
    }

}