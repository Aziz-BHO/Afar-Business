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
import com.formalab.niw.entities.Point;
import com.formalab.niw.entities.Publicity;
import com.formalab.niw.entities.TotalPoint;
import com.formalab.niw.repositories.PointRepository;

@Service
public class PointService {

	@Autowired
	PointRepository pointRepository ;

	@Autowired
	ClientService clientService ; 
	
	@Autowired
	PublicityService publicityService ; 
	
	
	public  Point AffectPointClientPublicity(Long idClient , Long IdPublicity) {
		
		System.out.println(idClient);
		
		Client client = clientService.findById(idClient).get();
		
		Publicity publicity = publicityService.findById(IdPublicity);

			
			Point point = new Point() ;
			
			point.setClient(client);
			point.setPublicity(publicity);
			point.setEarned(publicity.getPointToEarn()) ;
			
			client.getPoints().add(point) ; 
			
			
			
			Set<TotalPoint> clientTotalPointList =  client.getTotalpointsPerEntreprise();
			int totalpointEarned = 0 ; 
			if (clientTotalPointList==null  || clientTotalPointList.isEmpty()  ) {
				totalpointEarned = publicity.getPointToEarn() ;
				TotalPoint totalPoint = new TotalPoint() ;
				
				totalPoint.setClient(client) ; 
				totalPoint.setIdEntreprise(publicity.getEntreprise().getId()) ;
				totalPoint.setTotalpoints(totalpointEarned) ;
				
				clientTotalPointList.add(totalPoint) ;
				
			}else if (!clientTotalPointList.isEmpty())  {
				
			
				
				
			
				
				boolean exist = clientTotalPointList.parallelStream().anyMatch(pt->pt.getIdEntreprise()== publicity.getEntreprise().getId()) ;
				
				
				if (exist ) {
					
				
					
					TotalPoint tp = clientTotalPointList.parallelStream().findAny().filter(pt->pt.getIdEntreprise()==publicity.getEntreprise().getId()).get() ; 
					
					if (tp.getIdEntreprise() == publicity.getEntreprise().getId()   ) {
						
						totalpointEarned = tp.getTotalpoints() ;
						
						totalpointEarned = totalpointEarned + publicity.getPointToEarn() ;  
						
						tp.setTotalpoints(totalpointEarned);
						
					}
				}else   {
					
				
					
					
					TotalPoint totalPoint = new TotalPoint() ;
					totalPoint.setIdEntreprise(publicity.getEntreprise().getId());
					
					totalPoint.setClient(client) ;
					
					
					totalpointEarned = totalpointEarned + publicity.getPointToEarn() ;  
					
					totalPoint.setTotalpoints(totalpointEarned);
					client.getTotalpointsPerEntreprise().add(totalPoint);
					
					clientTotalPointList.add(totalPoint) ;
					client.getTotalpointsPerEntreprise().add(totalPoint);
				} 
				
				//for (TotalPoint tp : clientTotalPointList) {
					
					
					
					
				//}
				
			}
			
			
			
			
			publicity.getPointEarned().add(point) ; 
			
			
			
			return pointRepository.save(point);
			
		
			
			
			
		}
		
	
	
	
	
//	public  String AffectPointClientPublicity(Long idClient , Long IdPublicity) {
//		Client client = clientService.findById(idClient).get();
//		
//		Publicity publicity = publicityService.findById(IdPublicity).get();
//		
//		System.out.println(pointRepository.existsPointByClient(client));
//		
//		if (!pointRepository. existsPointByClient(client)) {
//			
//
//			
//			Point point = new Point() ;
//			
//			point.setClient(client);
//			point.setPublicity(publicity);
//			point.setEarned(publicity.getPointToEarn()) ;
//			
//			client.getPoints().add(point) ; 
//			publicity.getPointEarned().add(point) ; 
//			
//			
//			
//			return pointRepository.save(point).toString();
//			
//		}else {
//			
//			return "{\"error\" : \"vous avez deja regarder la video \" }" ;
//			
//		}
//		
//	
//	}
	
	
	
	
	public <S extends Point> S save(S entity) {
		return pointRepository.save(entity);
	}

	public <S extends Point> Optional<S> findOne(Example<S> example) {
		return pointRepository.findOne(example);
	}

	public Page<Point> findAll(Pageable pageable) {
		return pointRepository.findAll(pageable);
	}

	public List<Point> findAll() {
		return pointRepository.findAll();
	}

	public List<Point> findAll(Sort sort) {
		return pointRepository.findAll(sort);
	}

	public List<Point> findAllById(Iterable<Long> ids) {
		return pointRepository.findAllById(ids);
	}

	public <S extends Point> List<S> saveAll(Iterable<S> entities) {
		return pointRepository.saveAll(entities);
	}

	public Optional<Point> findById(Long id) {
		return pointRepository.findById(id);
	}

	public void flush() {
		pointRepository.flush();
	}

	public <S extends Point> S saveAndFlush(S entity) {
		return pointRepository.saveAndFlush(entity);
	}

	public boolean existsById(Long id) {
		return pointRepository.existsById(id);
	}

	public void deleteInBatch(Iterable<Point> entities) {
		pointRepository.deleteInBatch(entities);
	}

	public <S extends Point> Page<S> findAll(Example<S> example, Pageable pageable) {
		return pointRepository.findAll(example, pageable);
	}

	public void deleteAllInBatch() {
		pointRepository.deleteAllInBatch();
	}

	public Point getOne(Long id) {
		return pointRepository.getOne(id);
	}

	public <S extends Point> long count(Example<S> example) {
		return pointRepository.count(example);
	}

	public <S extends Point> boolean exists(Example<S> example) {
		return pointRepository.exists(example);
	}

	public <S extends Point> List<S> findAll(Example<S> example) {
		return pointRepository.findAll(example);
	}

	public long count() {
		return pointRepository.count();
	}

	public void deleteById(Long id) {
		pointRepository.deleteById(id);
	}

	public <S extends Point> List<S> findAll(Example<S> example, Sort sort) {
		return pointRepository.findAll(example, sort);
	}

	public void delete(Point entity) {
		pointRepository.delete(entity);
	}

	public void deleteAll(Iterable<? extends Point> entities) {
		pointRepository.deleteAll(entities);
	}

	public void deleteAll() {
		pointRepository.deleteAll();
	} 
	
	
}
