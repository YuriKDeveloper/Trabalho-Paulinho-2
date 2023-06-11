
package com.mycompany.trabalhopaulinho.models;


public class ContaDoBanco {
    private String nomeDono;
    private int nrmConta;
    private double saldoAtual;

    public ContaDoBanco(String nomeDono, int nrmConta, double saldoAtual) {
        this.nomeDono = nomeDono;
        this.nrmConta = nrmConta;
        this.saldoAtual = saldoAtual;

    }

    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }

    public int getNrmConta() {
        return nrmConta;
    }

    public void setNrmConta(int nrmConta) {
        this.nrmConta = nrmConta;
    }

    public double getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(double saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public void exibirInformacoes() {
        System.out.println("Número da conta: " + nrmConta);
        System.out.println("Titular da conta: " + nomeDono);
        System.out.println("Saldo atual: " + saldoAtual);
    }
    
    
}
