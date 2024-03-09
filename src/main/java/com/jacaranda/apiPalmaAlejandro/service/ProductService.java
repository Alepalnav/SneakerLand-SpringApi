package com.jacaranda.apiPalmaAlejandro.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jacaranda.apiPalmaAlejandro.dto.Order_detailsDTO;
import com.jacaranda.apiPalmaAlejandro.dto.ProductDTO;
import com.jacaranda.apiPalmaAlejandro.exception.ExceptionValueNotRight;
import com.jacaranda.apiPalmaAlejandro.model.Product;
import com.jacaranda.apiPalmaAlejandro.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	Cloudinary cloudinaryConfig;
	
	public List<ProductDTO> getAll(){
		List<Product>list = productRepository.findAll();
        return ProductDTO.convertProductToDTO(list);	   
	}
	
	public Page<ProductDTO> findAllPages(String numPage, int pageSize, String order, String ad){
		Pageable pageable = null;
		
		if(ad.equals("desc")) {
			try {
				pageable = PageRequest.of(Integer.valueOf(numPage) - 1, pageSize,Sort.by(order).descending());
			}catch (Exception e) {
				pageable = PageRequest.of(0, pageSize, Sort.by(order).descending());
			}
		}else {
			try {
				pageable = PageRequest.of(Integer.valueOf(numPage) - 1, pageSize,Sort.by(order).ascending());
			}catch (Exception e) {
				pageable = PageRequest.of(0, pageSize, Sort.by(order).ascending());
			}
		}
		
		Page<Product> temp = productRepository.findByRemove(0, pageable);
		Page<ProductDTO> result = temp.map(product -> new ProductDTO(product.getId(),product.getName(),product.getBrand(),product.getDescrip(),product.getSize(),product.getImage(), product.getPrice(), product.getStock(),product.getRemove()));
	    return result;
	}
	
	public ProductDTO getById(Integer id) {
		Product product = productRepository.findById(id).orElse(null);
		if(product==null) {
			return null;
		}
		Order_detailsDTO order_detailsDTO = new Order_detailsDTO();
		try {
			ProductDTO productDTO = new ProductDTO(product.getId(),product.getName(),product.getBrand(),product.getDescrip(),product.getSize(),product.getImage(), product.getPrice(), product.getStock(),product.getRemove(),order_detailsDTO.convertToDTO(product.getDetails()));			
			return productDTO;
		}catch(Exception e) {
			return null;
		}
	}
	
	public Page<ProductDTO> findAllPagesBrand(String numPage, int pageSize, String order, String ad, String brand){
		Pageable pageable = null;
		
		if(ad.equals("desc")) {
			try {
				pageable = PageRequest.of(Integer.valueOf(numPage) - 1, pageSize,Sort.by(order).descending());
			}catch (Exception e) {
				pageable = PageRequest.of(0, pageSize, Sort.by(order).descending());
			}
		}else {
			try {
				pageable = PageRequest.of(Integer.valueOf(numPage) - 1, pageSize,Sort.by(order).ascending());
			}catch (Exception e) {
				pageable = PageRequest.of(0, pageSize, Sort.by(order).ascending());
			}
		}
		
		Page<Product> temp = productRepository.findByBrand(brand,pageable);
		Page<ProductDTO> result = temp.map(product -> new ProductDTO(product.getId(),product.getName(),product.getBrand(),product.getDescrip(),product.getSize(),product.getImage(), product.getPrice(), product.getStock(),product.getRemove()));
	    return result;
	}
	
	public Page<ProductDTO> findAllPagesSearch(String numPage, int pageSize, String order, String ad, String name){
		Pageable pageable = null;
		
		if(ad.equals("desc")) {
			try {
				pageable = PageRequest.of(Integer.valueOf(numPage) - 1, pageSize,Sort.by(order).descending());
			}catch (Exception e) {
				pageable = PageRequest.of(0, pageSize, Sort.by(order).descending());
			}
		}else {
			try {
				pageable = PageRequest.of(Integer.valueOf(numPage) - 1, pageSize,Sort.by(order).ascending());
			}catch (Exception e) {
				pageable = PageRequest.of(0, pageSize, Sort.by(order).ascending());
			}
		}
		
		Page<Product> temp = productRepository.findByNameContainsAndRemove(name,0,pageable);
		Page<ProductDTO> result = temp.map(product -> new ProductDTO(product.getId(),product.getName(),product.getBrand(),product.getDescrip(),product.getSize(),product.getImage(), product.getPrice(), product.getStock(),product.getRemove()));
		return result;
	}
	
	
	public ProductDTO add(ProductDTO productDTO, MultipartFile file) {
		Integer id = productDTO.getId();
		if(id!=null && productRepository.existsById(id)) {
			return null;
		}else {
			if(file!=null && !file.isEmpty()) {
				String url = uploadFile(file);
				productDTO.setImage(url);
			}
			try {
				Product product = new Product(productDTO.getName(), productDTO.getBrand(), productDTO.getDescrip(),productDTO.getSize(), productDTO.getImage(), productDTO.getPrice(), productDTO.getStock(), productDTO.getRemove()); 
				productRepository.save(product);
				id = product.getId();
				ProductDTO newProductDTO = new ProductDTO(id,productDTO.getName(), productDTO.getBrand(), productDTO.getDescrip(),productDTO.getSize(), productDTO.getImage(), productDTO.getPrice(), productDTO.getStock(), productDTO.getRemove());
				return newProductDTO;				
			}catch(Exception e) {
				throw new ExceptionValueNotRight(e.getMessage());
			}
		}
	}
	
	public ProductDTO edit(Integer id, ProductDTO productDTO, MultipartFile file) {
		ProductDTO newProduct = getById(id);
		if(newProduct!=null) {
			if(file!=null && !file.isEmpty()) {
				String url = uploadFile(file);
				newProduct.setImage(url);
			}

			try {
				Product product = new Product(id,productDTO.getName(), productDTO.getBrand(), productDTO.getDescrip(),productDTO.getSize(), productDTO.getImage(), productDTO.getPrice(), productDTO.getStock(), productDTO.getRemove());
				productRepository.save(product);
				ProductDTO productDTO2 = new ProductDTO(product.getId(),product.getName(),product.getBrand(),product.getDescrip(),product.getSize(),product.getImage(), product.getPrice(), product.getStock(),product.getRemove());
				return productDTO2;				
			}catch(Exception e) {
				throw new ExceptionValueNotRight(e.getMessage());
			}
		}else {
			return null;
		}
	}
	
	public ProductDTO delete(Integer id) {

		Product product = productRepository.getById(id);
		if(product!=null && product.getRemove()==0) {
			try {
				product.setRemove(1);
				productRepository.save(product);
				Order_detailsDTO order_detailsDTO = new Order_detailsDTO();
				ProductDTO productDTO = new ProductDTO(product.getId(),product.getName(),product.getBrand(),product.getDescrip(),product.getSize(),product.getImage(), product.getPrice(), product.getStock(),product.getRemove(),order_detailsDTO.convertToDTO(product.getDetails()));
				return productDTO;				
			}catch(Exception e) {
				return null;
			}
		}else {
			return null;
		}
	}
	
	public String uploadFile(MultipartFile gif) {
		try {
			File uploadedFile = convertMultiPartToFile(gif);
			Map uploadResult =
			cloudinaryConfig.uploader().upload(uploadedFile,
					ObjectUtils.emptyMap());
			boolean isDeleted = uploadedFile.delete();
				if (isDeleted){
					System.out.println("File successfully deleted");
				}else
					System.out.println("File doesn't exist");
				return uploadResult.get("url").toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private File convertMultiPartToFile(MultipartFile file) throws
	IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

}
