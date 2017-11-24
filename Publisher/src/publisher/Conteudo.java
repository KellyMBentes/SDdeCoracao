/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publisher;

import publisher.Sleeper;

/**
 *
 * @author Felipe
 */
public class Conteudo {

    static boolean chooseA[] = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
    static boolean chooseB[] = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
    static boolean chooseC[] = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};

    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    Conteudo() {

    }

    public String getConteudoA() {
        int escolheA;
        do {
            escolheA = Sleeper.random_int(0, 15);
        } while (!chooseA[escolheA]);
        String vet[] = {"À margem da vida", "À meia luz", "A um passo da eternidade", "Acerto Final", "Achados e Perdidos",
            "Adivinhe Quem Vem Para Roubar", "Admiradora Secreta", "Adoráveis Mulheres", "Alguém Muito Especial",
            "Alguém para Amar", "Alguém Tem Que Ceder", "Alto Risco", "Alucinação", "Alucinações do Passado", "Alvin e os Esquilos"};
        chooseA[escolheA] = false;
        return vet[escolheA];
    }

    public String getConteudoB() {
        int escolheB;
        do {
            escolheB = Sleeper.random_int(0, 15);
        } while (!chooseB[escolheB]);
        String vet[] = {"Beleza Americana", "Beleza Roubada", "Bella Mafia", "Bellini e a Esfinge", "Bellini e o Demônio",
            "Bem-Amada", "Bem Me Quer, Mal Me Quer", "Bem-vindo à Casa de Bonecas", "Bem-vindo à Selva", "Bem-vindos ao Paraíso",
            "Ben-Hur", "Bendito Fruto", "Benjamim", "Brigada 49", "Brilhante"};
        chooseB[escolheB] = false;
        return vet[escolheB];
    }

    public String getConteudoC() {
        int escolheC;
        do {
            escolheC = Sleeper.random_int(0, 15);
        } while (!chooseC[escolheC]);
        String vet[] = {"Cenas de um Casamento", "Cenas de uma Família", "Censura Máxima", "Ciladas da Sorte", "Cinco Covas no Egito",
            "Cinco Dias Antes da Morte", "Cinco Dias de Conspiração", "Cinco Evas e um Adão", "Código de Ataque", "Código Desconhecido",
            "Código para o Inferno", "Comportamento Suspeito", "Con Air - A Rota da Fuga", "Copacabana", "Copenhagen"};
        chooseC[escolheC] = false;
        return vet[escolheC];

    }

    public boolean checkAvailabilityOne(boolean vet1[]) {
        for (int i = 0; i < vet1.length; i++) {
            if (vet1[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean checkAvailability(boolean vet1[], boolean vet2[], boolean vet3[]) {
        for (int i = 0; i < vet1.length; i++) {
            if (vet1[i]) {
                return true;
            }
        }
        for (int i = 0; i < vet2.length; i++) {
            if (vet2[i]) {
                return true;
            }
        }
        for (int i = 0; i < vet3.length; i++) {
            if (vet3[i]) {
                return true;
            }
        }
        return false;
    }

    public void printVetor() {
        System.out.println("Vetor A = {" + chooseA[0] + "," + chooseA[1] + "," + chooseA[2] + "," + chooseA[3] + "," + chooseA[4] + "," + chooseA[5] + "," + chooseA[6] + "," + chooseA[7] + "," + chooseA[8] + "," + chooseA[9] + "," + chooseA[10] + "," + chooseA[11] + "," + chooseA[12] + "," + chooseA[13] + "," + chooseA[14] + "}");
        System.out.println("Vetor B = {" + chooseB[0] + "," + chooseB[1] + "," + chooseB[2] + "," + chooseB[3] + "," + chooseB[4] + "," + chooseB[5] + "," + chooseB[6] + "," + chooseB[7] + "," + chooseB[8] + "," + chooseB[9] + "," + chooseB[10] + "," + chooseB[11] + "," + chooseB[12] + "," + chooseB[13] + "," + chooseB[14] + "}");
        System.out.println("Vetor C = {" + chooseC[0] + "," + chooseC[1] + "," + chooseC[2] + "," + chooseC[3] + "," + chooseC[4] + "," + chooseC[5] + "," + chooseC[6] + "," + chooseC[7] + "," + chooseC[8] + "," + chooseC[9] + "," + chooseC[10] + "," + chooseC[11] + "," + chooseC[12] + "," + chooseC[13] + "," + chooseC[14] + "}");

    }
}
