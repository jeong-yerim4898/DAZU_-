package com.web.dazu.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.dazu.model.dummy;
import com.web.dazu.service.FileUploadService;

import io.swagger.annotations.ApiOperation;

import java.io.File;
import java.util.List; 

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/file")
public class FileuploadController {
	@Autowired
	private FileUploadService fileuploadservice; 
	@ApiOperation(value = "파일 업로드 및 회원 정보 받아오기", response = String.class)
	//@RequestParam("address") String address,@RequestParam("detailaddress") String detailaddress
	//@RequestPart(value = "key", required = false) dummy dum
	@PostMapping("/fileupload")
	public String uploadSingle(@RequestPart(value = "files", required = true) List<MultipartFile> file
			) throws Exception {
		System.out.println("진입");
		System.out.println(file.get(0).getOriginalFilename());

//		for (int i = 0; i < file.size(); i++) {
//		    String rootPath = "home/Image/";
//		    String basePath = rootPath + "/" + "store";
//		    String filePath = basePath + "/" + file.get(i).getOriginalFilename();
//		    File dest = new File(filePath);
//		    fileuploadservice.fileup(filePath);
//		    file.get(i).transferTo(dest); // 파일 업로드 작업 수행
//		}
	    return "uploaded";
	}
}

