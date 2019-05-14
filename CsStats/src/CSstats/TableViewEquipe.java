package CSstats;

import DAO.DaoConecta;
import MODEL.IEntity;
import MODEL.TbJogadorEquipeEntity;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static DAO.DaoConecta.abreConexao;
import static DAO.DaoConecta.fecharConexao;

public class TableViewEquipe {
    private String nome;
    private String origem;
    private String integrantes;


    public TableViewEquipe(String nome, String origem, String integrantes) {
        this.nome = nome;
        this.origem = origem;
        this.integrantes = integrantes;
    }

    public static String getIntegrantesbyId(int id) {
        abreConexao();
        CriteriaBuilder builder = DaoConecta.em.getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery( TbJogadorEquipeEntity.class);
        Root<IEntity> root = cq.from(TbJogadorEquipeEntity.class);
        cq.select(cq.from(TbJogadorEquipeEntity.class)).where(builder.equal( root.get("idEquipe"), id));
        Query q = DaoConecta.em.createQuery(cq);
        List<TbJogadorEquipeEntity> result = q.getResultList();
        List<String> list_of_strings = new ArrayList<>(  );
        for (TbJogadorEquipeEntity j: result
        ) {
            list_of_strings.add(j.getCondenome());
        }
        fecharConexao();
        String integrantes = String.join(", ", list_of_strings);


        return integrantes;
    }

    public void setIntegrantes(String j){
        this.integrantes = j;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

}
