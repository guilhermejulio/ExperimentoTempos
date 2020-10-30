import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class App {

    public static int fibbonacci(int n) {
        return (n < 2) ? 1 : fibbonacci(n - 1) + fibbonacci(n - 2);
    }

    public static void executar(int N) {
        HashMap<Integer, Long> tempos = new HashMap<Integer, Long>();
        boolean estourou = false;
        long ini, fim;

        while (!estourou) {
            ini = System.nanoTime();
            int fibo = fibbonacci(N);
            fim = System.nanoTime();

            long tempoDecorrido = fim - ini;

            if (TimeUnit.SECONDS.convert(tempoDecorrido, TimeUnit.NANOSECONDS) > 30) {
                estourou = true;
            }

            tempos.put(fibo, tempoDecorrido);
            N++;
        }

        gravarTempo(tempos);
    }

    private static void gravarTempo(HashMap<Integer, Long> tempos) {
        String path = "Log_Fibbonacci.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            for(Map.Entry tempo : tempos.entrySet()){
                bw.write("Numero -->  "+tempo.getKey()+"     | Tempo decorrido:    "+tempo.getValue()+" nanosegundos");
                bw.newLine();
            }
        }catch (final IOException e) {
            e.printStackTrace();
        }
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
        System.out.println("1. Executar Fibbonacci a partir de N=3, até estourar 30 segundos");
        System.out.println("2. Executar a partir de outro N");
        System.out.println("\nOpcao:");
        int opcao = Integer.parseInt(leitor.nextLine());
        return opcao;
    }

    public static void main(String[] args) throws Exception {
        int opcao;
        Scanner entrada = new Scanner(System.in);
        Scanner teclado = new Scanner(System.in);
        int N = 3;

        try{
            do{
                opcao = menu(entrada);
                switch(opcao){
                    case 1:
                        System.out.println("\n\n\n\n\n\nSerá feita a execução a partir de N=3 até estourar o tempo de <30s>\n\nPrescione <enter> para iniciar...");
                        teclado.nextLine();
                        System.out.println("\n\n\n\nEm execução...\n\nOs tempos serão gravados no arquivo 'LogFibbonacci.txt' ");
                        executar(N);
                    break;
                    case 2:
                        System.out.println("\n\n\n\n\n\nDigite um valor para N: ");
                        N = Integer.parseInt(entrada.nextLine());
                        System.out.println("\n\n\n\nEm execução...\n\nOs tempos serão gravados no arquivo 'LogFibbonacci.txt' ");
                        executar(N);
                    break;
                    default:
                        System.out.println("\nOpção invalida, selecione novamente:");
                        teclado.nextLine();
                    break;
                }
            }while (opcao != 0);
            
        }catch (Exception ex) {
            System.out.println("Ocorreu um erro: " + ex.getMessage());
        }

    }   
}
