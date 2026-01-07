import Tabuleiro.Casa;

import Tabuleiro.Tabuleiro;
import pecas.Peca;

import java.util.Scanner;

import static Tabuleiro.Casa.BY_BLACK;
import static Tabuleiro.Casa.BY_WHITE;
import static Tabuleiro.Tabuleiro.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        criarCasas();
        preencherCasasToString();
        lerFEN(FEN_POS_INICIAL); // en passant tb mostrar
        // lerFEN("r3k1nr/1p1ppppp/8/8/8/2Q5/PPPPPPPP/RN2K2R"); // exemplo roque
        // lerFEN("r7/6k1/5q2/8/1p4N1/1P6/1KP5/7r"); //Caso de cheque
        // lerFEN("4k3/3r4/6n1/7B/Q7/4q3/8/1KR4R"); // peças cravadas
        // lerFEN("3k4/3r1P2/6n1/7B/Q7/4q3/8/1KR2R2"); // promoção e xeque mate
        // lerFEN("7k/8/p3Q3/Pp5p/1P5P/8/1K6/8"); //Caso afogamento
        // lerFEN("6n1/Rn3k2/2r4b/5N2/5Q2/8/1K6/8"); //Caso de cheque duplo

        imprimirBranco();

        System.out.println("Digite 'ajuda' para saber mais sobre os comandos.");

        inicio: while (true) {
            int turno = getJogadas();

            clearCasasLegais(); // Esvazia as casas legais antes de analisar novamente.
            clearCasasDeBloqueio(); // Esvazia as casas de bloqueio antes de analisar novamente.
            clearPecasAtacantes(); // Zera a contagem das peças que estão atacando o rei.
            clearIsAtacked();

            refreshCasasLegais(); // Analisa as casas legais de todas as peças do tabuleiro.
            refreshIsInCheck(); // Verifica se os reis estão em cheque.
            refreshFiltroCasasLegais(); // Filtra a interseção entre casas legais e as casas de bloqueio.
            refreshCravaPecas();
            uniteCasasLegais();

            // Xequemate
            if (getReiBranco().isCheckmated()) {
                System.out.println("As pretas venceram por chequemate!");
                System.exit(0);
            }
            if (getReiPreto().isCheckmated()) {
                System.out.println("As brancas venceram por chequemate!");
                System.exit(0);
            }

            // Afogamento
            if (getReiBranco().isStalemate()) {
                System.out.println("Empate por afogamento.");
                System.exit(0);
            }
            if (getReiPreto().isStalemate()) {
                System.out.println("Empate por afogamento.");
                System.exit(0);
            }

            System.out.println("\nDigite a casa de origem: ");
            String origem;
            while (true) {
                origem = sc.nextLine();

                switch (origem) {
                    case "virar" -> {
                        virar();
                        continue inicio;
                    }
                    case "desistir" -> {
                        imprimirCorAtual();

                        if (Tabuleiro.getJogadas() % 2 == 0) {
                            System.out.println("As pretas venceram por desistência!");
                        } else {
                            System.out.println("As brancas venceram por desistência!");
                        }
                        System.exit(0);
                    }
                    case "ajuda" -> {
                        System.out.println(
                                "-------|Palavras-Chave|----------------------------------------------------------------------------------------------------");
                        System.out.println("I-   Digite 'virar' para virar o tabuleiro.");
                        System.out.println("II-  Digite 'lance' para saber em qual lance a partida está.");
                        System.out.println("III- Digite 'vez' para saber de quem é a vez.");
                        System.out.println("IV-  Digite 'tabuleiro' para ver o tabuleiro.");
                        System.out.println("V-   Digite 'brancas?' para ver os movimentos legais das peças brancas.");
                        System.out.println("VI-  Digite 'pretas?' para ver os movimentos legais das peças brancas.");
                        System.out.println("VII- Digite 'desistir' para abandonar a partida.");
                        System.out.println(
                                "---------------------------------------------------------------------------------------------------------------------------");
                        System.out.println(
                                "* Para mover uma peça digite a casa em que ela se encontra, e a casa desejada.");
                        System.out.println(
                                "* Para saber os movimentos legais de uma peça, digite a casa em que ela se encontra, pressione enter e depois digite '?'.");
                        System.out.println("  ex: Digite a casa de origem: a2");
                        System.out.println("      Digite a casa de destino: ?");
                        System.out.println(
                                "---------------------------------------------------------------------------------------------------------------------------");
                        System.out.println();

                        continue inicio;
                    }
                    case "lance" -> {
                        System.out.println("A partida está no lance " + ((Tabuleiro.getJogadas() / 2) + 1));

                        continue inicio;
                    }
                    case "vez" -> {
                        if (Tabuleiro.getJogadas() % 2 == 0) {
                            System.out.println("É a vez das brancas!");
                        } else {
                            System.out.println("É a vez das pretas!");
                        }

                        continue inicio;
                    }
                    case "tabuleiro" -> {
                        imprimirCorAtual();

                        continue inicio;
                    }
                    case "brancas?" -> {
                        System.out.print("Movimentos legais peças brancas: [");
                        for (Casa casa : casasLegaisPecasBrancas) {
                            System.out.print(casa.posString() + " ");
                        }
                        System.out.println("]");

                        continue inicio;
                    }
                    case "pretas?" -> {
                        System.out.print("Movimentos legais peças pretas: [");
                        for (Casa casa : casasLegaisPecasPretas) {
                            System.out.print(casa.posString() + " ");
                        }
                        System.out.println("]");

                        continue inicio;
                    }
                }

                if (casasToString.contains(origem)) {
                    break; // Só aceita a entrada se a casa de origem existir
                }
            }

            int colOrigem = Tabuleiro.parseNotationColuna(origem);
            int filOrigem = Tabuleiro.parseNotationFileira(origem);

            System.out.println("\nDigite a casa de destino: ");
            String destino;
            while (true) {
                destino = sc.nextLine();
                Casa casaOrigem = Tabuleiro.getCasa(colOrigem, filOrigem);
                Peca pecaCasaOrigem = casaOrigem.getPeca();
                if (destino.equalsIgnoreCase("?")) {

                    if (pecaCasaOrigem != null) {
                        System.out.println(
                                "[" + pecaCasaOrigem.getTipo() + casaOrigem.posString() + "]" + " Movimentos legais: ");

                        for (Casa casa : pecaCasaOrigem.getCasasLegais()) {
                            System.out.print(casa.posString() + " ");
                        }
                        System.out.println();

                        continue inicio;
                    } else {
                        System.out.println("Não existe peças nessa casa!");

                        continue inicio;
                    }
                } else if (destino.equalsIgnoreCase("atacada?")) {

                    if (casaOrigem.isAtacked().contains(BY_WHITE)) {
                        System.out.println(casaOrigem.posString() + " está sendo atacada por uma peça branca!");
                    }
                    if (casaOrigem.isAtacked().contains(BY_BLACK)) {
                        System.out.println(casaOrigem.posString() + " está sendo atacada por uma peça preta!");
                    }
                    if (!casaOrigem.isAtacked().contains(BY_WHITE) && !casaOrigem.isAtacked().contains(BY_BLACK)) {
                        System.out.println(casaOrigem.posString() + " não está sendo atacada por ninguém!");
                    }
                    continue inicio;
                }

                if (casasToString.contains(destino)) {
                    break;
                }
            }

            int colDestino = Tabuleiro.parseNotationColuna(destino);
            int filDestino = Tabuleiro.parseNotationFileira(destino);

            if (Tabuleiro.getCasa(colOrigem, filOrigem).getPeca() != null) {

                if (Tabuleiro.getJogadas() % 2 == 0) {
                    int corDaPeca = Tabuleiro.getCasa(colOrigem, filOrigem).getPeca().getCor();

                    if (corDaPeca == PRETO) {
                        System.out.println("Vez do branco");
                        imprimirCorAtual();
                        continue;
                    }
                } else {
                    int corDaPeca = Tabuleiro.getCasa(colOrigem, filOrigem).getPeca().getCor();

                    if (corDaPeca == BRANCO) {
                        System.out.println("Vez do preto");
                        imprimirCorAtual();
                        continue;
                    }
                }

                Tabuleiro.moverPeca(colOrigem, filOrigem, colDestino, filDestino);
            } else {
                System.out.println("Não existe nenhuma peça nesta casa!");
                imprimirCorAtual();
            }
        }
    }
}