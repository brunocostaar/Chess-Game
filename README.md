# ♟️ Jogo de Xadrez em Java

-----

### [🇬🇧/🇺🇸 English Version](https://github.com/xopxee/Chess-Game/blob/main/README_en.md)

-----

## 📜 Sumário

* Funcionalidades
* Tecnologias Utilizadas
* Como Executar o Projeto
* Estrutura do Projeto
* Licença

-----

## ✨ Funcionalidades

* **Estrutura de Tabuleiro:** Representação completa de um tabuleiro de xadrez 8x8.
* **Design Orientado a Objetos:** Cada peça (Peão, Torre, Cavalo, Bispo, Rainha, Rei) é modelada como uma classe distinta, herdando de uma classe base `Peca`, o que promove a reutilização e manutenção do código.
* **Movimentação Completa:** Lógica de movimento e captura para todas as peças, de acordo com suas regras fundamentais.
* **Regras Complexas:** O jogo conta com regras avançadas como en passant, roque, xeque, peças cravadas.
* **Condições de Vitória ou Empate:** Xeque-Mate, Empate por afogamento, Derrota por desistência.
* **Interface de Console (Se desejar):** Exibição do estado atual do tabuleiro no terminal para visualização do jogo.
* **Interface Gráfica (GUI):** Interface de usuário visualmente interativa utilizando a biblioteca JavaFX.


## 🛠️ Tecnologias Utilizadas

Linguagem: Java

Plataforma: JDK (Java Development Kit) 11 ou superior

Framework de UI : JavaFX

Gestor de Dependências: Maven (Apenas para utilizar o JavaFX)

## 🚀 Como Executar o Projeto

Para compilar e executar este projeto localmente, siga os passos abaixo.

### Pré-requisitos

Antes de começar, certifique-se de que você tem o **Java Development Kit (JDK)** (versão 11 ou mais recente) instalado em sua máquina.

### Passos para Execução

1.  **Clone o repositório:**

    ```bash
    git clone https://github.com/xopxee/Chess-Game.git
    ```

2.  **Navegue até o diretório do projeto:**

    ```bash
    cd Chess-Game
    ```

3.  **Compile os arquivos `.java` a partir da raiz do projeto:**
    O comando abaixo compilará todas as classes necessárias, respeitando a estrutura de pacotes.

    ```bash
    javac src/**/*.java
    ```

4.  **Execute a aplicação:**
    Após a compilação bem-sucedida, execute a classe principal para iniciar o jogo no console.

    ```bash
    java src/Main
    ```

## 📂 Estrutura do Projeto

O código-fonte está organizado de forma a separar as responsabilidades, facilitando o entendimento e a manutenção.

```
Chess-Game/
├── .idea/
├── src/
│   ├── gui/
│   │   ├── chess-gui.css
│   │   ├── ChessGUI.java
│   │   └── GuiLauncher.java
│   ├── Tabuleiro/
│   │   ├── Casa.java
│   │   └── Tabuleiro.java
│   ├── pecas/
│   │   ├── Bispo.java
│   │   ├── Cavalo.java
│   │   ├── Peao.java
│   │   ├── Peca.java
│   │   ├── Rainha.java
│   │   ├── Rei.java
│   │   └── Torre.java
│   └── Main.java
├── .gitignore
├── ChessGame.iml
├── LICENSE
├── pom.xml
├── README.md
└── README_en.md
```

### [📂Diagrama UML(Datado, será atualizado em breve)](https://lucid.app/lucidchart/95e617d8-9ed0-4962-9897-b22b88b38569/edit?beaconFlowId=1853CEFB36C7CF9A&invitationId=inv_1e47aab7-1f42-41f4-a381-2b2e6b5ff430&page=HWEp-vi-RSFO#)

## 📄 Licença

Este projeto está licenciado sob a [Licença MIT](https://en.wikipedia.org/wiki/MIT_License). Veja o arquivo para mais detalhes.

-----
