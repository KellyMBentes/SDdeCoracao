
public class MensagemParaEnviar {
	private int id;
	private int idClient;
	private String clientEnd;
	private String content;
	private int received;
	
	public MensagemParaEnviar(int id, int idC, String end, String msg, int rcvd){
		this.setId(id);
		this.setIdClient(idC);
		this.setClientEnd(end);
        this.setMensagem(msg);
        this.setReceived(rcvd);
	}

	//Getters
	public int getId() {
		return id;
	}
	
	public int getIdClient() {
		return idClient;
	}

	public String getClientEnd() {
		return clientEnd;
	}

	public String getMensagem() {
		return content;
	}

	public int getReceived() {
		return received;
	}

	//Setters
	public void setId(int id) {
		this.id = id;
	}
	
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public void setClientEnd(String clientEnd) {
		this.clientEnd = clientEnd;
	}

	public void setMensagem(String mensagem) {
		this.content = mensagem;
	}

	public void setReceived(int received) {
		this.received = received;
	}
	
	@Override
	public String toString(){
		return (id + " " + clientEnd + " " + content + " " + received);
	}

}
