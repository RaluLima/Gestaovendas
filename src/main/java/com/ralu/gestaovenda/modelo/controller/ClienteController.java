package com.ralu.gestaovenda.modelo.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

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
            case "editar": editar(); break;     // ✅ novo
            case "apagar": apagar(); break;     // ✅ novo
            case "cancelar": cancelar(); break;
        }
    }

    public void salvar() {
        String idString = dashboard.getTxtClienteId().getText();
        String nome = dashboard.getTxtClienteNome().getText();
        String telefone = dashboard.getTxtClienteTelefone().getText();
        String endereco = dashboard.getTxtClienteEndereco().getText();
        
        Long id = Long.valueOf(idString);
        Cliente cliente = new Cliente(id, nome, telefone, endereco);
        String mensagem = clienteDao.salvar(cliente);
        
        if(mensagem.startsWith("Cliente")) {
            mensagemNaTela(mensagem, Color.GREEN);
            actualizarTabelaCliente();
            limpar();
        } else {
            mensagemNaTela(mensagem, Color.RED);
        }
    }
    
    private void editar() {
        int linhaSelecionada = dashboard.getTabelaCliente().getSelectedRow();
        if (linhaSelecionada >= 0) {
            Cliente cliente = clienteTableModel.getClientes().get(linhaSelecionada);
            dashboard.getTxtClienteId().setText(cliente.getId().toString());
            dashboard.getTxtClienteNome().setText(cliente.getNome());
            dashboard.getTxtClienteTelefone().setText(cliente.getTelefone());
            dashboard.getTxtClienteEndereco().setText(cliente.getEndereco());
            mostrarTela();
        } else {
            JOptionPane.showMessageDialog(dashboard, "Deves selecionar um cliente na tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void apagar() {
        int linhaSelecionada = dashboard.getTabelaCliente().getSelectedRow();
        if (linhaSelecionada >= 0) {
            Cliente cliente = clienteTableModel.getClientes().get(linhaSelecionada);
            int confirmar = JOptionPane.showConfirmDialog(dashboard,
                String.format("Tens certeza que desejas apagar o cliente: %s?", cliente.getNome()),
                "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                String mensagem = clienteDao.deleteClientePeloId(cliente.getId());
                JOptionPane.showMessageDialog(dashboard, mensagem);
                actualizarTabelaCliente();
                limpar();
            }
        } else {
            JOptionPane.showMessageDialog(dashboard, "Deves selecionar um cliente na tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void mensagemNaTela(String mensagem, Color color) {
        dashboard.getLabelClienteMensagem().setBackground(color);
        dashboard.getLabelClienteMensagem().setText(mensagem);
    }

    private void cancelar() {
        limpar();
        dashboard.getDialogCliente().setVisible(false);
    }

    private void limpar() {
        dashboard.getTxtClienteId().setText("0");
        dashboard.getTxtClienteNome().setText("");
        dashboard.getTxtClienteTelefone().setText("");
        dashboard.getTxtClienteEndereco().setText("");
    }

    private void mostrarTela() {
        dashboard.getDialogCliente().pack();
        dashboard.getDialogCliente().setLocationRelativeTo(dashboard);
        dashboard.getDialogCliente().setVisible(true);
    }

    private void adicionar() {
        limpar();
        mostrarTela();
    }

    private void actualizarTabelaCliente() {
        List<Cliente> clientes = clienteDao.todosCliente();
        clienteTableModel = new ClienteTableModel(clientes);
        dashboard.getTabelaCliente().setModel(clienteTableModel);
        dashboard.getLabelHomeCliente().setText(String.format("%d", clientes.size()));
    }
}