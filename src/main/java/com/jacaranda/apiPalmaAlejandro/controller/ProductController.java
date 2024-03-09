package com.jacaranda.apiPalmaAlejandro.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jacaranda.apiPalmaAlejandro.dto.ProductDTO;
import com.jacaranda.apiPalmaAlejandro.exception.ElementNotFoundException;
import com.jacaranda.apiPalmaAlejandro.exception.ExceptionPageNotFound;
import com.jacaranda.apiPalmaAlejandro.service.ProductService;

@RestController
@CrossOrigin(origins = "https://sneaker-land.vercel.app")
public class ProductController {

	@Autowired
	ProductService productService;
	
//	@GetMapping("/products")
//	public List<ProductDTO> getAll(){
//		return this.productService.getAll();
//	}
	
	@GetMapping("/products")
	public ResponseEntity<?> listProducts(@RequestParam("numPage") Optional<String> numPage,@RequestParam("pageSize") Optional<Integer> pageSize, @RequestParam("order") Optional<String> order, 
			@RequestParam("asc") Optional<Boolean> asc){
		
		Page<ProductDTO> pageProduct = productService.findAllPages(numPage.orElse("1"),pageSize.orElse(10),order.orElse("id"),asc.orElse(false)?"desc":"");
		
		if(pageProduct==null) {
			return ResponseEntity.notFound().build();
		}else if(pageProduct.getContent().size()==0) {
			throw new ExceptionPageNotFound("Pagina no existente");
		}else {
			return ResponseEntity.ok(pageProduct);
		}
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<ProductDTO> get(@PathVariable Integer id){
		ProductDTO result = productService.getById(id);
		if(result==null) {
			throw new ElementNotFoundException(id);
		}else {
			return ResponseEntity.ok(result);
		}
	}
	@GetMapping("/products/{brand}")
	public ResponseEntity<?> listProductsByBrand(@RequestParam("numPage") Optional<String> numPage,@RequestParam("pageSize") Optional<Integer> pageSize, @RequestParam("order") Optional<String> order, 
			@RequestParam("asc") Optional<Boolean> asc, @PathVariable String brand){
		
		Page<ProductDTO> pageProduct = productService.findAllPagesBrand(numPage.orElse("1"),pageSize.orElse(10),order.orElse("id"),asc.orElse(false)?"desc":"", brand);
		
		if(pageProduct==null) {
			return ResponseEntity.notFound().build();
		}else if(pageProduct.getContent().size()==0) {
			throw new ExceptionPageNotFound("Pagina no existente");
		}else {
			return ResponseEntity.ok(pageProduct);
		}
	}
	
	@GetMapping("/products/filter")
	public ResponseEntity<?> listProductsBySearch(@RequestParam("numPage") Optional<String> numPage,@RequestParam("pageSize") Optional<Integer> pageSize, @RequestParam("order") Optional<String> order, 
			@RequestParam("asc") Optional<Boolean> asc, @RequestParam("search") Optional<String> search){
		
		Page<ProductDTO> pageProduct = productService.findAllPagesSearch(numPage.orElse("1"),pageSize.orElse(10),order.orElse("id"),asc.orElse(false)?"desc":"", search.orElse(null));
		
		if(pageProduct==null) {
			return ResponseEntity.notFound().build();
		}else if(pageProduct.getContent().size()==0) {
			throw new ExceptionPageNotFound("Pagina no existente");
		}else {
			return ResponseEntity.ok(pageProduct);
		}
	}
	
	@PostMapping("/product")
    public ResponseEntity<?> create(@RequestPart("file") Optional<MultipartFile> file, @Validated @RequestPart("productDTO") ProductDTO ProductDTO, BindingResult bindingResult) {
		
		ProductDTO newProductDTO = productService.add(ProductDTO, file.orElse(null));
    	
    	if(newProductDTO==null) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al añadir elemento");
    	}else {
    		return ResponseEntity.status(201).body(newProductDTO);
    	}
    
    }

	@PutMapping("/product/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id,@RequestPart("file") Optional<MultipartFile> file,@Validated @RequestPart("productDTO") ProductDTO ProductDTO, BindingResult bindingResult) {
		ProductDTO newProductDTO = productService.edit(id,ProductDTO, file.orElse(null));
		if(newProductDTO==null){

			throw new ElementNotFoundException(id);
		}else {
			return ResponseEntity.ok(newProductDTO);
		}
	}

	//sin terminar (falta terminar controller con imagen)
//	@PutMapping("/product/{id}")
//	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ProductDTO ProductDTO, BindingResult bindingResult) {
//		ProductDTO newProductDTO = productService.edit(id,ProductDTO);
//		if(newProductDTO==null){
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
//    	}else {
//			return ResponseEntity.ok(newProductDTO);
//    	}
//	}
	
	//sin terminar (falta terminar controller con imagen)
//	@PutMapping("/product/{id}")
//	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ProductDTO ProductDTO, BindingResult bindingResult) {
//		ProductDTO newProductDTO = productService.edit(id,ProductDTO);
//		if(newProductDTO==null){
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
//    	}else {
//			return ResponseEntity.ok(newProductDTO);
//    	}
//	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		ProductDTO newProductDTO = productService.delete(id);
		if(newProductDTO==null){
			throw new ElementNotFoundException(id);
    	}else {
    		return ResponseEntity.status(201).body(newProductDTO);
    	}
	}
	
}
