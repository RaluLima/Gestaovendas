package com.ralu.gestaovenda.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ralu.gestaovenda.modelo.conexao.Conexao;
import com.ralu.gestaovenda.modelo.conexao.ConexaoMysql;
import com.ralu.gestaovenda.modelo.entidades.Cliente;
import com.ralu.gestaovenda.modelo.entidades.Produto;
import com.ralu.gestaovenda.modelo.entidades.Usuario;
import com.ralu.gestaovenda.modelo.entidades.Venda;
import com.ralu.gestaovenda.modelo.entidades.VendaDetalhes;

public class VendaDao {
    private final Conexao conexao;
    private final ProdutoDao produtoDao;

    public VendaDao() {
        this.conexao = new ConexaoMysql();
        this.produtoDao = new ProdutoDao();
    }

    // Método para salvar uma venda (inserir ou editar)
    public String salvar(Venda venda) {
        return venda.getId() == 0L ? adicionar(venda) : editar(venda);
    }

    // Adiciona nova venda
    private String adicionar(Venda venda) {
        String sql = "INSERT INTO venda(total_venda, valor_pago, troco, desconto, cliente_id, usuario_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.obterConexao().prepareStatement(sql);
            preparedStatementSet(ps, venda);

            int resultado = ps.executeUpdate();

            if (resultado == 1) {
                Long idDaVenda = buscarIdDaUltimaVenda();
                venda.setId(idDaVenda);

                // Salva itens da venda (detalhes) e atualiza estoque
                for (VendaDetalhes vd : venda.getVendasDetalhes().values()) {
                    vd.setVenda(venda);
                    int novaQuantidade = vd.getProduto().getQuantidade() - vd.getQuantidade();
                    produtoDao.actualizarQuantidade(vd.getProduto().getId(), novaQuantidade, venda.getUsuario().getId());
                    adicionarVendaItem(vd);
                }

                return "Venda adicionado com sucesso.";
            } else {
                return "Nao foi possivel adicionar venda";
            }

        } catch (SQLException e) {
            return String.format("Error: %s", e.getMessage());
        }
    }

    // Edita uma venda existente
    private String editar(Venda venda) {
        String sql = "UPDATE venda SET total_venda = ?, valor_pago = ?, troco = ?, desconto = ?, cliente_id = ?, usuario_id = ?, ultima_actualizacao = ? WHERE id = ?";
        try {
            PreparedStatement ps = conexao.obterConexao().prepareStatement(sql);
            preparedStatementSet(ps, venda);
            int resultado = ps.executeUpdate();

            if (resultado == 1) {
                for (VendaDetalhes vd : venda.getVendasDetalhes().values()) {
                    editarVendaItem(vd);
                }

                return "Venda editado com sucesso.";
            } else {
                return "Nao foi possivel editar venda";
            }

        } catch (SQLException e) {
            return String.format("Error: %s", e.getMessage());
        }
    }

    // Preenche os parâmetros comuns para salvar/editar venda
    private void preparedStatementSet(PreparedStatement ps, Venda venda) throws SQLException {
        ps.setBigDecimal(1, venda.getTotalVenda());
        ps.setBigDecimal(2, venda.getValorPago());
        ps.setBigDecimal(3, venda.getTroco());
        ps.setBigDecimal(4, venda.getDesconto());
        ps.setLong(5, venda.getCliente().getId());
        ps.setLong(6, venda.getUsuario().getId());

        if (venda.getId() != 0L) {
            ps.setObject(7, LocalDateTime.now());
            ps.setLong(8, venda.getId());
        }
    }

    // Adiciona um item à venda
    private void adicionarVendaItem(VendaDetalhes vd) {
        String sql = "INSERT INTO venda_item(quantidade, total, desconto, venda_id, produto_id) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.obterConexao().prepareStatement(sql);
            preparedStatementSetDetalhes(ps, vd);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar item da venda: " + e.getMessage());
        }
    }

    // Edita um item da venda
    private void editarVendaItem(VendaDetalhes vd) {
        String sql = "UPDATE venda_item SET quantidade = ?, total = ?, desconto = ? WHERE venda_id = ? AND produto_id = ?";
        try {
            PreparedStatement ps = conexao.obterConexao().prepareStatement(sql);
            preparedStatementSetDetalhes(ps, vd);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao editar item da venda: " + e.getMessage());
        }
    }

    // Preenche os parâmetros dos itens da venda
    private void preparedStatementSetDetalhes(PreparedStatement ps, VendaDetalhes vd) throws SQLException {
        ps.setInt(1, vd.getQuantidade());
        ps.setBigDecimal(2, vd.getTotal());
        ps.setBigDecimal(3, vd.getDesconto());
        ps.setLong(4, vd.getVenda().getId());
        ps.setLong(5, vd.getProduto().getId());
    }

    // Retorna todas as vendas
    public List<Venda> todosVendas() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda v, cliente c, usuario u WHERE v.cliente_id = c.id AND v.usuario_id = u.id ORDER BY v.id";
        try {
            ResultSet rs = conexao.obterConexao().prepareStatement(sql).executeQuery();
            while (rs.next()) {
                vendas.add(getVenda(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar vendas: " + e.getMessage());
        }
        return vendas;
    }

    // Busca detalhes de uma venda específica
    public List<VendaDetalhes> buscaDetalhesDaVendaPeloId(Long id) {
        List<VendaDetalhes> detalhes = new ArrayList<>();
        String sql = String.format("SELECT * FROM venda v, venda_item vi, produto p, cliente c, usuario u " +
                "WHERE v.cliente_id = c.id AND v.usuario_id = u.id AND vi.venda_id = v.id AND vi.produto_id = p.id AND v.id = %d", id);
        try {
            ResultSet rs = conexao.obterConexao().prepareStatement(sql).executeQuery();
            while (rs.next()) {
                detalhes.add(getVendaDetalhes(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar detalhes da venda: " + e.getMessage());
        }
        return detalhes;
    }

    // Constrói um objeto VendaDetalhes a partir de um resultado do banco
    private VendaDetalhes getVendaDetalhes(ResultSet rs) throws SQLException {
        VendaDetalhes vd = new VendaDetalhes();
        Venda venda = new Venda();
        Cliente cliente = new Cliente();
        Usuario usuario = new Usuario();
        Produto produto = new Produto();

        cliente.setId(rs.getLong("c.id"));
        cliente.setNome(rs.getString("c.nome"));

        usuario.setId(rs.getLong("u.id"));
        usuario.setNome(rs.getString("u.nome"));

        produto.setId(rs.getLong("p.id"));
        produto.setNome(rs.getString("p.nome"));
        produto.setPreco(rs.getBigDecimal("p.preco"));

        venda.setId(rs.getLong("v.id"));
        venda.setTotalVenda(rs.getBigDecimal("v.total_venda"));
        venda.setValorPago(rs.getBigDecimal("v.valor_pago"));
        venda.setTroco(rs.getBigDecimal("v.troco"));
        venda.setDesconto(rs.getBigDecimal("v.desconto"));
        venda.setDataHoraCriacao(rs.getObject("v.data_hora_criacao", LocalDateTime.class));
        venda.setCliente(cliente);
        venda.setUsuario(usuario);

        vd.setProduto(produto);
        vd.setVenda(venda);
        vd.setQuantidade(rs.getInt("vi.quantidade"));
        vd.setDesconto(rs.getBigDecimal("vi.desconto"));
        vd.setTotal(rs.getBigDecimal("vi.total"));

        return vd;
    }

    // Constrói objeto Venda (sem detalhes)
    private Venda getVenda(ResultSet rs) throws SQLException {
        Venda venda = new Venda();
        Cliente cliente = new Cliente();
        Usuario usuario = new Usuario();

        cliente.setId(rs.getLong("c.id"));
        cliente.setNome(rs.getString("c.nome"));

        usuario.setId(rs.getLong("u.id"));
        usuario.setNome(rs.getString("u.nome"));

        venda.setId(rs.getLong("v.id"));
        venda.setTotalVenda(rs.getBigDecimal("v.total_venda"));
        venda.setValorPago(rs.getBigDecimal("v.valor_pago"));
        venda.setTroco(rs.getBigDecimal("v.troco"));
        venda.setDesconto(rs.getBigDecimal("v.desconto"));
        venda.setDataHoraCriacao(rs.getObject("v.data_hora_criacao", LocalDateTime.class));
        venda.setCliente(cliente);
        venda.setUsuario(usuario);

        return venda;
    }

    // Retorna o ID da última venda inserida
    private Long buscarIdDaUltimaVenda() {
        String sql = "SELECT max(id) FROM venda";
        try {
            ResultSet rs = conexao.obterConexao().prepareStatement(sql).executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar ID da última venda: " + e.getMessage());
        }
        return null;
    }

    // Permite expor a conexão para outras classes (como o Controller)
    public java.sql.Connection obterConexao() throws SQLException {
        return this.conexao.obterConexao();
    }
    public String deleteVendaPeloId(Long id) {
        String sql = "DELETE FROM venda WHERE id = ?";
        try {
            PreparedStatement ps = conexao.obterConexao().prepareStatement(sql);
            ps.setLong(1, id);
            int res = ps.executeUpdate();
            return res == 1 ? "Venda apagada com sucesso." : "Nao foi possivel apagar venda.";
        } catch (SQLException e) {
            if (e.getMessage().toLowerCase().contains("foreign key")) {
                return "Venda não pode ser apagada (referenciada em outra tabela).";
            }
            return "Erro ao apagar: " + e.getMessage();
        }
    }
}



