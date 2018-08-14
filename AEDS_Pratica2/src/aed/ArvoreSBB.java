package aed;


public class ArvoreSBB {
    /**
    *Ligação entre os elementos.
    */
    private static class No { 
      Item reg; 
      No esq, dir;
      byte incE, incD;
    }
    private int compr = 0;//comparaçoes
    private static final byte Horizontal = 0; 
    private static final byte Vertical = 1; 
    private No raiz;
    private boolean propSBB;

    /**
     * Pesquisa e escolhe qual a sub-arvore
     * podera conter o item buscado.
     * @param reg
     * @param p
     * @return 
     */
    private Item pesquisa (Item reg, No p) {
        this.compr++;
        if (p == null) return null;
        else{
        this.compr++;
        if (reg.compara (p.reg) < 0) return pesquisa (reg, p.esq);
            else{
                this.compr++;
                if (reg.compara (p.reg) > 0) return pesquisa (reg, p.dir);
                else return p.reg;
            }
      }
    }

    private No ee (No ap) {
      No ap1 = ap.esq; ap.esq = ap1.dir; ap1.dir = ap;
      ap1.incE = Vertical; ap.incE = Vertical; ap = ap1;
      return ap; 
    }

    private No ed (No ap) {
      No ap1 = ap.esq; No ap2 = ap1.dir; ap1.incD = Vertical;
      ap.incE = Vertical; ap1.dir = ap2.esq; ap2.esq = ap1;
      ap.esq = ap2.dir; ap2.dir = ap; ap = ap2;    
      return ap; 
    }

    private No dd (No ap) {
      No ap1 = ap.dir; ap.dir = ap1.esq; ap1.esq = ap;
      ap1.incD = Vertical; ap.incD = Vertical; ap = ap1;
      return ap; 
    }

    private No de (No ap) {
      No ap1 = ap.dir; No ap2 = ap1.esq; ap1.incE = Vertical;
      ap.incD = Vertical; ap1.esq = ap2.dir; ap2.dir = ap1;
      ap.dir = ap2.esq; ap2.esq = ap; ap = ap2;    
      return ap; 
    }
    /**
     * Decidi onde irá ser inserido o item na arvore
     * para mante-la binaria e balanceada. 
     * @param reg
     * @param pai
     * @param filho
     * @param filhoEsq
     * @return 
     */
    private No insere (Item reg, No pai, No filho, boolean filhoEsq) {
      if (filho == null) {
        filho = new No (); filho.reg = reg; 
        filho.incE = Vertical; filho.incD = Vertical;
        filho.esq = null; filho.dir = null;
        if (pai != null)
          if (filhoEsq) pai.incE = Horizontal; else pai.incD = Horizontal;
        this.propSBB = false;
      }
      else if (reg.compara (filho.reg) < 0) {
        filho.esq = insere (reg, filho, filho.esq, true);
        if (!this.propSBB) 
          if (filho.incE == Horizontal) { 
            if (filho.esq.incE == Horizontal) {
              filho = this.ee (filho); // @{\it trasforma\c{c}\~ao esquerda-esquerda}@
              if (pai != null)
                if (filhoEsq) pai.incE=Horizontal; else pai.incD=Horizontal;
            }
            else if (filho.esq.incD == Horizontal) {
              filho = this.ed (filho); // @{\it trasforma\c{c}\~ao esquerda-direita}@
              if (pai != null) 
                if (filhoEsq) pai.incE=Horizontal; else pai.incD=Horizontal;            
            }
          }
          else this.propSBB = true;
      }
      else if (reg.compara (filho.reg) > 0) {
        filho.dir = insere (reg, filho, filho.dir, false);
        if (!this.propSBB) 
          if (filho.incD == Horizontal) {
            if (filho.dir.incD == Horizontal) {
              filho = this.dd (filho); // @{\it trasforma\c{c}\~ao direita-direita}@
              if (pai != null)
                if (filhoEsq) pai.incE=Horizontal; else pai.incD=Horizontal;
            }
            else if (filho.dir.incE == Horizontal) {
              filho = this.de (filho); // @{\it trasforma\c{c}\~ao direita-esquerda}@
              if (pai != null)
                if (filhoEsq) pai.incE=Horizontal; else pai.incD=Horizontal;            
            }
          }
          else this.propSBB = true;
      }
      else {      
        System.out.println ("Erro: Registro ja existente"); 
        this.propSBB = true;
      }
      return filho; 
    }

    public int getCompr() {
        return compr;
    }

    public ArvoreSBB () {
      this.raiz = null;
      this.propSBB = true;
    }
  
    /**
     * Procura pelo item pesquisado.
     * @param reg
     * @return 
     */
    public Item pesquisa (Item reg) {
      return this.pesquisa (reg, this.raiz);
    }

    /**
     * Insere um item na arvore
     * @param reg 
     */
    public void insere (Item reg) {
      this.raiz = insere (reg, null, this.raiz, true);
    }

}
