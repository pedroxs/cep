package br.com.correios.cep.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The type Cep.
 */
@Entity
@Table(name = "cep")
public class Cep {

    @NotBlank
    @Id
    @Column(name = "cep", length = 8, nullable = false, unique = true)
    private String cep;

    @NotBlank
    @Column(name = "uf", length = 2)
    private String uf;

    @NotBlank
    @Column(name = "cidade", length = 50)
    private String cidade;

    @NotBlank
    @Column(name = "logradouro", length = 70)
    private String logradouro;

    @Column(name = "bairro", length = 72)
    private String bairro;

    @Column(name = "tipo_logradouro", length = 20)
    private String tipoLogradouro;

    /**
     * Gets cep.
     *
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * Sets cep.
     *
     * @param cep the cep
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * Gets uf.
     *
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * Sets uf.
     *
     * @param uf the uf
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * Gets cidade.
     *
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Sets cidade.
     *
     * @param cidade the cidade
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Gets logradouro.
     *
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * Sets logradouro.
     *
     * @param logradouro the logradouro
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * Gets bairro.
     *
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * Sets bairro.
     *
     * @param bairro the bairro
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * Gets tipo logradouro.
     *
     * @return the tipo logradouro
     */
    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    /**
     * Sets tipo logradouro.
     *
     * @param tipoLogradouro the tipo logradouro
     */
    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }
}
