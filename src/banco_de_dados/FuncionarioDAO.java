package banco_de_dados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import entidades.Funcionario;

/**
 * DAO = Data Access Object
 * É a classe responsável por manipular as informações da classe Funcionário
 */
public class FuncionarioDAO {

    public FuncionarioDAO(){
    }

    public void inserir(Funcionario f){
        
        Connection con = ConexaoMySQL.getConnection();
        PreparedStatement ps;

        try {

            ps = con.prepareStatement("INSERT INTO funcionarios (nome, data_nasc, salario, data_registro, cadastro_ativo) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, f.getNome());
            ps.setDate(2, new java.sql.Date(f.getDataNascimento().getTime()));
            ps.setBigDecimal(3, f.getSalario());
            ps.setTimestamp(4, new Timestamp(f.getDataRegistro().getTime())); 
            ps.setBoolean(5, f.getAtivo());

            ps.execute();

            ps.close();
            con.close();

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public ArrayList<Funcionario> listarTodos(){
        
        ArrayList<Funcionario> lista = new ArrayList<>();

        Connection con = ConexaoMySQL.getConnection();
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("SELECT * FROM funcionarios");
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {

                Funcionario f = new Funcionario();

                f.setId(resultado.getInt("id"));
                f.setNome(resultado.getString("nome"));
                f.setDataNascimento(new java.util.Date(resultado.getDate("data_nasc").getTime()));
                f.setSalario(resultado.getBigDecimal("salario"));
                f.setDataRegistro(new java.util.Date(resultado.getTimestamp("data_registro").getTime()));
                f.setAtivo(resultado.getBoolean("cadastro_ativo"));

                lista.add(f);
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return lista;
    }

    public void alterar(Funcionario f){

        Connection con = ConexaoMySQL.getConnection();
        PreparedStatement ps;

        try {
            ps = con.prepareStatement(
                "UPDATE funcionarios SET nome = ?, data_nasc = ?, salario = ?, cadastro_ativo = ? WHERE id = ?"
            );
            ps.setString(1, f.getNome());
            ps.setDate(2, new java.sql.Date(f.getDataNascimento().getTime()));
            ps.setBigDecimal(3, f.getSalario());
            ps.setBoolean(4, f.getAtivo());

            ps.executeUpdate();

            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deletar(Funcionario f) {

    }

    public Funcionario buscarPorId(Integer id){
        return null;
    }

}
