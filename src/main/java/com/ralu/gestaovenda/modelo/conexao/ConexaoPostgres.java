
package com.ralu.gestaovenda.modelo.conexao;

import java.sql.Connection;
import java.sql.SQLException;

public class ConexaoPostgres implements Conexao{

    @Override
    public Connection obterConexao() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fecharConexao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
