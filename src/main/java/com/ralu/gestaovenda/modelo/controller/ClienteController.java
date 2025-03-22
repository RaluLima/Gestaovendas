
package com.ralu.gestaovenda.modelo.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.ralu.gestaovenda.modelo.dao.ClienteDao;
import com.ralu.gestaovenda.modelo.entidades.Cliente;
import com.ralu.gestaovenda.modelo.util.ClienteTableModel;
import com.ralu.gestaovenda.view.formulario.Dashboard;


public class ClienteController implements ActionListener {
    private Dashboard dashboard;
    private ClienteDao clienteDao;
    private ClienteTableModel clienteTableModel;

    public ClienteController(Dashboard dashboard) {
        this.dashboard = dashboard;
        this.clienteDao = new ClienteDao();
        actualizarTabelaCliente();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String accao = ae.getActionCommand().toLowerCase();
        
        switch(accao) {
            case "adicionar": adicionar(); break;
            case "salvar": salvar(); break;
            case "cancelar": cancelar();break;
        }
    }

    public void salvar() {
        String idString = this.dashboard.getTxtClienteId().getText();
        String nome = this.dashboard.getTxtClienteNome().getText();
        String telefone = this.dashboard.getTxtClienteTelefone().getText();
        String endereco = this.dashboard.getTxtClienteEndereco().getText();
        
        Long id = Long.valueOf(idString);
        
        Cliente cliente = new Cliente(id, nome, telefone, endereco);
        String mensagem = clienteDao.salvar(cliente);
        
        if(mensagem.startsWith("Cliente")) {
            mensagemNaTela(mensagem, Color.GREEN);
            actualizarTabelaCliente();
        }else {
            mensagemNaTela(mensagem, Color.RED);
        }
    }
    
    private void mensagemNaTela(String mensagem, Color color) {
         this.dashboard.getLabelClienteMensagem().setBackground(color);
         this.dashboard.getLabelClienteMensagem().setText(mensagem);
    }

    private void cancelar() {
        limpar();
        this.dashboard.getDialogCliente().setVisible(false);
    }
    
    private void limpar() {
        this.dashboard.getTxtClienteId().setText("0");
        this.dashboard.getTxtClienteNome().setText("");
        this.dashboard.getTxtClienteTelefone().setText("");
        this.dashboard.getTxtClienteEndereco().setText("");
    }
    
    private void mostrarTela() {
        this.dashboard.getDialogCliente().pack();
        this.dashboard.getDialogCliente().setLocationRelativeTo(dashboard);
        this.dashboard.getDialogCliente().setVisible(true);
    }

    private void adicionar() {
        mostrarTela();
    }

    private void actualizarTabelaCliente() {
        List<Cliente> clientes = clienteDao.todosCliente();
        this.clienteTableModel = new ClienteTableModel(clientes);
        this.dashboard.getTabelaCliente().setModel(clienteTableModel);
        this.dashboard.getLabelHomeCliente().setText(String.format("%d", clientes.size()));
    }
}
