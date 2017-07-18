import java.util.Calendar;

public class Recurso {
	private String nome;
	private int id;
    private boolean alocado;
    private boolean emProcessoDeAlocacao;
    private boolean emAndamento;
    private boolean concluido;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public boolean isAlocado() {
        return alocado;
    }

    public void setAlocado(boolean alocado) {
        this.alocado = alocado;
    }

    public boolean isEmProcessoDeAlocacao() {
        return emProcessoDeAlocacao;
    }

    public void setEmProcessoDeAlocacao(boolean emProcessoDeAlocacao) {
        this.emProcessoDeAlocacao = emProcessoDeAlocacao;
    }

    public boolean isEmAndamento() {
        return emAndamento;
    }

    public void setEmAndamento(boolean emAndamento) {
        this.emAndamento = emAndamento;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }
}
