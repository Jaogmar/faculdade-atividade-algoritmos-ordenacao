public class FilaPrioridade {
    private Chamado[] heap;
    private int tamanho;

    public FilaPrioridade(int capacidade) {
        heap = new Chamado[capacidade];
        tamanho = 0;
    }

    private boolean temPrioridadeSobre(Chamado a, Chamado b) {
        if (a.prioridade != b.prioridade) {
            return a.prioridade > b.prioridade;
        }
        return a.id < b.id;
    }

    private void trocar(int i, int j) {
        Chamado temporario = heap[i];
        heap[i] = heap[j];
        heap[j] = temporario;
    }

    public void inserir(Chamado c) {
        if (tamanho == heap.length) {
            System.out.println("Fila cheia! Chamado " + c + " não inserido.");
            return;
        }
        heap[tamanho] = c;
        subir(tamanho);
        tamanho++;
    }

    private void subir(int indice) {
        while (indice > 0) {
            int pai = (indice - 1) / 2;
            if (!temPrioridadeSobre(heap[indice], heap[pai])) {
                break;
            }
            trocar(indice, pai);
            indice = pai;
        }
    }

    public Chamado removerMaior() {
        if (tamanho == 0) {
            return null;
        }
        Chamado maior = heap[0];
        heap[0] = heap[tamanho - 1];
        heap[tamanho - 1] = null;
        tamanho--;
        descer(0);
        return maior;
    }

    private void descer(int indice) {
        while (2 * indice + 1 < tamanho) {
            int esquerdo = 2 * indice + 1;
            int direito = 2 * indice + 2;
            int filhoMaior = esquerdo;
            if (direito < tamanho && temPrioridadeSobre(heap[direito], heap[esquerdo])) {
                filhoMaior = direito;
            }
            if (!temPrioridadeSobre(heap[filhoMaior], heap[indice])) {
                break;
            }
            trocar(indice, filhoMaior);
            indice = filhoMaior;
        }
    }

    public void imprimirVetor() {
        for (int i = 0; i < tamanho; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}
