package CSstats;

public class SQLQueries {



    public String update(String tabela, String coluna, String valor){

        return "UPDATE " + tabela + "SET " + coluna + "(" + valor + ")";
    }

    public String insert(String tabela, String coluna, String valor){
        return "INSERT INTO " + tabela + "(" + coluna + ") " + "VALUES(" + valor + ")";
    }

    public String insert(String tabela, String coluna1, String coluna2, String valores){
        return "INSERT INTO " + tabela + "(" + coluna1 + "," + coluna2 +") " + "VALUES(" + valores + ")";
    }

    public String insert(String tabela, String coluna1, String coluna2, String coluna3, String valores){
        return "INSERT INTO " + tabela + "(" + coluna1 + "," + coluna2 + "," + coluna3 +") " + "VALUES(" + valores + ")";
    }

    public String insert(String tabela, String coluna1, String coluna2,
                         String coluna3, String coluna4, String valores){
        return "INSERT INTO " + tabela + "(" + coluna1 + "," + coluna2 + "," +
                coluna3 + "," + coluna4 +") " + "VALUES(" + valores + ")";
    }

    public String insert(String tabela, String coluna1, String coluna2, String coluna3,
                         String coluna4, String coluna5, String valores){
        return "INSERT INTO " + tabela + "(" + coluna1 + "," + coluna2 + "," + coluna3
                + "," + coluna4 + "," + coluna5 +") " + "VALUES(" + valores + ")";
    }

    public String delete(String tabela, String condicao){
        return "DELETE FROM" + tabela + " WHERE " + condicao;
    }

}
