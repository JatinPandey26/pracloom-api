package com.pracloomcompany.pracloom.Service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pracloomcompany.pracloom.Service.CloudinaryImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CloudnaryService implements CloudinaryImageService {

    private final Cloudinary cloudinary;

    @Override
    public Map uploadImage(MultipartFile image) throws IOException {

        Map data = this.cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
        log.info("data : {}",data);
        return data;
    }
}
