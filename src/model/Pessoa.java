package model;

public class Pessoa {

	private String cpf;
	private String nome;
	private int idade;
	private String genero;
	private Endereco endereco;
	private Conta conta;
	
	
	public Pessoa() {
		super();
	}


	public Pessoa(String cpf, String nome, int idade, String genero, Endereco endereco, Conta conta) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
		this.genero = genero;
		this.endereco = endereco;
		this.conta = conta;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getIdade() {
		return idade;
	}


	public void setIdade(int idade) {
		this.idade = idade;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public Conta getConta() {
		return conta;
	}


	public void setConta(Conta conta) {
		this.conta = conta;
	}


	@Override
	public String toString() {
		return "Pessoa [cpf=" + cpf + ", nome=" + nome + ", idade=" + idade + ", genero=" + genero + ", endereco="
				+ endereco + ", conta=" + conta + "]";
	}
	
	
	
	
}
