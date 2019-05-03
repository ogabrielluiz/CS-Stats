/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gabri
 */
@Entity
@Table(name = "tb_equipes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM tb_equipes p")
    , @NamedQuery(name = "Pessoa.findById_equipe", query = "SELECT p FROM tb_equipes p WHERE p.Id_equipe = :Id_equipe")
    , @NamedQuery(name = "Pessoa.findByNome", query = "SELECT p FROM tb_equipes p WHERE p.nome = :nome")
    , @NamedQuery(name = "Pessoa.findByImagem", query = "SELECT p FROM tb_equipes p WHERE p.imagem = :imagem")
    , @NamedQuery(name = "Pessoa.findByOrigem", query = "SELECT p FROM tb_equipes p WHERE p.origem = :origem")})
public class Equipe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_equipe")
    private Integer id_equipe;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "imagem")
    private String imagem;
    
    @Column(name = "origem")
    private String origem;
    
    
    public Equipe(){   
    }
    
    public Equipe(Integer id_equipe) {
        this.id_equipe = id_equipe;
    }

    public Integer getId_equipe() {
        return id_equipe;
    }

    public void setId_equipe(Integer id_equipe) {
        this.id_equipe = id_equipe;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipe)) {
            return false;
        }
        Equipe other = (Equipe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODEL.Equipe[ id=" + id + " ]";
    }
    
}
