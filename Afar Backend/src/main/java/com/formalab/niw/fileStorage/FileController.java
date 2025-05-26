package com.formalab.niw.fileStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.formalab.niw.entities.AppUser;
import com.formalab.niw.entities.Product;
import com.formalab.niw.entities.Publicity;
import com.formalab.niw.repositories.AppUserRepository;
import com.formalab.niw.services.ProductService;
import com.formalab.niw.services.PublicityService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    AppUserRepository utilisateurService ; 
    @Autowired
    PublicityService publicityService ; 
    @Autowired
    FileService fileService ;
    @Autowired
    ProductService productService ;
    
    
    //Upload File pour support
    @PostMapping("/uploadproductimage/{id}")
    public Product uploadFileproduct(@RequestParam("file") MultipartFile file ,
    		@PathVariable("id") Long id) { 
    	
    	Product product = productService.findById(id).get() ;
    	
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        
       File fileuploaded = new File() ;
       
       fileuploaded.setExtension(fileName);
       fileuploaded.setFileName(fileDownloadUri);
       

       fileService.save(fileuploaded) ;
       
    
       
       
        new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
        
        product.setImageLink(fileDownloadUri) ;
        return productService.save(product);
    }
    
    
    
    //Upload File pour support
    @PostMapping("/uploadpublicity/{id}")
    public void uploadFileUtilisateur(@RequestParam("file") MultipartFile file ,
    		@PathVariable("id") Long id) { 
    	
    	Publicity publicity = publicityService.findById(id) ;
    	
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        
       File fileuploaded = new File() ;
       
       fileuploaded.setExtension(fileName);
       fileuploaded.setFileName(fileDownloadUri);
       fileuploaded.setPublicity(publicity);
   
       	publicity.setVideo(fileuploaded);

       fileService.save(fileuploaded) ;
       
    
       
       
        new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
        
        publicity.setVideoLink(fileDownloadUri) ;
        publicityService.save(publicity);
    }
    
    //Upload Photo pour Utilisateur
    @PostMapping("/uploadimage/{id}")
    public void uploadFileSupport(@RequestParam("file") MultipartFile file ,
    		@PathVariable("id") Long id) {
    	AppUser utilisateur = utilisateurService.findById(id).get() ;
    	
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        
       File fileuploaded = new File() ;
       
       fileuploaded.setExtension(fileName);
       fileuploaded.setFileName(fileDownloadUri);
       
       fileService.save(fileuploaded) ;
       
       utilisateur.setImageFile(fileuploaded);
   
        new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
        
        utilisateur.setImageLink(fileDownloadUri);
        
        utilisateurService.save(utilisateur);
    }
    
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
	
    @GetMapping("/downloadFile/exist/{fileName:.+}")
    public boolean testfile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        Boolean test =true ;
        String response =  new String() ;
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
            test=false ;
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
            test=false ;
        }

        if (test==true) {
        	 response = "{"+'"'+"testfield"+'"'+":"+'"'+"true"+'"'+"}" ;
        }else {
        	 response = "{"+'"'+"testfield"+'"'+":"+'"'+"false"+'"'+"}" ;
        }
        
        return test;
    }
    
    
}