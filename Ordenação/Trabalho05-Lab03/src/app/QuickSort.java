package app;


final class QuickSort {
    /***
     * Metdo de ordenação utilizando a tecnica
     * @param elementos
     * @param esq elemento a esquerda, inicio
     * @param dir elemento a direita, final
     */
    public static void Sort(int [] elementos,int esq, int dir){
        if(esq < dir){
            int part = Partition(elementos,esq,dir);

            Sort(elementos,esq,part-1);
            Sort(elementos,part+1, dir);
        }
    }

    private static int Partition(int [] elementos, int esq, int dir){
        int pivo = elementos[dir];

        int i = esq-1;

        for(int j = esq; j<dir;j++){
            if(elementos[j] <= pivo){
                i++;

                int aux = elementos[i];
                elementos[i] = elementos[j];
                elementos[j] = aux ;
            }
        }

        int aux = elementos[i+1];
        elementos[i+1] = elementos[dir];
        elementos[dir] = aux;

       return i+1;
    }
}
