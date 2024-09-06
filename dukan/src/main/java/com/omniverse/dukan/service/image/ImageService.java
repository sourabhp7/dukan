package com.omniverse.dukan.service.image;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.omniverse.dukan.exceptions.ResourceNotFoundException;
import com.omniverse.dukan.model.Image;
import com.omniverse.dukan.repository.ImageRepository;
import com.omniverse.dukan.service.product.IproductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

	private final ImageRepository imageRepository;

	private final IproductService iproductService;

	@Override
	public Image getImageById(Long id) {
		// TODO Auto-generated method stub
		return imageRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No image dound of this is: " + id));
	}

	@Override
	public void deleteImageById(Long id) {
		imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {
			throw new ResourceNotFoundException("No image found with id: " + id);
		});

	}

	@Override
	public Image saveImage(MultipartFile file, Long imageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateImage(MultipartFile file, Long imageId) {
		Image image = getImageById(imageId);

		try {
			image.setFileName(file.getOriginalFilename());
			image.setFileName(file.getOriginalFilename());
			image.setImage(new SerialBlob(file.getBytes()));
			imageRepository.save(image);

		} catch (IOException | SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

}
