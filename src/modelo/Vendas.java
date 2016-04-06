package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Vendas {

	@Id
	@GeneratedValue
	private Long id;
	private String datahora;
	private double total;
	private String situacao;

	// Relacionamento com Clientes
	@ManyToOne
	@JoinColumn(name = "cod_clientes")
	private Clientes clientes;

	// Relacionamento com Itens
	@OneToMany(mappedBy = "vendas", targetEntity = Itens.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Itens> itens;

	public Vendas() {
		// Hora e data
		Date dataAtual = new Date();
		SimpleDateFormat sdf2 = new SimpleDateFormat();
		String data = sdf2.format(dataAtual);
		this.setDatahora(data);
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDatahora() {
		return datahora;
	}

	public void setDatahora(String datahora) {
		this.datahora = datahora;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Clientes getClientes() {
		return clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public List<Itens> getItens() {
		return itens;
	}

	public void setItens(List<Itens> itens) {
		this.itens = itens;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	

}
