public class Main {
    public static void main(String[] args) {
        AVL tree = new AVL();

        tree.remover(1);
        tree.inserir(10);
        tree.inserir(6);
        tree.inserir(5);
        tree.inserir(20);
        tree.inserir(32);
        tree.inserir(19);
        tree.print();

        tree.remover(10);
        tree.remover(20);
        tree.print();
    }
}