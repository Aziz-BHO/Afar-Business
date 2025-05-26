package com.formalab.niw.fileStorage;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;





// service file pour la persistence de l'objet File  
@Service
public class FileService {

	@Autowired
	FileRepository fileRepository ;

	public <S extends File> S save(S entity) {
		return fileRepository.save(entity);
	}

	public <S extends File> Optional<S> findOne(Example<S> example) {
		return fileRepository.findOne(example);
	}

	public Page<File> findAll(Pageable pageable) {
		return fileRepository.findAll(pageable);
	}

	public List<File> findAll() {
		return fileRepository.findAll();
	}

	public List<File> findAll(Sort sort) {
		return fileRepository.findAll(sort);
	}

	public List<File> findAllById(Iterable<Integer> ids) {
		return fileRepository.findAllById(ids);
	}

	public <S extends File> List<S> saveAll(Iterable<S> entities) {
		return fileRepository.saveAll(entities);
	}

	public Optional<File> findById(Integer id) {
		return fileRepository.findById(id);
	}

	public void flush() {
		fileRepository.flush();
	}

	public <S extends File> S saveAndFlush(S entity) {
		return fileRepository.saveAndFlush(entity);
	}

	public boolean existsById(Integer id) {
		return fileRepository.existsById(id);
	}

	public void deleteInBatch(Iterable<File> entities) {
		fileRepository.deleteInBatch(entities);
	}

	public <S extends File> Page<S> findAll(Example<S> example, Pageable pageable) {
		return fileRepository.findAll(example, pageable);
	}

	public void deleteAllInBatch() {
		fileRepository.deleteAllInBatch();
	}

	public File getOne(Integer id) {
		return fileRepository.getOne(id);
	}

	public <S extends File> long count(Example<S> example) {
		return fileRepository.count(example);
	}

	public <S extends File> boolean exists(Example<S> example) {
		return fileRepository.exists(example);
	}

	public <S extends File> List<S> findAll(Example<S> example) {
		return fileRepository.findAll(example);
	}

	public long count() {
		return fileRepository.count();
	}

	public void deleteById(Integer id) {
		fileRepository.deleteById(id);
	}

	public <S extends File> List<S> findAll(Example<S> example, Sort sort) {
		return fileRepository.findAll(example, sort);
	}

	public void delete(File entity) {
		fileRepository.delete(entity);
	}

	public void deleteAll(Iterable<? extends File> entities) {
		fileRepository.deleteAll(entities);
	}

	public void deleteAll() {
		fileRepository.deleteAll();
	} 
	
}
