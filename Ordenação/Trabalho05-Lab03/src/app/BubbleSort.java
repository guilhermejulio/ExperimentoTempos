package app;

final class BubbleSort {
  

    /***
     * Método que realiza a ordenação de elementos com a tecnica de BubbleSort
     * @param elementos Vetor a ser ordenado
     */
    public static void Sort(int [] elementos){
        int auxiliar;

        for(int i = (elementos.length-1);i > 0; i--){
            for(int j=0; j< i; j++){
                if(elementos[j] > elementos[j+1]){
                    auxiliar = elementos[j+1];
                    elementos[j+1] = elementos[j];
                    elementos[j] = auxiliar;
                }
            }
        }
    }
}
