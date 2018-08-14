
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Vinicius
 */
public class Main {

    /**
     * Inicia a construção das arvores e seus valores aleatórios e ordenados
     * para realizar os testes.
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args) {
        
        System.out.println("**********PARA ELEMENTOS ORDENADOS***********");
        for(int j = 1; j < 11 ;j++){
            
            ArvoreSBB ab = new ArvoreSBB();
            
            for(int i = 0; i < 10000 ; i++){
                Item item = new Item(i+(j*10000));
                ab.insere(item);
            }
            
            long init = System.nanoTime(); //tempo inicial
            Item item = new Item(777888); 
            ab.pesquisa(item);            //busca pelo item ñ exist
            long fin = System.nanoTime(); //tempo final
            
            System.out.println("Para "+(j*10000)+" elementos"
                               +"\nTempo gasto: "+(fin-init)+"ns"
                               +"\nCom: "+ab.getCompr()+" comparacoes");
            System.out.println("----------------------------------");
        }
        
        System.out.println("\n\n*********PARA ELEMENTOS ALEATORIOS**********");
        for(int j = 1; j < 11 ;j++){
            
            ArvoreSBB ab = new ArvoreSBB();
            List<Integer> l = new ArrayList<>();
            
            for(int i = 0; i < 10000 ; i++){
                l.add(i+(j*1000));
            }
            
            //embaralha os itens da lista para inserir itens"aleatorios"
            Collections.shuffle(l); 
            
            for(int i = 0; i < 10000 ; i++){
                ab.insere(new Item(l.get(i)));
            }
            
            long init = System.nanoTime(); //tempo inicial
            Item item = new Item(777888); 
            ab.pesquisa(item);            //busca pelo item ñ exist
            long fin = System.nanoTime(); //tempo final
            
            System.out.println("Para "+(j*10000)+" elementos"
                               +"\nTempo gasto: "+(fin-init)+"ns"
                               +"\nCom: "+ab.getCompr()+" comparacoes");
            System.out.println("----------------------------------");
        }
        

    }
    
}
