public class Main {
    public static void main(String[] args) {
        AVL tree = new AVL();

        tree.inserir(1);
        tree.inserir(10);
        tree.inserir(6);
        tree.inserir(5);
        tree.inserir(4);
        tree.inserir(3);
        tree.inserir(20);
        tree.inserir(40);

        tree.print();

        tree.remover(6);
        tree.print();
    }
}