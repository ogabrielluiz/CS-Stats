package CSstats;

import DAO.DaoConecta;
import MODEL.IEntity;
import MODEL.TbJogadorEquipeEntity;

import javax.persistence.Query;
import javax.persistence.Tuple;
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


    public TableViewEquipe(String nome, String origem, String lista_de_integrantes) {
        this.nome = nome;
        this.origem = origem;
        this.integrantes = lista_de_integrantes;
    }

    public static String getIntegrantesbyId(int id) {
        abreConexao();
        CriteriaBuilder builder = DaoConecta.em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = builder.createTupleQuery();
        Root<TbJogadorEquipeEntity> root = cq.from(TbJogadorEquipeEntity.class);
        cq.multiselect(root.get("codenome")).where(builder.equal( root.get("idEquipe"), id));
        Query q = DaoConecta.em.createQuery(cq);
        List<Tuple> tupleResult = q.getResultList();
        List<String> listStrings = new ArrayList<>(  );
        for (Tuple j: tupleResult
             ) {
            listStrings.add(j.get(0).toString());
        }

        String lista_jogadores;

        fecharConexao();
        if(!listStrings.isEmpty()) {
            lista_jogadores = String.join( ", ", listStrings );
        } else{
            lista_jogadores = " ";
        }



        return lista_jogadores;
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

    public String getIntegrantes() {
        return integrantes;
    }
}
