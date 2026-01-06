package pecas;

import Tabuleiro.Casa;
import Tabuleiro.Tabuleiro;

import java.util.ArrayList;

import static Tabuleiro.Casa.BY_BLACK;
import static Tabuleiro.Casa.BY_WHITE;
import static Tabuleiro.Tabuleiro.*;

public class Rei extends Peca {
    private int pecasAtacantes = 0;
    private boolean isInCheck = false;

    public Rei(int coluna, int fileira, int cor) {
        super(coluna, fileira, cor);
        super.tipo = (super.cor == BRANCO) ? '♚' : '♔';
        super.casasLegais = new ArrayList<>(10);

        if (super.cor == BRANCO) {
            setReiBranco(this);
        } else {
            setReiPreto(this);
        }
    }

    @Override
    public void setCasasLegais() {
        super.casasLegais.clear();
        ArrayList<Casa> arrayCorrespondente = (this.getCor() == BRANCO) ? casasLegaisPecasBrancas
                : casasLegaisPecasPretas;

        int byCorAtual = (super.getCor() == BRANCO) ? BY_WHITE : BY_BLACK;

        // Movimentos para direita
        int idColuna = super.getColuna() + 1;
        if (idColuna < 8) {
            Casa casaNaFileira = Tabuleiro.getCasa(idColuna, super.getFileira());
            Peca pecaNaFileira = casaNaFileira.getPeca();

            if (pecaNaFileira == null) {
                super.casasLegais.add(casaNaFileira);
                arrayCorrespondente.add(casaNaFileira);
            } else {
                if (pecaNaFileira.getCor() != super.getCor()) {
                    super.casasLegais.add(casaNaFileira);
                    arrayCorrespondente.add(casaNaFileira);
                }
            }
            casaNaFileira.setAtacked(byCorAtual);
        }

        // Movimentos para esquerda
        idColuna = super.getColuna() - 1;
        if (idColuna >= 0) {
            Casa casaNaFileira = Tabuleiro.getCasa(idColuna, super.getFileira());
            Peca pecaNaFileira = casaNaFileira.getPeca();

            if (pecaNaFileira == null) {
                super.casasLegais.add(casaNaFileira);
                arrayCorrespondente.add(casaNaFileira);
            } else {
                if (pecaNaFileira.getCor() != super.getCor()) {
                    super.casasLegais.add(casaNaFileira);
                    arrayCorrespondente.add(casaNaFileira);
                }
            }
            casaNaFileira.setAtacked(byCorAtual);
        }

        // Movimentos para cima
        int idFileira = super.getFileira() + 1;
        if (idFileira < 8) {
            Casa casaNaColuna = Tabuleiro.getCasa(super.getColuna(), idFileira);
            Peca pecaNaColuna = casaNaColuna.getPeca();

            if (pecaNaColuna == null) {
                super.casasLegais.add(casaNaColuna);
                arrayCorrespondente.add(casaNaColuna);
            } else {
                if (pecaNaColuna.getCor() != super.getCor()) {
                    super.casasLegais.add(casaNaColuna);
                    arrayCorrespondente.add(casaNaColuna);
                }
            }
            casaNaColuna.setAtacked(byCorAtual);
        }

        // Movimentos para baixo
        idFileira = super.getFileira() - 1;
        if (idFileira >= 0) {
            Casa casaNaColuna = Tabuleiro.getCasa(super.getColuna(), idFileira);
            Peca pecaNaColuna = casaNaColuna.getPeca();

            if (pecaNaColuna == null) {
                super.casasLegais.add(casaNaColuna);
                arrayCorrespondente.add(casaNaColuna);
            } else {
                if (pecaNaColuna.getCor() != super.getCor()) {
                    super.casasLegais.add(casaNaColuna);
                    arrayCorrespondente.add(casaNaColuna);
                }
            }
            casaNaColuna.setAtacked(byCorAtual);
        }

        // Movimento para diagonal direita superior
        idColuna = super.getColuna() + 1;
        idFileira = super.getFileira() + 1;
        if (idColuna < 8 && idFileira < 8) {
            Casa casaNaDiagonal = Tabuleiro.getCasa(idColuna, idFileira);
            Peca pecaNaDiagonal = casaNaDiagonal.getPeca();

            if (pecaNaDiagonal == null) {
                super.casasLegais.add(casaNaDiagonal);
                arrayCorrespondente.add(casaNaDiagonal);
            } else {
                if (pecaNaDiagonal.getCor() != super.getCor()) {
                    super.casasLegais.add(casaNaDiagonal);
                    arrayCorrespondente.add(casaNaDiagonal);
                }
            }
            casaNaDiagonal.setAtacked(byCorAtual);
        }

        // Movimento para diagonal esquerda superior
        idColuna = super.getColuna() - 1;
        idFileira = super.getFileira() + 1;
        if (idColuna >= 0 && idFileira < 8) {
            Casa casaNaDiagonal = Tabuleiro.getCasa(idColuna, idFileira);
            Peca pecaNaDiagonal = casaNaDiagonal.getPeca();

            if (pecaNaDiagonal == null) {
                super.casasLegais.add(casaNaDiagonal);
                arrayCorrespondente.add(casaNaDiagonal);
            } else {
                if (pecaNaDiagonal.getCor() != super.getCor()) {
                    super.casasLegais.add(casaNaDiagonal);
                    arrayCorrespondente.add(casaNaDiagonal);
                }
            }
            casaNaDiagonal.setAtacked(byCorAtual);
        }

        // Movimento para diagonal esquerda inferior
        idColuna = super.getColuna() - 1;
        idFileira = super.getFileira() - 1;
        if (idColuna >= 0 && idFileira >= 0) {
            Casa casaNaDiagonal = Tabuleiro.getCasa(idColuna, idFileira);
            Peca pecaNaDiagonal = casaNaDiagonal.getPeca();

            if (pecaNaDiagonal == null) {
                super.casasLegais.add(casaNaDiagonal);
                arrayCorrespondente.add(casaNaDiagonal);
            } else {
                if (pecaNaDiagonal.getCor() != super.getCor()) {
                    super.casasLegais.add(casaNaDiagonal);
                    arrayCorrespondente.add(casaNaDiagonal);
                }
            }
            casaNaDiagonal.setAtacked(byCorAtual);
        }

        // Movimento para diagonal direita inferior
        idColuna = super.getColuna() + 1;
        idFileira = super.getFileira() - 1;
        if (idColuna < 8 && idFileira >= 0) {
            Casa casaNaDiagonal = Tabuleiro.getCasa(idColuna, idFileira);
            Peca pecaNaDiagonal = casaNaDiagonal.getPeca();

            if (pecaNaDiagonal == null) {
                super.casasLegais.add(casaNaDiagonal);
                arrayCorrespondente.add(casaNaDiagonal);
            } else {
                if (pecaNaDiagonal.getCor() != super.getCor()) {
                    super.casasLegais.add(casaNaDiagonal);
                    arrayCorrespondente.add(casaNaDiagonal);
                }
            }
            casaNaDiagonal.setAtacked(byCorAtual);
        }

        // Castling Logic Integration
        canCastleToTheLeft();
        canCastleToTheRight();
    }

    @Override
    public void filtrarCasasLegais() {
        int byCorInimiga = (super.getCor() == BRANCO) ? BY_BLACK : BY_WHITE;

        ArrayList<Casa> temp = new ArrayList<>();
        for (Casa casa : super.casasLegais) {
            if (!casa.isAtacked().contains(byCorInimiga)) {
                temp.add(casa);
            }
        }
        super.casasLegais.clear(); // Esvazia as casas legais,
        super.casasLegais = temp; // e troca pela interseção encontrada.
    }

    public void setIsInCheck(boolean isInCheck) {
        this.isInCheck = isInCheck;
    }

    public boolean isInCheck() {
        ArrayList<Casa> casasInimigas = (this.getCor() == BRANCO) ? casasLegaisPecasPretas : casasLegaisPecasBrancas;

        setIsInCheck(false);
        for (Casa casa : casasInimigas) {
            if (casa == this.getCasa()) {
                setIsInCheck(true);
                break;
            }
        }

        return isInCheck;
    }

    public boolean isCheckmated() {
        ArrayList<Casa> arrayCorrespondente = (super.getCor() == BRANCO) ? casasLegaisPecasBrancas
                : casasLegaisPecasPretas;

        if (arrayCorrespondente.isEmpty()) {
            if (this.isInCheck()) {
                return true;
            }
        }
        return false;
    }

    public boolean isStalemate() {
        ArrayList<Casa> arrayCorrespondente = (super.getCor() == BRANCO) ? casasLegaisPecasBrancas
                : casasLegaisPecasPretas;

        if (arrayCorrespondente.isEmpty()) {
            if (!this.isInCheck()) {
                return true;
            }
        }
        return false;
    }

    public boolean canCastleToTheLeft() {
        Peca peca = Tabuleiro.getCasa(COLUNA_A, super.getFileira()).getPeca(); // cria uma instância de peca na coluna A
        if (this.isInCheck() || super.jaMoveu || (peca instanceof Torre && peca.jaMoveu())) {
            return false;
        } else {
            if (!isPathClear(super.getColuna() - 1, COLUNA_B, -1)) {
                return false;
            }

            if (peca instanceof Torre && peca.getCor() == super.getCor()) { // se houver peça na casa, for uma torre da
                                                                            // mesma cor do rei, pode rocar.
                super.casasLegais.add(Tabuleiro.getCasa(COLUNA_C, super.getFileira()));
                peca.casasLegais.add(Tabuleiro.getCasa(COLUNA_D, super.getFileira()));
                return true;
            }
            return false;
        }
    }

    public boolean canCastleToTheRight() {
        Peca peca = Tabuleiro.getCasa(COLUNA_H, super.getFileira()).getPeca(); // cria uma instância de peca na coluna H
        if (this.isInCheck() || super.jaMoveu || (peca instanceof Torre && peca.jaMoveu())) {
            return false;
        } else {
            if (!isPathClear(super.getColuna() + 1, COLUNA_G, 1)) {
                return false;
            }

            if (peca instanceof Torre && peca.getCor() == super.getCor()) { // se houver peça na casa, for uma torre da
                                                                            // mesma cor do rei, pode rocar.
                super.casasLegais.add(Tabuleiro.getCasa(COLUNA_G, super.getFileira()));
                peca.casasLegais.add(Tabuleiro.getCasa(COLUNA_F, super.getFileira()));
                return true;
            }
            return false;
        }
    }

    private boolean isPathClear(int startCol, int endCol, int step) {
        for (int idColuna = startCol; (step > 0 ? idColuna <= endCol : idColuna >= endCol); idColuna += step) {
            Casa casaNaFileira = Tabuleiro.getCasa(idColuna, super.getFileira()); // armazena cada casa para checagem
            if (casaNaFileira.getPeca() != null) {
                return false; // se a casa não está nula, não pode rocar
            }
        }
        return true;
    }

    public void cravaPecas() {
        int corCorrespondente = super.getCor();
        int contadorDireita = 0;
        int contadorEsquerda = 0;
        int contadorBaixo = 0;
        int contadorCima = 0;
        int contadorDireitaCima = 0;
        int contadorEsquerdaCima = 0;
        int contadorDireitaBaixo = 0;
        int contadorEsquerdaBaixo = 0;
        Peca pecaPossivelmenteCravadaDireita = null;
        Peca pecaPossivelmenteCravadaEsquerda = null;
        Peca pecaPossivelmenteCravadaBaixo = null;
        Peca pecaPossivelmenteCravadaCima = null;
        Peca pecaPossivelmenteCravadaDireitaCima = null;
        Peca pecaPossivelmenteCravadaEsquerdaCima = null;
        Peca pecaPossivelmenteCravadaDireitaBaixo = null;
        Peca pecaPossivelmenteCravadaEsquerdaBaixo = null;
        ArrayList<Casa> possiveisCasasLegaisDireita = new ArrayList<>(6);
        ArrayList<Casa> possiveisCasasLegaisEsquerda = new ArrayList<>(6);
        ArrayList<Casa> possiveisCasasLegaisBaixo = new ArrayList<>(6);
        ArrayList<Casa> possiveisCasasLegaisCima = new ArrayList<>(6);
        ArrayList<Casa> possiveisCasasLegaisDireitaCima = new ArrayList<>(6);
        ArrayList<Casa> possiveisCasasLegaisEsquerdaCima = new ArrayList<>(6);
        ArrayList<Casa> possiveisCasasLegaisDireitaBaixo = new ArrayList<>(6);
        ArrayList<Casa> possiveisCasasLegaisEsquerdaBaixo = new ArrayList<>(6);

        // RayCasting para direita
        for (int idColuna = super.getColuna() + 1; idColuna < COLUNAS; idColuna++) {
            Casa casaNaFileira = Tabuleiro.getCasa(idColuna, super.getFileira()); // Casas que estão na mesma fileira.

            Peca pecaNaFileira = casaNaFileira.getPeca(); // Peças que estão (ou não) nessas casas.

            if (pecaNaFileira != null) {
                if (pecaNaFileira.getCor() == corCorrespondente) {

                    if (contadorDireita == 0) {
                        pecaPossivelmenteCravadaDireita = pecaNaFileira; // se for a primeira peça da sua cor, ela PODE
                                                                         // estar cravada
                    }
                    contadorDireita++;

                } else if (pecaNaFileira.getCor() != corCorrespondente) {

                    if (contadorDireita == 0) {
                        break; // não há peças cravadas
                    } else {
                        if (contadorDireita == 1) {
                            if (pecaNaFileira instanceof Torre || pecaNaFileira instanceof Rainha) { // Pecas inimigas
                                                                                                     // que podem cravar
                                                                                                     // horizontalmente.
                                if (pecaPossivelmenteCravadaDireita != null) {
                                    pecaPossivelmenteCravadaDireita.setEstaCravada(true); // sua peça está cravada
                                    if (pecaPossivelmenteCravadaDireita instanceof Torre
                                            || pecaPossivelmenteCravadaDireita instanceof Rainha) {
                                        pecaPossivelmenteCravadaDireita.casasLegais.clear(); // da clear nas casas
                                                                                             // legais
                                        pecaPossivelmenteCravadaDireita.casasLegais.addAll(possiveisCasasLegaisDireita); // adiciona
                                        // as casas vazias na mesma fileira
                                        pecaPossivelmenteCravadaDireita.casasLegais.add(casaNaFileira); // adiciona a
                                                                                                        // casa da peça
                                                                                                        // atacante
                                    } else {
                                        pecaPossivelmenteCravadaDireita.casasLegais.clear(); // da clear nas casas
                                                                                             // legais.
                                                                                             // A peça não pode se
                                                                                             // mover.
                                    }
                                }
                            }
                            break;
                        } else {
                            if (pecaPossivelmenteCravadaDireita != null) {
                                pecaPossivelmenteCravadaDireita.setEstaCravada(false); // tem duas peças da sua cor
                                                                                       // antes de uma adversária
                                break;
                                // portanto não está cravada
                            }
                        }
                    }
                }
            } else {
                possiveisCasasLegaisDireita.add(casaNaFileira);
            }
        }

        // RayCasting para esquerda
        for (int idColuna = super.getColuna() - 1; idColuna >= 0; idColuna--) {
            Casa casaNaFileira = Tabuleiro.getCasa(idColuna, super.getFileira()); // Casas que estão na mesma fileira.

            Peca pecaNaFileira = casaNaFileira.getPeca(); // Peças que estão (ou não) nessas casas.

            if (pecaNaFileira != null) {
                if (pecaNaFileira.getCor() == corCorrespondente) {

                    if (contadorEsquerda == 0) {
                        pecaPossivelmenteCravadaEsquerda = pecaNaFileira; // se for a primeira peça da sua cor, ela PODE
                                                                          // estar cravada
                    }
                    contadorEsquerda++;

                } else if (pecaNaFileira.getCor() != corCorrespondente) {

                    if (contadorEsquerda == 0) {
                        break; // não há peças cravadas
                    } else {
                        if (contadorEsquerda == 1) {
                            if (pecaNaFileira instanceof Torre || pecaNaFileira instanceof Rainha) { // Pecas inimigas
                                                                                                     // que podem cravar
                                                                                                     // horizontalmente.
                                if (pecaPossivelmenteCravadaEsquerda != null) {
                                    pecaPossivelmenteCravadaEsquerda.setEstaCravada(true); // sua peça está cravada
                                    if (pecaPossivelmenteCravadaEsquerda instanceof Torre
                                            || pecaPossivelmenteCravadaEsquerda instanceof Rainha) {
                                        pecaPossivelmenteCravadaEsquerda.casasLegais.clear(); // da clear nas casas
                                                                                              // legais
                                        pecaPossivelmenteCravadaEsquerda.casasLegais
                                                .addAll(possiveisCasasLegaisEsquerda); // adiciona as casas vazias na
                                                                                       // mesma fileira
                                        pecaPossivelmenteCravadaEsquerda.casasLegais.add(casaNaFileira); // adiciona a
                                                                                                         // casa da peça
                                                                                                         // atacante
                                    } else {
                                        pecaPossivelmenteCravadaEsquerda.casasLegais.clear(); // da clear nas casas
                                                                                              // legais. A peça não pode
                                                                                              // se mover
                                    }
                                }
                            }
                            break;
                        } else {
                            if (pecaPossivelmenteCravadaEsquerda != null) {
                                pecaPossivelmenteCravadaEsquerda.setEstaCravada(false); // tem duas peças da sua cor
                                                                                        // antes de uma adversária
                                break;
                                // portanto não está cravada
                            }
                        }
                    }
                }
            } else {
                possiveisCasasLegaisEsquerda.add(casaNaFileira);
            }
        }

        // RayCasting para cima
        for (int idFileira = super.getFileira() + 1; idFileira < FILEIRAS; idFileira++) {
            Casa casaNaColuna = Tabuleiro.getCasa(super.getColuna(), idFileira); // Casas que estão na mesma coluna.

            Peca pecaNaColuna = casaNaColuna.getPeca(); // Peças que estão (ou não) nessas casas.

            if (pecaNaColuna != null) {
                if (pecaNaColuna.getCor() == corCorrespondente) {

                    if (contadorCima == 0) {
                        pecaPossivelmenteCravadaCima = pecaNaColuna; // se for a primeira peça da sua cor, ela PODE
                                                                     // estar cravada
                    }
                    contadorCima++;

                } else if (pecaNaColuna.getCor() != corCorrespondente) {

                    if (contadorCima == 0) {
                        break; // não há peças cravadas
                    } else {
                        if (contadorCima == 1) {
                            if (pecaNaColuna instanceof Torre || pecaNaColuna instanceof Rainha) { // Pecas inimigas que
                                                                                                   // podem cravar
                                                                                                   // horizontalmente.
                                if (pecaPossivelmenteCravadaCima != null) {
                                    pecaPossivelmenteCravadaCima.setEstaCravada(true); // sua peça está cravada
                                    if (pecaPossivelmenteCravadaCima instanceof Torre
                                            || pecaPossivelmenteCravadaCima instanceof Rainha) {
                                        pecaPossivelmenteCravadaCima.casasLegais.clear(); // da clear nas casas legais
                                        pecaPossivelmenteCravadaCima.casasLegais.addAll(possiveisCasasLegaisCima); // adiciona
                                                                                                                   // as
                                                                                                                   // casas
                                                                                                                   // vazias
                                                                                                                   // na
                                                                                                                   // mesma
                                                                                                                   // coluna
                                        pecaPossivelmenteCravadaCima.casasLegais.add(casaNaColuna); // adiciona a casa
                                                                                                    // da peça atacante
                                    } else {
                                        pecaPossivelmenteCravadaCima.casasLegais.clear(); // da clear nas casas legais.
                                                                                          // A peça não pode se mover
                                    }
                                }
                            }
                            break;
                        } else {
                            if (pecaPossivelmenteCravadaCima != null) {
                                pecaPossivelmenteCravadaCima.setEstaCravada(false); // tem duas peças da sua cor antes
                                                                                    // de uma adversária
                                break;
                                // portanto não está cravada
                            }
                        }
                    }
                }
            } else {
                possiveisCasasLegaisCima.add(casaNaColuna);
            }
        }

        // RayCasting para baixo
        for (int idFileira = super.getFileira() - 1; idFileira >= 0; idFileira--) {
            Casa casaNaColuna = Tabuleiro.getCasa(super.getColuna(), idFileira); // Casas que estão na mesma coluna.

            Peca pecaNaColuna = casaNaColuna.getPeca(); // Peças que estão (ou não) nessas casas.

            if (pecaNaColuna != null) {
                if (pecaNaColuna.getCor() == corCorrespondente) {

                    if (contadorBaixo == 0) {
                        pecaPossivelmenteCravadaBaixo = pecaNaColuna; // se for a primeira peça da sua cor, ela PODE
                                                                      // estar cravada
                    }
                    contadorBaixo++;

                } else if (pecaNaColuna.getCor() != corCorrespondente) {

                    if (contadorBaixo == 0) {
                        break; // não há peças cravadas
                    } else {
                        if (contadorBaixo == 1) {
                            if (pecaNaColuna instanceof Torre || pecaNaColuna instanceof Rainha) { // Pecas inimigas que
                                                                                                   // podem cravar
                                                                                                   // horizontalmente.
                                if (pecaPossivelmenteCravadaBaixo != null) {
                                    pecaPossivelmenteCravadaBaixo.setEstaCravada(true); // sua peça está cravada
                                    if (pecaPossivelmenteCravadaBaixo instanceof Torre
                                            || pecaPossivelmenteCravadaBaixo instanceof Rainha) {
                                        pecaPossivelmenteCravadaBaixo.casasLegais.clear(); // da clear nas casas legais
                                        pecaPossivelmenteCravadaBaixo.casasLegais.addAll(possiveisCasasLegaisBaixo); // adiciona
                                                                                                                     // as
                                                                                                                     // casas
                                                                                                                     // vazias
                                                                                                                     // na
                                                                                                                     // mesma
                                                                                                                     // coluna
                                        pecaPossivelmenteCravadaBaixo.casasLegais.add(casaNaColuna); // adiciona a casa
                                                                                                     // da peça atacante
                                    } else {
                                        pecaPossivelmenteCravadaBaixo.casasLegais.clear(); // da clear nas casas legais.
                                                                                           // A peça não pode se mover
                                    }
                                }
                            }
                            break;
                        } else {
                            if (pecaPossivelmenteCravadaBaixo != null) {
                                pecaPossivelmenteCravadaBaixo.setEstaCravada(false); // tem duas peças da sua cor antes
                                                                                     // de uma adversária
                                break;
                                // portanto não está cravada
                            }
                        }
                    }
                }
            } else {
                possiveisCasasLegaisBaixo.add(casaNaColuna);
            }
        }

        // RayCasting diagonal direita superior
        int idColuna = super.getColuna() + 1;
        int idFileira = super.getFileira() + 1;

        for (; ((idColuna < COLUNAS) && (idFileira < FILEIRAS)); idColuna++, idFileira++) {
            Casa casaNaDiagonal = Tabuleiro.getCasa(idColuna, idFileira); // Casas que estão na mesma diagonal.

            Peca pecaNaDiagonal = casaNaDiagonal.getPeca(); // Peças que estão (ou não) nessas casas.

            if (pecaNaDiagonal != null) {
                if (pecaNaDiagonal.getCor() == corCorrespondente) {

                    if (contadorDireitaCima == 0) {
                        pecaPossivelmenteCravadaDireitaCima = pecaNaDiagonal; // se for a primeira peça da sua cor, ela
                                                                              // PODE estar cravada
                    }
                    contadorDireitaCima++;

                } else if (pecaNaDiagonal.getCor() != corCorrespondente) {

                    if (contadorDireitaCima == 0) {
                        break; // não há peças cravadas
                    } else {
                        if (contadorDireitaCima == 1) {
                            if (pecaNaDiagonal instanceof Bispo || pecaNaDiagonal instanceof Rainha) { // Pecas inimigas
                                                                                                       // que podem
                                                                                                       // cravar
                                                                                                       // horizontalmente.
                                if (pecaPossivelmenteCravadaDireitaCima != null) {
                                    pecaPossivelmenteCravadaDireitaCima.setEstaCravada(true); // sua peça está cravada
                                    if (pecaPossivelmenteCravadaDireitaCima instanceof Bispo
                                            || pecaPossivelmenteCravadaDireitaCima instanceof Rainha) {
                                        pecaPossivelmenteCravadaDireitaCima.casasLegais.clear(); // da clear nas casas
                                                                                                 // legais
                                        pecaPossivelmenteCravadaDireitaCima.casasLegais
                                                .addAll(possiveisCasasLegaisDireitaCima); // adiciona as casas vazias na
                                                                                          // mesma fileira
                                        pecaPossivelmenteCravadaDireitaCima.casasLegais.add(casaNaDiagonal); // adiciona
                                                                                                             // a casa
                                                                                                             // da peça
                                                                                                             // atacante
                                    } else {
                                        pecaPossivelmenteCravadaDireitaCima.casasLegais.clear(); // da clear nas casas
                                                                                                 // legais. A peça não
                                                                                                 // pode se mover
                                    }
                                }
                            }
                            break;
                        } else {
                            if (pecaPossivelmenteCravadaDireitaCima != null) {
                                pecaPossivelmenteCravadaDireitaCima.setEstaCravada(false); // tem duas peças da sua cor
                                                                                           // antes de uma adversária
                                break;
                                // portanto não está cravada
                            }
                        }
                    }
                }
            } else {
                possiveisCasasLegaisDireitaCima.add(casaNaDiagonal);
            }
        }

        // RayCasting esquerda superior
        idColuna = super.getColuna() - 1;
        idFileira = super.getFileira() + 1;

        for (; ((idColuna >= 0) && (idFileira < FILEIRAS)); idColuna--, idFileira++) {
            Casa casaNaDiagonal = Tabuleiro.getCasa(idColuna, idFileira); // Casas que estão na mesma diagonal.

            Peca pecaNaDiagonal = casaNaDiagonal.getPeca(); // Peças que estão (ou não) nessas casas.

            if (pecaNaDiagonal != null) {
                if (pecaNaDiagonal.getCor() == corCorrespondente) {

                    if (contadorEsquerdaCima == 0) {
                        pecaPossivelmenteCravadaEsquerdaCima = pecaNaDiagonal; // se for a primeira peça da sua cor, ela
                                                                               // PODE estar cravada
                    }
                    contadorEsquerdaCima++;

                } else if (pecaNaDiagonal.getCor() != corCorrespondente) {

                    if (contadorEsquerdaCima == 0) {
                        break; // não há peças cravadas
                    } else {
                        if (contadorEsquerdaCima == 1) {
                            if (pecaNaDiagonal instanceof Bispo || pecaNaDiagonal instanceof Rainha) { // Pecas inimigas
                                                                                                       // que podem
                                                                                                       // cravar
                                                                                                       // horizontalmente.
                                if (pecaPossivelmenteCravadaEsquerdaCima != null) {
                                    pecaPossivelmenteCravadaEsquerdaCima.setEstaCravada(true); // sua peça está cravada
                                    if (pecaPossivelmenteCravadaEsquerdaCima instanceof Bispo
                                            || pecaPossivelmenteCravadaEsquerdaCima instanceof Rainha) {
                                        pecaPossivelmenteCravadaEsquerdaCima.casasLegais.clear(); // da clear nas casas
                                                                                                  // legais
                                        pecaPossivelmenteCravadaEsquerdaCima.casasLegais
                                                .addAll(possiveisCasasLegaisEsquerdaCima); // adiciona as casas vazias
                                                                                           // na mesma fileira
                                        pecaPossivelmenteCravadaEsquerdaCima.casasLegais.add(casaNaDiagonal); // adiciona
                                                                                                              // a casa
                                                                                                              // da peça
                                                                                                              // atacante
                                    } else {
                                        pecaPossivelmenteCravadaEsquerdaCima.casasLegais.clear(); // da clear nas casas
                                                                                                  // legais. A peça não
                                                                                                  // pode se mover
                                    }
                                }
                            }
                            break;
                        } else {
                            if (pecaPossivelmenteCravadaEsquerdaCima != null) {
                                pecaPossivelmenteCravadaEsquerdaCima.setEstaCravada(false); // tem duas peças da sua cor
                                                                                            // antes de uma adversária
                                break;
                                // portanto não está cravada
                            }
                        }
                    }
                }
            } else {
                possiveisCasasLegaisEsquerdaCima.add(casaNaDiagonal);
            }
        }

        // RayCasting direita inferior
        idColuna = super.getColuna() + 1;
        idFileira = super.getFileira() - 1;

        for (; ((idColuna < COLUNAS) && (idFileira >= 0)); idColuna++, idFileira--) {
            Casa casaNaDiagonal = Tabuleiro.getCasa(idColuna, idFileira); // Casas que estão na mesma diagonal.

            Peca pecaNaDiagonal = casaNaDiagonal.getPeca(); // Peças que estão (ou não) nessas casas.

            if (pecaNaDiagonal != null) {
                if (pecaNaDiagonal.getCor() == corCorrespondente) {

                    if (contadorDireitaBaixo == 0) {
                        pecaPossivelmenteCravadaDireitaBaixo = pecaNaDiagonal; // se for a primeira peça da sua cor, ela
                                                                               // PODE estar cravada
                    }
                    contadorDireitaBaixo++;

                } else if (pecaNaDiagonal.getCor() != corCorrespondente) {

                    if (contadorDireitaBaixo == 0) {
                        break; // não há peças cravadas
                    } else {
                        if (contadorDireitaBaixo == 1) {
                            if (pecaNaDiagonal instanceof Bispo || pecaNaDiagonal instanceof Rainha) { // Pecas inimigas
                                                                                                       // que podem
                                                                                                       // cravar
                                                                                                       // horizontalmente.
                                if (pecaPossivelmenteCravadaDireitaBaixo != null) {
                                    pecaPossivelmenteCravadaDireitaBaixo.setEstaCravada(true); // sua peça está cravada
                                    if (pecaPossivelmenteCravadaDireitaBaixo instanceof Bispo
                                            || pecaPossivelmenteCravadaDireitaBaixo instanceof Rainha) {
                                        pecaPossivelmenteCravadaDireitaBaixo.casasLegais.clear(); // da clear nas casas
                                                                                                  // legais
                                        pecaPossivelmenteCravadaDireitaBaixo.casasLegais
                                                .addAll(possiveisCasasLegaisDireitaBaixo); // adiciona as casas vazias
                                                                                           // na mesma fileira
                                        pecaPossivelmenteCravadaDireitaBaixo.casasLegais.add(casaNaDiagonal); // adiciona
                                                                                                              // a casa
                                                                                                              // da peça
                                                                                                              // atacante
                                    } else {
                                        pecaPossivelmenteCravadaDireitaBaixo.casasLegais.clear(); // da clear nas casas
                                                                                                  // legais. A peça não
                                                                                                  // pode se mover
                                    }
                                }
                            }
                            break;
                        } else {
                            if (pecaPossivelmenteCravadaDireitaBaixo != null) {
                                pecaPossivelmenteCravadaDireitaBaixo.setEstaCravada(false); // tem duas peças da sua cor
                                                                                            // antes de uma adversária
                                break;
                                // portanto não está cravada
                            }
                        }
                    }
                }
            } else {
                possiveisCasasLegaisDireitaBaixo.add(casaNaDiagonal);
            }
        }

        // RayCasting esquerda inferior
        idColuna = super.getColuna() - 1;
        idFileira = super.getFileira() - 1;

        for (; ((idColuna >= 0) && (idFileira >= 0)); idColuna--, idFileira--) {
            Casa casaNaDiagonal = Tabuleiro.getCasa(idColuna, idFileira); // Casas que estão na mesma diagonal.

            Peca pecaNaDiagonal = casaNaDiagonal.getPeca(); // Peças que estão (ou não) nessas casas.

            if (pecaNaDiagonal != null) {
                if (pecaNaDiagonal.getCor() == corCorrespondente) {

                    if (contadorEsquerdaBaixo == 0) {
                        pecaPossivelmenteCravadaEsquerdaBaixo = pecaNaDiagonal; // se for a primeira peça da sua cor,
                                                                                // ela PODE estar cravada
                    }
                    contadorEsquerdaBaixo++;

                } else if (pecaNaDiagonal.getCor() != corCorrespondente) {

                    if (contadorEsquerdaBaixo == 0) {
                        break; // não há peças cravadas
                    } else {
                        if (contadorEsquerdaBaixo == 1) {
                            if (pecaNaDiagonal instanceof Bispo || pecaNaDiagonal instanceof Rainha) { // Pecas inimigas
                                                                                                       // que podem
                                                                                                       // cravar
                                                                                                       // horizontalmente.
                                if (pecaPossivelmenteCravadaEsquerdaBaixo != null) {
                                    pecaPossivelmenteCravadaEsquerdaBaixo.setEstaCravada(true); // sua peça está cravada
                                    if (pecaPossivelmenteCravadaEsquerdaBaixo instanceof Bispo
                                            || pecaPossivelmenteCravadaEsquerdaBaixo instanceof Rainha) {
                                        pecaPossivelmenteCravadaEsquerdaBaixo.casasLegais.clear(); // da clear nas casas
                                                                                                   // legais
                                        pecaPossivelmenteCravadaEsquerdaBaixo.casasLegais
                                                .addAll(possiveisCasasLegaisEsquerdaBaixo); // adiciona as casas vazias
                                                                                            // na mesma fileira
                                        pecaPossivelmenteCravadaEsquerdaBaixo.casasLegais.add(casaNaDiagonal); // adiciona
                                                                                                               // a casa
                                                                                                               // da
                                                                                                               // peça
                                                                                                               // atacante
                                    } else {
                                        pecaPossivelmenteCravadaEsquerdaBaixo.casasLegais.clear(); // da clear nas casas
                                                                                                   // legais. A peça não
                                                                                                   // pode se mover
                                    }
                                }
                            }
                            break;
                        } else {
                            if (pecaPossivelmenteCravadaEsquerdaBaixo != null) {
                                pecaPossivelmenteCravadaEsquerdaBaixo.setEstaCravada(false); // tem duas peças da sua
                                                                                             // cor antes de uma
                                                                                             // adversária
                                break;
                                // portanto não está cravada
                            }
                        }
                    }
                }
            } else {
                possiveisCasasLegaisEsquerdaBaixo.add(casaNaDiagonal);
            }
        }
    }

    public int getPecasAtacantes() {
        return pecasAtacantes;
    }

    public void setPecasAtacantes(int pecasAtacantes) {
        this.pecasAtacantes = pecasAtacantes;
    }

    public void incPecasAtacantes() {
        this.pecasAtacantes++;
    }
}