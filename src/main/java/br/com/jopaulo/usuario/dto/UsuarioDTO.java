package br.com.jopaulo.usuario.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.jopaulo.usuario.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO implements Serializable{

	private Long id;
	private String nome;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	private String foto;
	
	public UsuarioDTO(Usuario entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.dataNascimento = entity.getDataNascimento();
		this.foto = entity.getFoto();	
	}
	
}
