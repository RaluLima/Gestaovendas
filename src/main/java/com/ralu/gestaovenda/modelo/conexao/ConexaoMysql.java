
package com.ralu.gestaovenda.modelo.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoMysql implements Conexao{

    private Connection connection;
    private final String URL = "jdbc:mysql://localhost:3306/gestao_vendas1?user=root";
    private final String USER = "root";
    private final String PASSWORD = "123456";

    @Override
    public Connection obterConexao() throws SQLException {
        if(connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    @Override
    public void fecharConexao() throws SQLException {
        if(connection != null)
            connection.close();
    }

}
