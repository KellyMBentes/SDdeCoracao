package src.app.dao;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Vector;

import src.app.model.Usuario;
import src.app.conf.Configuracao;
import src.lib.Debug;

public class UsuarioCSVDao implements UsuarioDao {
	private static final String CONF_REPOSITORIO = "repositorio_usuario_csv";
	private static final String SEPARADOR = ";";
	private static final String CONTEUDO_LINHA = "{:id}"+UsuarioCSVDao.SEPARADOR+"{:ip}";
	private static String REPOSITORIO = Configuracao.get(CONF_REPOSITORIO);

	public void salvarUsuario(Usuario val){
		Debug.println("Salvando usuário: "+val);
		String resultado = usuarioParaLinha(val);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(REPOSITORIO, true))){
			bw.write(resultado);
			Debug.println("usuário {:usuario} salvo!".replace("{:usuario}", val.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Usuario buscarUsuarioPorId(String id){
		Usuario resultado = null;
		String linha = null;

		try (BufferedReader br = new BufferedReader(new FileReader(REPOSITORIO))){
			Debug.println("Abrindo arquivo "+REPOSITORIO);
			while ((linha = br.readLine()) != null) {

	            resultado = linhaParaUsuario(linha);
	            Debug.println("usuário lido: "+resultado);
	            if(resultado.getId() == id){
	            	Debug.println("usuário com id {:id} encontrado!".replace("{:id}", id));
	            	break;
	            }
	        }

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return resultado;
	}

	public Usuario buscarUsuarioPorIp(String ip){
		Usuario resultado = null;
		String linha = null;

		try (BufferedReader br = new BufferedReader(new FileReader(REPOSITORIO))){
			while ((linha = br.readLine()) != null) {

	            resultado = linhaParaUsuario(linha);
	            if(resultado.getIp() == ip){
	            	break;
	            }
	        }

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return resultado;
	}

	private Usuario linhaParaUsuario(String linha){
		Usuario resultado = null;
		String atributosUsuario[] = linha.split(SEPARADOR);
		String chaveAtributosUsuario[] = CONTEUDO_LINHA.split(SEPARADOR);
		ArrayList lista = new ArrayList<String>();
		for (int i= 0; i < chaveAtributosUsuario.length; i++){
			lista.add(chaveAtributosUsuario[i]);
		}
		// atributos
		
		try{
			String id = atributosUsuario[lista.indexOf("{:id}")];
			String ip = atributosUsuario[lista.indexOf("{:ip}")];
			resultado = new Usuario(id, ip);
			
		} catch (Exception e) {
			Debug.println("Linha fora do formato esperado!");
			e.printStackTrace();
		}
		return resultado;

	}

	private String usuarioParaLinha(Usuario usuario){
		return CONTEUDO_LINHA.replace("{:id}", usuario.getId()).replace("{:ip}", usuario.getIp());
	}
}