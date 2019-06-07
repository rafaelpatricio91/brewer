package com.rafa.brewer.storage.local;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.rafa.brewer.storage.FotoStorage;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class FotoStorageLocal implements FotoStorage
{
	private static final Logger LOGGER = LoggerFactory.getLogger(FotoStorageLocal.class);
	
	private Path local;
	private Path localTemporario;
	
	public FotoStorageLocal()
	{
		this(FileSystems.getDefault().getPath(System.getenv("HOME"), ".brewerfotos") );
	}
	
	public FotoStorageLocal(Path path)
	{
		this.local = path;
		criarPastas();
	}
	
	private void criarPastas()
	{
		try
		{
			Files.createDirectories(this.local);
			this.localTemporario = FileSystems.getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);
			
			if (LOGGER.isDebugEnabled())
			{
				LOGGER.debug("Pastas criadas para salvar fotos");
				LOGGER.debug("Pasta default: "+ this.local.toAbsolutePath());
				LOGGER.debug("Pasta temporária: " + this.localTemporario.toAbsolutePath() );
			}
		} 
		catch (IOException ex)
		{
			throw new RuntimeException("Erro ao criar pasta para salvar foto", ex);
		}
	}
	
	@Override
	public String salvarTemporariamente(MultipartFile[] files)
	{
		String novoNome = null;
		
		if (files != null && files.length > 0)
		{
			MultipartFile arquivo = files[0];
			novoNome = renomearArquivo(arquivo.getOriginalFilename() );
			
			try
			{
				arquivo.transferTo(new File(this.localTemporario.toAbsolutePath().
						toString()+ FileSystems.getDefault().getSeparator() + novoNome));
			} catch (IOException ex)
			{
				throw new RuntimeException("Erro ao salvar a foto na pasta temporária", ex);
			
			}
		}
		return novoNome;
	}
	
	private String renomearArquivo(String nomeOriginal)
	{
		String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;
		
		if (LOGGER.isDebugEnabled() )
		{
			LOGGER.debug(String.format("Novo nome do arquivo %s", novoNome));
		}
		
		return novoNome;
	}

	@Override
	public byte[] recuperarFotoTemporaria(String nome)
	{
		try
		{
			return Files.readAllBytes(this.localTemporario.resolve(nome) );
		} catch (IOException e)
		{
			throw new RuntimeException("Erro ao ler foto temporária ", e);
		}
	}
	
	@Override
	public void salvar(String foto)
	{
		try
		{
			Files.move(this.localTemporario.resolve(foto), this.local.resolve(foto));
		} catch (IOException e)
		{
			throw new RuntimeException("Erro ao mover a foto para o destino final", e);		
		}
		
		try
		{
			Thumbnails.of(this.local.resolve(foto).toString()).size(40, 68).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e)
		{
			throw new RuntimeException("Erro ao gerar thumbnail ", e);
		}
	}

	@Override
	public byte[] recuperar(String foto)
	{
		try
		{
			return Files.readAllBytes(this.local.resolve(foto) );
		} catch (IOException e)
		{
			throw new RuntimeException("Erro ao ler foto ", e);
		}
	}
}
