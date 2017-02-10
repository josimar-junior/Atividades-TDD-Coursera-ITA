package gamificacao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class Repositorio {

	private String nomeArquivo;

	public Repositorio(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
		new File(nomeArquivo);
	}

	public void limparArquivo() {
		salvarUsuarios(new HashMap<>());
	}

	public void salvar(Usuario usuario) {
		Map<String, Usuario> usuarios = listarUsuarios();
		usuarios.put(usuario.getNome(), usuario);
		salvarUsuarios(usuarios);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Usuario> listarUsuarios() {
		try (ObjectInputStream objInput = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(nomeArquivo)))) {
			
			return (Map<String, Usuario>) objInput.readObject();

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao listar usuários.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	private void salvarUsuarios(Map<String, Usuario> usuarios) {
		try (ObjectOutputStream obj = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(nomeArquivo)))) {
			
			obj.writeObject(usuarios);

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao salvar usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	public Usuario getUsuarioOuCria(String nome) {
		return listarUsuarios().getOrDefault(nome, new Usuario(nome));
	}

}
