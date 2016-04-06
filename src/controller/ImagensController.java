package controller;

import imagens.Imagens;

import java.io.File;

import modelo.Produtos;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

@Resource
public class ImagensController {
	private final Imagens imagens;  
    private final Result result;  
      
    public ImagensController(Imagens imagens, Result result) {       
        this.imagens = imagens;    
        this.result = result;  
    }  
      
    @Post("/produtos/{produtos.id}/imagem")  
    public void upload(Produtos produtos, final UploadedFile imagem) {        
        imagens.salva(imagem, produtos);  
        result.redirectTo(ProdutosController.class).edita(produtos.getId());  
    }  
      
     @Get ("/produtos/{produtos.id}/imagem")  
     public File download(Produtos produtos) {  
         return imagens.mostra(produtos);  
     }  

}
