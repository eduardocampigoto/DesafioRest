package br.com.neomind.objetos;

import org.codehaus.jackson.annotate.JsonProperty;



public class Cliente {
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("name") 
	private String name;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("cnpj")
	private String cnpj;
	
	@JsonProperty("comment")
	private String comment;
	

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
	

}
