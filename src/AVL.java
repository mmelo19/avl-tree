public class AVL {

    private Node raiz;

    public Node getRaiz() {
        return raiz;
    }

    public Node inserir(Integer data) {
        raiz = inserir(data, raiz);
        return raiz;
    }

    private Node inserir(Integer data, Node node) {
        if (node == null) {
            return new Node(data);
        }
        if (data.compareTo(node.valor) < 0){
            node.setEsq(inserir(data, node.getEsq()));
        } else if (data.compareTo(node.valor) > 0) {
            node.setDir(inserir(data, node.getDir()));
        } else {
            return node;
        }

        atualizarAlt(node); //Atualiza a altura do nó pai após a inserção do nó filho
        return Rebalanceamento(node); // Caso haja desequilíbrio, aplica a rotação
    }

    public void remover(Integer data) {
        raiz = remover(data, raiz);
    }

    private Node remover(Integer data, Node node) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.valor) < 0) {
            node.setEsq(remover(data, node.getEsq()));
        } else if (data.compareTo(node.valor) > 0) {
            node.setDir(remover(data, node.getDir()));
        } else {
            // Casos onde há apenas um filho ou o nó a ser removido é um nó folha
            if (node.getEsq() == null) {
                return node.getDir();
            } else if (node.getDir() == null) {
                return node.getEsq();
            }
            // Casos onde há dois filhos
            node.setValor(getMax(node.getEsq()));
            node.setEsq(remover(node.valor, node.getEsq()));
        }
        atualizarAlt(node);
        return Rebalanceamento(node);
    }


    public int getMax() {
        if (isEmpty()) {
            return 0;
        }
        return getMax(raiz);
    }

    private int getMax(Node node) {
        if (node.getDir() != null) {
            return getMax(node.getDir());
        }
        return node.getValor();
    }


    public boolean isEmpty() {
        return raiz == null;
    }

    //Verifica o fator de balanceamento e aplica a rotação se necessário
    private Node Rebalanceamento(Node node) {
        int balance = fatorBalance(node);
        if (balance > 1) { // Caso seja positivo -> desbalanceamento a esquerda
            if (fatorBalance(node.getEsq()) < 0) { // Verifica se o fator do filho a esquerda é positivo também
                node.setEsq(rotacaoEsq(node.getEsq())); // Caso não, aplicamos a rotação a esquerda no filho primeiro
            }
            return rotacaoDir(node); /* Seguido pela rotação a direita no próprio nó. Ou caso seja positivo só aplicamos
            a rotação a direita no próprio nó mesmo */
        }
        if (balance < -1) { // Caso seja negativo -> desbalanceamento a direita
            if (fatorBalance(node.getDir()) > 0) { // Verifica se o fator do filho a direita é negativo também
                node.setDir(rotacaoDir(node.getDir())); // Caso não, aplicamos a rotação a direita no filho primeiro
            }
            return rotacaoEsq(node); /* Seguido pela rotação a esquerda no próprio nó. Ou caso seja negativo só aplicamos
            a rotação a esquerda no próprio nó mesmo */
        }
        return node;
    }

    //Método de rotação a direita
    private Node rotacaoDir(Node node) {
        Node noEsquerdo = node.getEsq(); // Salva o filho a esquerda do nó a ser rotacionado (pai)
        Node noNeto = noEsquerdo.getDir(); // Salva o nó a direita do filho do nó a ser rotacionado
        noEsquerdo.setDir(node); // A direita do filho salvo anteriormente, passa a ser o nó pai
        node.setEsq(noNeto); // A esquerda do nó pai passa a ser o neto a esquerda
        atualizarAlt(node);
        atualizarAlt(noEsquerdo);
        return noEsquerdo;
    }

    //Método de rotação a esquerda
    private Node rotacaoEsq(Node node) {
        Node noDireito = node.getDir(); // Salva o filho a direita do nó a ser rotacionado (pai)
        Node noNeto = noDireito.getEsq(); // Salva o no a esquerda do filho do nó a ser rotacionado
        noDireito.setEsq(node); // A esquerda do filho salvo anteriormente, passa a ser o nó pai
        node.setDir(noNeto); // A direita do nó pai passa a ser o neto a esquerda
        atualizarAlt(node);
        atualizarAlt(noDireito);
        return noDireito;
    }

    //Atualizar altura após inserção ou remoção
    private void atualizarAlt(Node node) {
        int maxHeight = Math.max(getAltura(node.getEsq()), getAltura(node.getDir()));
        node.alt = (maxHeight + 1);
    }

    //Calcular o fator de Balanceamento considerando (Hesquerda - Hdireita)
    private int fatorBalance(Node node) {
        if(node != null){
            return getAltura(node.getEsq()) - getAltura(node.getDir());
        } else{
            return 0;
        }
    }

    //Pegar altura do Nó
    private int getAltura(Node node) {
        if (node != null){
            return node.alt;
        } else {
            return 0;
        }
    }

    //Imprimir em Pré Ordem
    public void preOrdem(Node atual){
        if(atual != null){
            System.out.println(atual.valor);
            preOrdem(atual.esq);
            preOrdem(atual.dir);
        }
    }

    public void print() {
        this.raiz.print();
    }
}