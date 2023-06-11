package com.mycompany.trabalhopaulinho;

import com.mycompany.trabalhopaulinho.models.ContaDoBanco;
import javax.swing.JOptionPane;

public class BancoUniparPaulinho {

    private static ContaDoBanco[] contas;
    private static int totalContas;

    public static void main(String[] args) {
        contas = new ContaDoBanco[100];
        totalContas = 0;

        boolean encerrar = false;

        while (!encerrar) {
            String opcoes = "=== Sistema de Conta Bancária ===\n"
                    + "1. Cadastrar nova conta bancária\n"
                    + "2. Exibir contas bancárias ordenadas\n"
                    + "3. Realizar depósito\n"
                    + "4. Realizar saque\n"
                    + "5. Calcular saldo total do banco\n"
                    + "6. Verificar saldo negativo\n"
                    + "0. Sair";

            String opcaoStr = JOptionPane.showInputDialog(opcoes + "\n\nEscolha uma opção:");
            int opcao = Integer.parseInt(opcaoStr);

            switch (opcao) {
                case 1:
                    cadastroDeContas();
                    break;
                case 2:
                    ordenarContas();
                    break;
                case 3:
                    realizarDeposito();
                    break;
                case 4:
                    realizarSaque();
                    break;
                case 5:
                    calcularSaldoTotal();
                    break;
                case 6:
                    verificacao();
                    break;
                case 0:
                    encerrar = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida! Tente novamente.");
            }
        }
    }

    public static void cadastroDeContas() {
        int maxContas = 100;
        if (totalContas < maxContas) {
            int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Insira o número da conta:"));

            String nomeDono = JOptionPane.showInputDialog("Insira o nome do titular:");

            double saldoInicial = Double.parseDouble(JOptionPane.showInputDialog("Insira o saldo inicial:"));

            ContaDoBanco conta = new ContaDoBanco(nomeDono, numeroConta, saldoInicial);

            contas[totalContas] = conta;

            totalContas++;
        } else {
            JOptionPane.showMessageDialog(null, "Você excedeu o número máximo de contas.");
        }
    }

    public static void ordenarContas() {
        String mensagem = "=== Exibir contas bancárias ordenadas ===\n"
                + "1. Por número da conta\n"
                + "2. Por saldo\n"
                + "3. Por nome do titular";

        int opcao = Integer.parseInt(JOptionPane.showInputDialog(mensagem + "\nEscolha um método de ordenação:"));

        switch (opcao) {
            case 1:
                ordenarContasPorNumeroConta(contas);
                exibirContas();
                break;
            case 2:
                ordenarContasPorSaldo(contas);
                exibirContas();
                break;
            case 3:
                ordenarContasPorTitular(contas);
                exibirContas();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida! ");
        }
    }

    public static void exibirContas() {
        String resultado = "=== Contas Bancárias ===\n";
        for (int i = 0; i < totalContas; i++) {
            ContaDoBanco conta = contas[i];
            resultado += "Número da Conta: " + conta.getNrmConta() + "\n";
            resultado += "Titular: " + conta.getNomeDono() + "\n";
            resultado += "Saldo: R$" + conta.getSaldoAtual() + "\n\n";
        }
        JOptionPane.showMessageDialog(null, resultado);
    }

    public static void verificacao() {
        verificarSaldoNegativo(0);
    }

    public static void verificarSaldoNegativo(int i) {
        if (i >= totalContas) {
            return;
        }

        ContaDoBanco conta = contas[i];
        if (conta.getSaldoAtual() < 0) {
            JOptionPane.showMessageDialog(null, "O saldo da conta " + conta.getNrmConta() + " está negativado.");
        } else {
            JOptionPane.showMessageDialog(null, "Não há saldo negativo na conta " + conta.getNrmConta() + ".");
        }

        verificarSaldoNegativo(i + 1);
    }

    public static void realizarDeposito() {
        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Informe o número da conta:"));
        int i = buscaBinaria(numeroConta);
        if (i != -1) {
            ContaDoBanco conta = contas[i];

            double valorDeposito = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do depósito:"));
            conta.setSaldoAtual(conta.getSaldoAtual() + valorDeposito);
            JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Conta não encontrada.");
        }
    }

    public static int buscaBinaria(int numeroConta) {
        int inicio = 0;
        int fim = totalContas - 1;

        while (inicio <= fim) {
            int meio = inicio + (fim - inicio) / 2;
            if (contas[meio].getNrmConta() == numeroConta) {
                return meio;
            }
            if (contas[meio].getNrmConta() < numeroConta) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }
        return -1;
    }

    public static void realizarSaque() {
        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Informe o número da conta:"));
        int i = buscaBinaria(numeroConta);
        if (i != -1) {
            ContaDoBanco conta = contas[i];
            double valorSaque = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do saque:"));
            if (conta.getSaldoAtual() >= valorSaque) {
                conta.setSaldoAtual(conta.getSaldoAtual() - valorSaque);
                JOptionPane.showMessageDialog(null, "Saque realizado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente. Saque não realizado.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Conta não encontrada. Saque não realizado.");
        }
    }

    public static void calcularSaldoTotal() {
        double saldoTotal = 0;
        for (int i = 0; i < totalContas; i++) {
            saldoTotal += contas[i].getSaldoAtual();
        }
        JOptionPane.showMessageDialog(null, "Saldo total do banco: R$" + saldoTotal);
    }

    public static void ordenarContasPorSaldo(ContaDoBanco[] contas) {
        for (int i = 0; i < totalContas - 1; i++) {
            for (int j = 0; j < totalContas - i - 1; j++) {
                if (contas[j].getSaldoAtual() > contas[j + 1].getSaldoAtual()) {
                    ContaDoBanco temp = contas[j];
                    contas[j] = contas[j + 1];
                    contas[j + 1] = temp;
                }
            }
        }
    }

    public static void ordenarContasPorNumeroConta(ContaDoBanco[] contas) {
        for (int i = 0; i < totalContas - 1; i++) {
            for (int j = 0; j < totalContas - i - 1; j++) {
                if (contas[j].getNrmConta() > contas[j + 1].getNrmConta()) {
                    ContaDoBanco temp = contas[j];
                    contas[j] = contas[j + 1];
                    contas[j + 1] = temp;
                }
            }
        }
    }

    public static void ordenarContasPorTitular(ContaDoBanco[] contas) {
        for (int i = 0; i < totalContas - 1; i++) {
            for (int j = 0; j < totalContas - i - 1; j++) {
                if (contas[j].getNomeDono().compareTo(contas[j + 1].getNomeDono()) > 0) {
                    ContaDoBanco temp = contas[j];
                    contas[j] = contas[j + 1];
                    contas[j + 1] = temp;
                }
            }
        }
    }
    
    
}