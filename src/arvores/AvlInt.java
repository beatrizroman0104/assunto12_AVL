package arvores;

public class AvlInt {
    private class No{
        int dado;
        No dir;
        No esq;
        int hEsq ;
        int hDir;
    }

    public No root = null;

    public No inserirH(No p, int info) {
        if (p == null) { //nó inserido sempre será nó folha
            p=new No();
            p.dado = info;
            p.esq = null;
            p.dir = null;
            p.hDir=0;
            p.hEsq=0;
        }
        else if (info < p.dado){
            p.esq= inserirH (p.esq, info);
            if (p.esq.hDir > p.esq.hEsq) //Altura do nó será a maior
                p.hEsq = p.esq.hDir + 1; //altura dos seus filhos
            else
                p.hEsq = p.esq.hEsq + 1;
        }
        else {
            p.dir=inserirH(p.dir, info);
            if (p.dir.hDir > p.dir.hEsq)
                p.hDir = p.dir.hDir + 1;
            else
                p.hDir = p.dir.hEsq + 1;
        }
        p = balanceamento(p);
        return p;
    }

    public void mostraFB(No p) {
        if (p != null){
            mostraFB(p.esq);
            mostraFB(p.dir);
            System.out.println("Dados: " + p.dado + "\t FB: " + (p.hDir-p.hEsq));
        }
    }

    public No rotacaoDireita (No p){
        // faz rotação para direita em relação ao nó apontado por p
        No q,temp;
        q = p.esq;
        temp = q.dir;
        q.dir = p;
        p.esq = temp;
        return q;
    }

    public No rotacaoEsquerda(No p) {
        // faz rotação para esquerda em relação ao nó apontado por p
        No q,temp;
        q = p.dir;
        temp = q.esq;
        q.esq = p;
        p.dir = temp;
        return q;
    }

    public No balanceamento (No p) {
    // analisa FB e realiza rotações necessárias para balancear árvore
        int FB = p.hDir - p.hEsq;
        if (FB > 1) {
            int fbFilhoDir = p.dir.hDir - p.dir.hEsq;
            if (fbFilhoDir >= 0)
                p = rotacaoEsquerda(p);
            else {
                p.dir = rotacaoDireita(p.dir);
                p = rotacaoEsquerda(p);
            }
        }
        else {
            if (FB < -1) {
                int fbFilhoEsq = p.esq.hDir - p.esq.hEsq;
                if (fbFilhoEsq <= 0)
                    p = rotacaoDireita(p);
                else {
                    p.esq = rotacaoEsquerda(p.esq);
                    p = rotacaoDireita(p);
                }
            }
        }
        return p;
    }

    public No removeValorAVL(No p, int info) {
        if (p != null) {
            if (info == p.dado) {
                if (p.esq == null && p.dir == null)
                    return null;
                else {
                    if (p.esq == null)
                        return p.dir;
                    else if (p.dir == null)
                        return p.esq;
                    else {
                        No aux, aux2;
                        aux2 = p.dir;
                        aux = p.dir;
                        if (aux.esq == null) {
                            p.dado = aux.dado;
                            p.dir = aux.dir;
                        }else{
                            while (aux.esq != null){
                                aux2 = aux;
                                aux = aux.esq;
                            }
                            p.dado = aux.dado;
                            aux2.esq = null;
                        }
                    }
                }
            } else { // procura dado a ser removido na ABB
                if (info < p.dado)
                    p.esq = removeValorAVL(p.esq, info);
                else
                    p.dir = removeValorAVL(p.dir, info);
            }
        }
        return p;
    }

    public void atualizaAlturas(No p) {
        /*atualiza informação da altura
        de cada nó depois da remoção percorre
        a árvore usando percurso pós-ordem para
        ajustar primeiro os nós folhas (profundidade maior)
        e depois os níveis acima */
        if( p != null) {
            atualizaAlturas(p.esq);
            if (p.esq == null)
                p.hEsq = 0;
            else  if (p.esq.hEsq > p.esq.hDir)
                p.hEsq = p.esq.hEsq+1;
            else
                p.hEsq = p.esq.hDir+1;
            atualizaAlturas(p.dir);
            if (p.dir == null)
                p.hDir = 0;
            else if (p.dir.hEsq > p.dir.hDir)
                p.hDir = p.dir.hEsq+1;
            else
                p.hDir = p.dir.hDir+1;
        }
    }

    public No atualizaAlturaBalanceamento (No p) {
        /*atualiza informação da altura de cada nó depois da remoção
        percorre a árvore usando percurso pós-ordem para ajustar primeiro
        os nós folhas (profundidade maior) e depois os níveis acima */
        if( p != null) {
            p.esq = atualizaAlturaBalanceamento (p.esq);
            if (p.esq == null)
                p.hEsq = 0;
            else  if (p.esq.hEsq > p.esq.hDir)
                p.hEsq = p.esq.hEsq+1;
            else
                p.hEsq = p.esq.hDir+1;
            p.dir = atualizaAlturaBalanceamento (p.dir);
            if (p.dir == null)
                p.hDir = 0;
            else if (p.dir.hEsq > p.dir.hDir)
                p.hDir = p.dir.hEsq+1;
            else
                p.hDir = p.dir.hDir+1;
            p = balanceamento(p);
            atualizaAlturas(p);
        }
        return p;
    }



}
