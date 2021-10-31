package model;

public class Endereco {

	private String rua;
	private int numero;
	private String complemento;
	private int id;
	
	
	public Endereco() {
		super();
	}


	public Endereco(String rua, int numero, String complemento, int id) {
		super();
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.id = id;
	}


	public String getRua() {
		return rua;
	}


	public void setRua(String rua) {
		this.rua = rua;
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Endereco [rua=" + rua + ", numero=" + numero + ", complemento=" + complemento + ", id=" + id + "]";
	}
	
	
	
	
}
