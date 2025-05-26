package com.formalab.niw.fileStorage;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FIleControllerDB {

		@Autowired 
		private FileRepository fileRepository ;

		public <S extends File> S save(S entity) {
			return fileRepository.save(entity);
		}

		public List<File> findAll() {
			return fileRepository.findAll();
		}

		public Optional<File> findById(Integer id) {
			return fileRepository.findById(id);
		}

		public void delete(File entity) {
			fileRepository.delete(entity);
		} 
	
	
}
