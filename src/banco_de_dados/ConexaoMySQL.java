package banco_de_dados;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoMySQL {

	public static Connection getConnection() {

        Connection con = null;

        try {

            String usuario = "root"; // Nome do seu usuário localhost (o padrão é root)
            String senha = "root"; // Senha do seu usuário localhost (o padrão é vazio ou root, depende versão)
            String url = "jdbc:mysql://localhost:3306/"; // Caminho que localiza o banco de dados (a porta padrão do MYSQL é 3306)
            String nome_do_banco = "banco_java"; // Nome do seu banco de dados

            con = DriverManager.getConnection(url + nome_do_banco, usuario, senha);

        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    
  }
