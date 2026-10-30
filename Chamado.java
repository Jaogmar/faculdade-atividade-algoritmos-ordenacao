public class Chamado {
    int id;
    int prioridade;

    public Chamado(int id, int prioridade) {
        this.id = id;
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        return "#" + id + "(P" + prioridade + ")";
    }
}
