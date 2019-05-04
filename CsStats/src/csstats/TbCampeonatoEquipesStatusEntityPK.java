package csstats;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TbCampeonatoEquipesStatusEntityPK implements Serializable {
    private int idCampeonato;
    private int idEquipe;

    @Column(name = "id_campeonato", nullable = false)
    @Id
    public int getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(int idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    @Column(name = "id_equipe", nullable = false)
    @Id
    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbCampeonatoEquipesStatusEntityPK that = (TbCampeonatoEquipesStatusEntityPK) o;
        return idCampeonato == that.idCampeonato &&
                idEquipe == that.idEquipe;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCampeonato, idEquipe);
    }
}
