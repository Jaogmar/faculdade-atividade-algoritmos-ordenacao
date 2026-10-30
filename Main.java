public class Main {
    static Chamado[] criarChamados() {
        int[] prioridades = {3, 3, 5, 4, 5, 1, 4, 2};
        Chamado[] chamados = new Chamado[prioridades.length];
        for (int i = 0; i < prioridades.length; i++) {
            chamados[i] = new Chamado(i + 1, prioridades[i]);
        }
        return chamados;
    }

    static void insertionSort(Chamado[] v) {
        int deslocamentos = 0;
        for (int i = 1; i < v.length; i++) {
            Chamado atual = v[i];
            int j = i - 1;
            while (j >= 0 && v[j].prioridade < atual.prioridade) {
                v[j + 1] = v[j];
                deslocamentos++;
                j--;
            }
            v[j + 1] = atual;
        }
        System.out.println("Insertion Sort - deslocamentos: " + deslocamentos);
    }

    static void selectionSort(Chamado[] v) {
        int trocas = 0;
        for (int i = 0; i < v.length - 1; i++) {
            int maior = i;
            for (int j = i + 1; j < v.length; j++) {
                if (v[j].prioridade > v[maior].prioridade) {
                    maior = j;
                }
            }
            if (maior != i) {
                Chamado temporario = v[i];
                v[i] = v[maior];
                v[maior] = temporario;
                trocas++;
            }
        }
        System.out.println("Selection Sort - trocas: " + trocas);
    }

    static void imprimirIds(String rotulo, Chamado[] v) {
        System.out.print(rotulo);
        for (int i = 0; i < v.length; i++) {
            System.out.print(v[i].id + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Chamado[] chamados = criarChamados();

        System.out.println("=== Parte 1 - Fila de prioridade com heap ===");
        FilaPrioridade fila = new FilaPrioridade(chamados.length);
        for (int i = 0; i < chamados.length; i++) {
            fila.inserir(chamados[i]);
        }
        System.out.print("Vetor do heap: ");
        fila.imprimirVetor();

        Chamado[] ordemFila = new Chamado[chamados.length];
        System.out.print("Ordem de atendimento: ");
        int posicao = 0;
        Chamado atendido = fila.removerMaior();
        while (atendido != null) {
            ordemFila[posicao] = atendido;
            posicao++;
            System.out.print(atendido + " ");
            atendido = fila.removerMaior();
        }
        System.out.println();

        System.out.println();
        System.out.println("=== Parte 2 - Insertion Sort x Selection Sort ===");
        Chamado[] copiaInsertion = chamados.clone();
        insertionSort(copiaInsertion);
        Chamado[] copiaSelection = chamados.clone();
        selectionSort(copiaSelection);

        System.out.println();
        System.out.println("=== Parte 2.3 - Comparação dos ids ===");
        imprimirIds("Fila (heap):    ", ordemFila);
        imprimirIds("Insertion Sort: ", copiaInsertion);
        imprimirIds("Selection Sort: ", copiaSelection);
        System.out.println("Fora da ordem de chegada: no Selection Sort, #2 e #1 (P3) ficaram invertidos.");
        System.out.println("Heap e Insertion Sort mantiveram a ordem de chegada em todos os empates.");
    }
}
