package gui;

import javax.swing.*;

import batalhanaval.Embarcacao;

public class Botao extends JButton {
  private Embarcacao embarcacao;

  public Botao() {
  }

  public Botao(Embarcacao embarcacao) {
    this.embarcacao = embarcacao;
  }

  public Embarcacao getEmbarcacao() {
    return this.embarcacao;
  }

  public void setEmbarcacao(Embarcacao embarcacao) {
    this.embarcacao = embarcacao;
  }
}
