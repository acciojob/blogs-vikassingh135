package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);
        blog.getImageList().add(image);
        imageRepository2.save(image);
        return image;
    }

    public void deleteImage(Image image){
        imageRepository2.delete(image);
    }

    public Image findById(int id) {
        return imageRepository2.findById(id).get();
    }

    public int countImagesInScreen(int id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
           if(!imageRepository2.existsById(id)) return 0;
           Image image = imageRepository2.findById(id).get();
           String imageDimension = image.getDimensions();
           String first[] = imageDimension.split("\\*");
           String second[] = screenDimensions.split("\\*");
           return Integer.parseInt(second[0])*Integer.parseInt(second[1])/(Integer.parseInt(first[0])*Integer.parseInt(first[1]));
    }
}
