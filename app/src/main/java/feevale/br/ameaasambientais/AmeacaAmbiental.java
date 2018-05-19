package feevale.br.ameaasambientais;

import java.util.Date;

public class AmeacaAmbiental {

    private Integer id;

    private String ameaca;

    private String endereco;

    private String bairro;

    private String impacto; // TODO change front eand back to combo with a Integer, and not a String. In database too.

    private Date dtAtualizacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAmeaca() {
        return ameaca;
    }

    public void setAmeaca(String ameaca) {
        this.ameaca = ameaca;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getImpacto() {
        return impacto;
    }

    public void setImpacto(String impacto) {
        this.impacto = impacto;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }
}
