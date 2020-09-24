package TDP_Proyecto_1;

public class Edge {
	protected int v_origen;
	protected int v_destino;
	
	public Edge (int v_origen, int v_destino) {
		this.v_origen = v_origen;
		this.v_destino = v_destino;
	}
	
	public int get_origen() {
		return v_origen;
	}
	
	public int get_destino() {
		return v_destino;
	}
	
	public void set_origen(int v_origen) {
		this.v_origen = v_origen;
	}
	
	public void set_destino(int v_destino) {
		this.v_destino = v_destino;
	}
	
	public String toString() {
		return "( "+v_origen+", "+v_destino+ " )";
	}
	
	public boolean equals(Edge arco) {
		boolean retorno;
		if (v_origen == arco.get_origen() && v_destino == arco.get_destino())
			retorno = true;
		else
			retorno = false;
		return retorno;
	}
}
