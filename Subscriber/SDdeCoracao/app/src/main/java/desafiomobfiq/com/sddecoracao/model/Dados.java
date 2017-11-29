package desafiomobfiq.com.sddecoracao.model;

public class Dados {
    private String ip;
    private int [] tags;
    private String mensagemSaida;

    public Dados() {
    }

    public Dados(String ip, int [] tags, String mensagemSaida) {
        this.ip = ip;
        this.tags = tags;
        this.mensagemSaida = mensagemSaida;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int[] getTags() {
        return tags;
    }

    public String getMensagemSaida() {
        return mensagemSaida;
    }

    public void setMensagemSaida(String mensagemSaida) {
        this.mensagemSaida = mensagemSaida;
    }
}
