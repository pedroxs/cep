package br.com.correios.cep.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * The type Address.
 */
@Entity
@Table(name = "address")
@SequenceGenerator(name = "address_seq", sequenceName = "address_seq")
public class Address {

    @Id
    @GeneratedValue(generator = "address_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Valid
    @OneToOne
    @JoinColumn(name = "cep")
    private Cep cep;

    @NotNull
    @Column(name = "numero")
    private Integer numero;

    @Column(name = "complemento", length = 70)
    private String complemento;

    @Column(name = "preferencial")
    private Boolean preferencial;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets cep.
     *
     * @return the cep
     */
    public Cep getCep() {
        return cep;
    }

    /**
     * Sets cep.
     *
     * @param cep the cep
     */
    public void setCep(Cep cep) {
        this.cep = cep;
    }

    /**
     * Gets numero.
     *
     * @return the numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * Sets numero.
     *
     * @param numero the numero
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * Gets complemento.
     *
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * Sets complemento.
     *
     * @param complemento the complemento
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * Gets preferencial.
     *
     * @return the preferencial
     */
    public Boolean getPreferencial() {
        return preferencial;
    }

    /**
     * Sets preferencial.
     *
     * @param preferencial the preferencial
     */
    public void setPreferencial(Boolean preferencial) {
        this.preferencial = preferencial;
    }
}
