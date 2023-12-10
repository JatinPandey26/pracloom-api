package com.pracloomcompany.pracloom.Service;

import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryImageService {
    public Map uploadImage(MultipartFile image) throws IOException;
}
