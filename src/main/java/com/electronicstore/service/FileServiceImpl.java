package com.electronicstore.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.electronicstore.exceptions.BadApiRequest;

@Service
public class FileServiceImpl implements FileService {

	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	@Override
	public String uploadFile(MultipartFile file, String path) throws IOException {

		String originalFilename = file.getOriginalFilename();
		logger.info("original file name is {}", originalFilename);
		String id = UUID.randomUUID().toString();
		String extention = originalFilename.substring(originalFilename.lastIndexOf("."));
		String newFileName = id + extention;

		String newFileNameWithPath = newFileName + File.separator + path;

		if (extention.equalsIgnoreCase(".jpg") || extention.equalsIgnoreCase(".jpeg")
				|| extention.equalsIgnoreCase(".png")) {

			// file save
			File folder = new File(path);
			if (!folder.exists()) {

				folder.mkdirs();
			}

			Files.copy(file.getInputStream(), Paths.get(newFileNameWithPath));

			return newFileNameWithPath;
		} else {
			throw new BadApiRequest("File with this extention " + extention + "is not available");
		}

	}

	@Override
	public InputStream getResource(String path, String name) throws FileNotFoundException {
		String fullPath=path+File.separator+name;
		InputStream inputStream=new FileInputStream(fullPath);
		return inputStream;
	}

}
