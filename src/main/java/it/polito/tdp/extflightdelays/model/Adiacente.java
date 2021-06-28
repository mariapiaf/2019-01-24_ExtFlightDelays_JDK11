package it.polito.tdp.extflightdelays.model;

public class Adiacente implements Comparable<Adiacente>{
	
	private String verticeOpposto;
	private int peso;
	
	public Adiacente(String verticeOpposto, int peso) {
		super();
		this.verticeOpposto = verticeOpposto;
		this.peso = peso;
	}

	public String getVerticeOpposto() {
		return verticeOpposto;
	}

	public void setVerticeOpposto(String verticeOpposto) {
		this.verticeOpposto = verticeOpposto;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return  verticeOpposto + " " + peso ;
	}

	@Override
	public int compareTo(Adiacente o) {
		return -(this.peso-o.peso);
	}
	
	
}
