
package com.ralu.gestaovenda.modelo.conexao;

import java.sql.Connection;
import java.sql.SQLException;


public interface Conexao {
    
    public Connection obterConexao() throws SQLException;
    public void fecharConexao()throws SQLException;
    
}
