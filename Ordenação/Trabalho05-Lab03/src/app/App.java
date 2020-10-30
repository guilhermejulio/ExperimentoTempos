package app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class App {

    // private static final double NANO = (1.0f / 1000000000);

    /**
     * Função que preence o vetor de elementos aleatorios entre 0 e o tamanho do vetor vezes 2
     * @param elementos
     */
    public static void Aleatorio(int[] elementos) {
        Random gerRandom = new Random();

        for (int i = 0; i < elementos.length; i++) // numero aleatorio de 0 a tamanho * 2
            elementos[i] = gerRandom.nextInt(elementos.length*2);

    }

    /***
     * Menu
     * 
     * @param leitor
     * @return
     */
    public static int menu(Scanner leitor) {
        System.out.println();
        System.out.println("\tTempo de execução de algoritmos!");
        System.out.println("\n0. Fim do programa");
        System.out.println("1. Executar ordenações até estourar o tempo de 10 Segundos");
        System.out.println("2. Executar APENAS UMA VEZ ordenação e obter tempo");
        System.out.println("\nOpcao:");
        int opcao = Integer.parseInt(leitor.nextLine());
        return opcao;
    }

    /***
     * Metodo que realiza a chamada da ordenação BubbleSort e retorna o tempo
     * @param elementos
     * @return
     */
    public static long BubbleSort(int [] elementos){
        long time;

        long ini, fim;

        ini = System.nanoTime();
        BubbleSort.Sort(elementos);
        fim = System.nanoTime();
        
        time = fim-ini;

        return time;
    }

    /**
     * Metodo que realiza a chamada da ordenação QuickSort e retorna o tempo
     * @param elementos
     * @return
     */
    public static long QuickSort(int [] elementos){
        long time;

        long ini, fim;

        ini = System.nanoTime();
        QuickSort.Sort(elementos,0,elementos.length-1);
        fim = System.nanoTime();
        
        time = fim-ini;

        return time;
    }
   
    /***
     * Metodo que executa varias vezes até estourar o tempo de 10s
     */
    public static void Executar() {
   
        int tamanho = 100;
        int[] elementos = new int[tamanho];

        HashMap <Integer,Long> temposBubble = new HashMap<Integer,Long>();
        HashMap <Integer,Long> temposQuick = new HashMap<Integer,Long>();

        long tempoDecorrido;

        //executar para 100 e 1000.
        for(int i =0; i <2; i++){
            //preenchendo com valores aleatorios
            Aleatorio(elementos);

            tempoDecorrido = BubbleSort(elementos);
            temposBubble.put(tamanho, tempoDecorrido);

            //preenchendo com valores aleatorios
            Aleatorio(elementos);

            tempoDecorrido = QuickSort(elementos);
            temposQuick.put(tamanho, tempoDecorrido);

            tamanho = 1000;
            elementos = new int[tamanho];
        }

        boolean estourou = false;
        tamanho = 10000;

        
        //executar para 10k em diante até estourar (10Segundos)
        while(!estourou){
            //incremento do tamanho do vetor
            elementos = new int[tamanho];

            /*             BUBBLE SORT                  */

            //preenchendo com valores aleatorios
            Aleatorio(elementos);

            //realizar Ordenação
            tempoDecorrido = BubbleSort(elementos);

            //salvar tempo
            temposBubble.put(tamanho, (long) (tempoDecorrido));

            //controle se estourou o tempo
            if(TimeUnit.SECONDS.convert(tempoDecorrido, TimeUnit.NANOSECONDS) > 10){
                estourou = true;
            }


            /*             QUICK SORT                 */

            //preenchendo com valores aleatorios
            Aleatorio(elementos);

            //realizar Ordenação
            tempoDecorrido = QuickSort(elementos);

            //salvar tempo
            temposQuick.put(tamanho, (long) (tempoDecorrido));

            //controle se estourou o tempo
            if(TimeUnit.SECONDS.convert(tempoDecorrido, TimeUnit.NANOSECONDS) > 10){
                estourou = true;
            }

            //aumento do tamanho em +10k
            tamanho += 10000;
        }


        gravarTempos(temposBubble, temposQuick);
        
    }


    /**
     * Metodo para salvar os tempos de execução em arquivo
     * @param temposBubble
     * @param temposQuick
     */
    public static void gravarTempos(HashMap<Integer,Long> temposBubble, HashMap<Integer,Long> temposQuick) {
        // Onde será gravado os resultados
        String path = "Resultados_BubbleSort.txt";
        String path2 = "Resultados_QuickSort.txt";


        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for(Map.Entry tempo : temposBubble.entrySet()){
                bw.write("Número de elementos --> "+tempo.getKey()+"| Tempo gasto: "+tempo.getValue()+" NanoSegundos");
                bw.newLine();
            }
            
            
        } catch (final IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path2))) {
            for(Map.Entry tempo : temposQuick.entrySet()){
                bw.write("Número de elementos --> "+tempo.getKey()+"| Tempo gasto: "+tempo.getValue()+" NanoSegundos");
                bw.newLine();
            }
           
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        int opcao;
        Scanner entrada = new Scanner(System.in);
        Scanner teclado = new Scanner(System.in);

        try {
            do {
                opcao = menu(entrada);
                switch (opcao) {
                    case 1:
                        System.out.println(
                                "\n\n\n\n\n\nSerá feito a ordenação de vetores de tamanho crescente, até estourar o tempo de <10s>");
                        System.out.println("\n\nPrescione <enter> para iniciar a execução...");
                        teclado.nextLine();
                        System.out.println(
                                "\n\n\nEm execução...\n\nOs resultados serão salvos nos arquivos <<Resultados Bubble, Resultados Quick>>...");
                        Executar();
                        break;
                    case 2:
                        System.out.println("\nDigite o tamanho desejado do vetor: ");
                        int tamanho = Integer.parseInt(entrada.nextLine());
                        int[] elementos = new int[tamanho];

                        /*          Bubble                  */
                        Aleatorio(elementos);
                        long tempoBubble = BubbleSort(elementos);

                        /*          Quick                  */
                        Aleatorio(elementos);
                        long tempoQuick = QuickSort(elementos);

                        System.out.println("\n\nTempo gasto no BubbleSort --> "+tempoBubble);
                        System.out.println("\nTempo gasto no QuickSort --> "+tempoQuick);
                        teclado.nextLine();
                        break;

                        default:
                        System.out.println("\nOpção invalida, selecione novamente:");
                        teclado.nextLine();
                        break;
                    
                }
            } while (opcao != 0);

        } catch (Exception ex) {
            System.out.println("Ocorreu um erro: " + ex.getMessage());
        }
    }
}
