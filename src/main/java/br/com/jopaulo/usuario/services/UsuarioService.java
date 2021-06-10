package br.com.jopaulo.usuario.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jopaulo.usuario.dto.UsuarioDTO;
import br.com.jopaulo.usuario.entities.Usuario;
import br.com.jopaulo.usuario.repositories.UsuarioRepository;
import br.com.jopaulo.usuario.services.exception.ResourceNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;

	@Transactional(readOnly = true)
	public Page<UsuarioDTO> findAllPaged(PageRequest pageRequest){
		Page<Usuario> list = repository.findAll(pageRequest);
		return list.map(x -> new UsuarioDTO(x));
	}
	
	@Transactional(readOnly = true)
	public Page<UsuarioDTO> findAllAge(PageRequest pageRequest){
		Page<Usuario> list = repository.findAll(pageRequest);
		return list.map(x -> new UsuarioDTO(x));
	}
	
	@Transactional(readOnly = true)
	public Page<UsuarioDTO> findAllSalary(PageRequest pageRequest){
		Page<Usuario> list = repository.findAll(pageRequest);
		return list.map(x -> new UsuarioDTO(x));
	}
	
	@Transactional(readOnly = true)
	public UsuarioDTO findById(long id) {
		Optional<Usuario> obj = repository.findById(id);
		Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException("Usuário de código " +id+ " não encontrado"));
		return new UsuarioDTO(entity);
	}
	
	@Transactional
	public UsuarioDTO insert (UsuarioDTO dto) {
		Usuario entity = new Usuario();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new UsuarioDTO(entity);
	}
	
	@Transactional
	public UsuarioDTO update (Long id, UsuarioDTO dto) {
		try {
			Usuario entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UsuarioDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Usuário de código " +id+ " não encontrado");
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Usuário de código " +id+ " não encontrado");
		}
	}

	private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
		entity.setNome(dto.getNome());
		entity.setDataNascimento(dto.getDataNascimento());
		entity.setFoto(dto.getFoto());
	}
}
