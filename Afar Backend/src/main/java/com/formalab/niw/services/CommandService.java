package com.formalab.niw.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.formalab.niw.entities.Client;
import com.formalab.niw.entities.Command;
import com.formalab.niw.entities.Point;
import com.formalab.niw.entities.Product;
import com.formalab.niw.entities.TotalPoint;
import com.formalab.niw.repositories.CommandRepository;
import com.formalab.niw.repositories.TotalPointRepository;

@Service
public class CommandService {

	@Autowired
	CommandRepository commandeRepository ;
	@Autowired
	ProductService productService ; 
	@Autowired
	ClientService clientService ; 
	@Autowired
	TotalPointRepository  tpr ; 
	
	
	
//	public int ClienttotalPointsperCommandEntreprise (Command command , Client client ) {
//		
//		 int clienttotalPoints = 0 ;
//		Long entrepriseId = command.getProduct().getEntreprise().getId() ; 
//		
//		Set<Point> points =  client.getPoints() ;
//		
//		for (Point point : points) {
//			if (point.getPublicity().getEntreprise().getId()== entrepriseId) {
//				
//				clienttotalPoints = clienttotalPoints +  point.getEarned() ;
//			}
//		}
//	
//
//		return clienttotalPoints  ; 
//	}
	
	
	
	public TotalPoint ClienttotalPointsperCommandEntreprise (Command command , Client client ) {
		TotalPoint totalPoint = new TotalPoint() ;
	Long entrepriseId = command.getProduct().getEntreprise().getId() ; 
	Set<TotalPoint> Totalpoint =  client.getTotalpointsPerEntreprise() ;
	for (TotalPoint tp : Totalpoint) {
		if (tp.getIdEntreprise()== entrepriseId) {
			totalPoint = tp ;
		}
	}
	return totalPoint  ; 
}
	
	
	public void decreaseClientPoints (Client client , int points) {

		client.getPoints() ;
		
	}
	
	
	
	public String saveCommandProductClient( Long idClient , Long [] idp) {
		String messageResult = "" ;
		Client client = clientService.findById(idClient).get();
		boolean fnstatus = false ; 
		
		do {
			
		
		for (int i = 0 ; i<idp.length ; i++) {
			Command command = new Command();
			Product product = productService.findById(idp[i]).get() ;
			command.setClient(client);
			command.setProduct(product) ;
			int clienttotalPoints = ClienttotalPointsperCommandEntreprise(command, client).getTotalpoints() ;
			
			if (clienttotalPoints < command.getProduct().getDiscountPoints()) {
				
				messageResult = "insufficient number of points for this company" ;
				
			} else {
				
				command.setStatus("waiting") ;
				command.setPrice(command.getProduct().getPrice()-command.getProduct().getDiscountPriceperPoints()) ;

				commandeRepository.save(command);
				messageResult = "{\"message\" : \"passed waiting for confirmation\" }" ;
				
			}
			
			if (i==idp.length) {
				fnstatus = true ;
			}
			System.out.println(i+"******"+idp.length);
		}
		
		return messageResult ;
		
		}while(fnstatus==true) ;
		
	}
	
	
	
	public String confirmCommand(Long idCommand ) {
		Command command = findById(idCommand).get();
		
		if (command.getStatus().equals("Confirmed") || command.getStatus().equals("Canceled") ) {
			
			return "{\"error\" : \" command was treated \" }" ;
			
		}else {
			TotalPoint tp = ClienttotalPointsperCommandEntreprise(command, command.getClient()) ;
			
			int totalPoints = tp.getTotalpoints() ;
			
			totalPoints = totalPoints - command.getProduct().getDiscountPoints() ;
			
			tp.setTotalpoints(totalPoints) ;
			tpr.save(tp) ;
				command.setStatus("Confirmed") ;
			
		
				commandeRepository.save(command);
			return "{\"message\" : \"command confirmed\" }";
		}
		
		
	}
	
	public String cancelCommand(Long idCommand ) {
		Command command = findById(idCommand).get();
		
		if (command.getStatus().equals("Confirmed") || command.getStatus().equals("Canceled")  ) {
			
			return "{\"error\" : \" command was treated \" }" ;
			
		}else {
			
				command.setStatus("Cancled") ;
			
			 command.getClient().getPoints().stream().forEach(p->{
				
			}); ;
			
			
			commandeRepository.save(command);
			return "{\"message\" : \"command canceled\" }" ;
		}
		
		
	}
	

	public <S extends Command> S save(S entity) {
		return commandeRepository.save(entity);
	}

	
	
	public <S extends Command> Optional<S> findOne(Example<S> example) {
		return commandeRepository.findOne(example);
	}

	public Page<Command> findAll(Pageable pageable) {
		return commandeRepository.findAll(pageable);
	}

	public List<Command> findAll() {
		return commandeRepository.findAll();
	}

	public List<Command> findAll(Sort sort) {
		return commandeRepository.findAll(sort);
	}

	public List<Command> findAllById(Iterable<Long> ids) {
		return commandeRepository.findAllById(ids);
	}

	public <S extends Command> List<S> saveAll(Iterable<S> entities) {
		return commandeRepository.saveAll(entities);
	}

	public Optional<Command> findById(Long id) {
		return commandeRepository.findById(id);
	}

	public void flush() {
		commandeRepository.flush();
	}

	public <S extends Command> S saveAndFlush(S entity) {
		return commandeRepository.saveAndFlush(entity);
	}

	public boolean existsById(Long id) {
		return commandeRepository.existsById(id);
	}

	public void deleteInBatch(Iterable<Command> entities) {
		commandeRepository.deleteInBatch(entities);
	}

	public <S extends Command> Page<S> findAll(Example<S> example, Pageable pageable) {
		return commandeRepository.findAll(example, pageable);
	}

	public void deleteAllInBatch() {
		commandeRepository.deleteAllInBatch();
	}

	public Command getOne(Long id) {
		return commandeRepository.getOne(id);
	}

	public <S extends Command> long count(Example<S> example) {
		return commandeRepository.count(example);
	}

	public <S extends Command> boolean exists(Example<S> example) {
		return commandeRepository.exists(example);
	}

	public <S extends Command> List<S> findAll(Example<S> example) {
		return commandeRepository.findAll(example);
	}

	public long count() {
		return commandeRepository.count();
	}

	public void deleteById(Long id) {
		commandeRepository.deleteById(id);
	}

	public <S extends Command> List<S> findAll(Example<S> example, Sort sort) {
		return commandeRepository.findAll(example, sort);
	}

	public void delete(Command entity) {
		commandeRepository.delete(entity);
	}

	public void deleteAll(Iterable<? extends Command> entities) {
		commandeRepository.deleteAll(entities);
	}

	public void deleteAll() {
		commandeRepository.deleteAll();
	} 
	
	
	
}