package imagens;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import modelo.Produtos;

import org.apache.commons.io.IOUtils;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class Imagens {
	private File pastaImagens;

	public Imagens() {
		String pastaImagens = "/home/kde/Wesley/projeto/foto";
		this.pastaImagens = new File(pastaImagens);
		File destino = new File(pastaImagens);
		destino.mkdir();
	}

	public void salva(UploadedFile imagem, Produtos produtos) {
		File destino = new File(pastaImagens, produtos.getId() + ".imagem");
		try {
			IOUtils.copy(imagem.getFile(), new FileOutputStream(destino));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao copiar imagem", e);
		}
	}

	public File mostra(Produtos produtos) {
		return new File(pastaImagens, produtos.getId() + ".imagem");
	}

}
