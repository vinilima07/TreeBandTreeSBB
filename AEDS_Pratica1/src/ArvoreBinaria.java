/**
 *
 * @author Vinicius
 */
public class ArvoreBinaria {
    /**
    *Ligação entre os elementos.
    */
    private static class No{
        Item reg;
        No esq, dir;
    }
    private No raiz;
    private int compr; //comparaçoes
    
    public ArvoreBinaria(){
        this.raiz = null;
        compr = 0;
    }
    public int getCompr(){
        return this.compr;
    }
    /**
     * Procura pelo item pesquisado.
     * @param reg
     * @return 
     */
    public Item pesquisa(Item reg){
        return this.pesquisa(reg, this.raiz);
    }
    
    /**
     * Pesquisa e escolhe qual a sub-arvore
     * podera conter o item buscado.
     * @param reg
     * @param p
     * @return 
     */
    public Item pesquisa(Item reg, No p){
        this.compr++;
        if(p == null) 
            return null;
        else{
            this.compr++;
            if(reg.compara(p.reg) < 0)
                return pesquisa(reg, p.esq);
            else return p.reg;
        }
       
    }
    
    /**
     * Insere um item na arvore
     * @param reg 
     */
    public void insere(Item reg){
        this.raiz = this.insere(reg, this.raiz);
    }
    
    /**
     * Decidi onde irá ser inserido o item na arvore
     * para mante-la binaria. 
     * @param reg
     * @param p
     * @return 
     */
    private No insere(Item reg, No p){
        if(p == null){
            p = new No();
            p.reg = reg;
            p.esq = null;
            p.dir = null;
        }
        else{
            if(reg.compara(p.reg) < 0)
                p.esq = insere(reg, p.esq);
            else{
                if(reg.compara(p.reg) > 0)
                    p.dir = insere(reg, p.dir);
                else System.out.println("Erro: Registro ja existente");
            }
        }
        return p; 
    }
    
    /**
     * Retira um item da arvore.
     * @param reg 
     */
    public void retira(Item reg){
        this.raiz = this.retira(reg, this.raiz);
    }
    
    /**
     * Apos retirar o item da arvore, o metodo
     * a rearranja.
     * @param reg
     * @param p
     * @return 
     */
    private No retira(Item reg, No p){
        if(p == null)
            System.out.println("Erro: Registro nao encontrado");
        else if(reg.compara(p.reg) < 0)
            p.esq = retira(reg, p.esq);
        else if(reg.compara(p.reg) > 0)
            p.dir = retira(reg, p.dir);
        else{
            if(p.dir == null)
                p = p.esq;
            else if(p.esq == null)
                p = p.dir;
            else p.esq = antecessor(p, p.esq);
        }
        return p;
    }
    
    /**
     * Verifica o antecessor do no, para fazer
     * a troca correta.
     * @param q
     * @param r
     * @return 
     */
    private No antecessor(No q, No r){
        if(r.dir != null)
            r.dir = antecessor(q, r.dir);
        else{
            q.reg = r.reg;
            r = r.esq;
        }
        return r;
    }
}
