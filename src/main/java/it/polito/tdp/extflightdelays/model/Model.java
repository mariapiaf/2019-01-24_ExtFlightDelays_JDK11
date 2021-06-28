package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	private ExtFlightDelaysDAO dao;
	private Graph<String, DefaultWeightedEdge> grafo;
	
	public Model() {
		dao = new ExtFlightDelaysDAO();
	}
	
	public void creaGrafo() {
		grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		// aggiungo vertici
		Graphs.addAllVertices(this.grafo, dao.getVertici());
		
		// aggiungo archi
		for(Arco a: dao.getArchi()) {
			if(!a.getS1().equals(a.getS2()))
				Graphs.addEdge(this.grafo, a.getS1(), a.getS2(), a.getPeso());
		}
	}
	
	public List<Adiacente> getAdiacentiA(String partenza) {
		List<Adiacente> result = new ArrayList<>();
		for(String vicino: Graphs.successorListOf(this.grafo, partenza)) {
			result.add(new Adiacente(vicino, (int) grafo.getEdgeWeight(grafo.getEdge(partenza, vicino))));
		}
		
		Collections.sort(result);
		return result;
	}
	
	public Set<String> getVertici(){
		return grafo.vertexSet();
	}
	
	public int nVertici() {
		return grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return grafo.edgeSet().size();
	}
}
