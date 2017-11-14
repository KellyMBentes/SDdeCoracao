package src.app.dao;

import src.app.model.Usuario;

public interface UsuarioDao {
	public void salvarUsuario(Usuario val);
	public Usuario buscarUsuarioPorId(String id);
	public Usuario buscarUsuarioPorIp(String ip);
}