package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	/* Módulo de conexão */
	// Parâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "1234";

	// Método de conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println();
			return null;
		}
	}

	/* CRUD CREAT */
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome, fone, email) values (?, ?, ?)";
		try {
			// abrir a conexao
			Connection con = conectar();
			// preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// substituir os parametros pelo conteudo das variaveus javabeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// executar a query
			pst.executeUpdate();
			// encerrar a conexao com o banco
			con.close();

		} catch (Exception e) {
			System.out.println();
		}
	}
	
	/**
	 * Listar contatos.
	 *
	 * @return the array list
	 */
	/*CRUD READ */
	public ArrayList<JavaBeans> listarContatos() {
		ArrayList<JavaBeans> contatos = new ArrayList<>();
	    String read = "select * from contatos order by nome";
	    try {
	        Connection con = conectar();
	        PreparedStatement pst = con.prepareStatement(read);
	        ResultSet rs = pst.executeQuery();
	      while (rs.next()) {
	    	  String idcon = rs.getString(1);
	    	  String nome = rs.getString(2);
	    	  String fone = rs.getString(3);
	    	  String email = rs.getString(4);
	    	  contatos.add(new JavaBeans(idcon,nome,fone,email));
	      }
	      con.close();
	      return contatos;
	    } catch (Exception e) {
	        System.out.println(e);
	        return null;
	    }
	}
	
	/** CRUD UPDATE **/
	
	public void selecionarContato(JavaBeans contato) {
		String read2 = "select *from contatos where idcon= ?";
		try {
			// abrir a conexao
			Connection con = conectar();
			// preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(read2);
			// substituir os parametros pelo conteudo das variaveus javabeans
			pst.setString(1,contato.getIdcon());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
				
			}
			con.close();

		} catch (Exception e) {
			System.out.println();
		}
	}
	//editar
	public void alterarContato(JavaBeans contato) {
		String create = "update contatos set nome=?,fone=?,email=? where idcon=?";
		try {
				
			Connection con = conectar();
			
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1,contato.getNome());
			pst.setString(2,contato.getFone());
			pst.setString(3,contato.getEmail());
			pst.setString(4,contato.getIdcon());
			pst.executeUpdate();
			con.close();
		}
		 catch (Exception e) {
			System.out.println(e);
		}
	} 
	
	public void deletarContato(JavaBeans contato) {
		String delete = "delete from contatos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
}
