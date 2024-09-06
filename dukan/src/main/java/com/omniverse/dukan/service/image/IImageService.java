package com.omniverse.dukan.service.image;

import org.springframework.web.multipart.MultipartFile;

import com.omniverse.dukan.model.Image;

public interface IImageService {
	
	Image getImageById(Long id);
	void deleteImageById(Long id);
	Image saveImage(MultipartFile file,Long imageId);
	void updateImage(MultipartFile file,Long imageId);

}
