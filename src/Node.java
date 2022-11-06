public class Node {
    int valor;
    int alt = 1;
    Node esq;
    Node dir;

    public Node(int v){
        this.valor = v;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Node getEsq() {
        return esq;
    }

    public void setEsq(Node esq) {
        this.esq = esq;
    }

    public Node getDir() {
        return dir;
    }

    public void setDir(Node dir) {
        this.dir = dir;
    }

    public void print() {
        if (dir != null)
            dir.print(false, "");
        System.out.println(this.valor + "");
        if (esq != null)
            esq.print(true, "");
        System.out.println("....................................");
    }

    private void print(boolean isLeft, String indentation) {
        if (dir != null)
            dir.print(false, indentation + (isLeft ? "|       " : "        "));
        System.out.print(indentation);
        if (isLeft)
            System.out.print(" \\");
        else
            System.out.print(" /");
        System.out.print("----- ");
        System.out.println(this.valor + "");
        if (esq != null)
            esq.print(true, indentation + (isLeft ? "        " : "|       "));
    }


}