
package com.ralu.gestaovenda.view.formulario;

import com.ralu.gestaovenda.modelo.controller.AutenticacaoController;
import com.ralu.gestaovenda.modelo.dao.UsuarioDao;
import com.ralu.gestaovenda.modelo.entidades.Usuario;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.ralu.gestaovenda.modelo.entidades.PERFIL;

public class LoginForm extends javax.swing.JFrame {
    
    private AutenticacaoController autenticacaoController;

    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        initComponents();
        setLocationRelativeTo(null);
        autenticacaoController = new AutenticacaoController(this);
        eventos();
    }
    
    private void eventos() {
        botaoLoginLogin.addActionListener(autenticacaoController);
        botaoLoginCancelar.addActionListener(autenticacaoController);
    }

 
    @SuppressWarnings("unchecked")
    
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLoginUsername = new javax.swing.JTextField();
        botaoLoginCancelar = new javax.swing.JButton();
        botaoLoginLogin = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        labelLoginMensagem = new javax.swing.JLabel();
        txtLoginSenha = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(java.awt.Color.white);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("Username:");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("Senha:");

        txtLoginUsername.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        txtLoginUsername.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        botaoLoginCancelar.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        botaoLoginCancelar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ralu\\Desktop\\gestao de vendas\\src\\main\\java\\com\\ralu\\gestaovenda\\view\\imagens\\signout16.png")); // NOI18N
        botaoLoginCancelar.setText("Cancelar");

        botaoLoginLogin.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        botaoLoginLogin.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ralu\\Desktop\\gestao de vendas\\src\\main\\java\\com\\ralu\\gestaovenda\\view\\imagens\\login_16.png")); // NOI18N
        botaoLoginLogin.setText("Login");

        labelLoginMensagem.setBackground(java.awt.Color.white);
        labelLoginMensagem.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelLoginMensagem.setForeground(java.awt.Color.red);
        labelLoginMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLoginMensagem.setOpaque(true);

        txtLoginSenha.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("Ralu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelLoginMensagem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtLoginUsername, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSeparator1)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(botaoLoginLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(botaoLoginCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtLoginSenha))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(labelLoginMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLoginUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLoginSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(0, 0, 0)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoLoginCancelar)
                    .addComponent(botaoLoginLogin))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        
        UsuarioDao usuarioDao = new UsuarioDao();

        
        Usuario user = new Usuario();
        user.setId(0L);
        user.setNome("Ralu");
        user.setSenha("123456");
        user.setUsername("ralu123");
        user.setEstado(true);
        user.setUsuario("ADMIN");
        user.setPerfil(PERFIL.ADMIN);

        usuarioDao.salvar(user);

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoLoginCancelar;
    private javax.swing.JButton botaoLoginLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelLoginMensagem;
    private javax.swing.JPasswordField txtLoginSenha;
    private javax.swing.JTextField txtLoginUsername;
    // End of variables declaration//GEN-END:variables


    public JTextField getTxtLoginUsername() {
        return txtLoginUsername;
    }
    
    public JTextField getTxtLoginSenha() {
        return txtLoginSenha;
    }
    
    public JButton getBotaoLoginLogin() {
        return botaoLoginLogin;
    }
    
    public JButton getBotaoLoginCancelar() {
        return botaoLoginCancelar;
    }
    
    public JLabel getLabelLoginMensagem() {
        return labelLoginMensagem;
    }
}
