package com.a88.controller;

import com.a88.Pojo.cloudStorage;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
public class uploadFileUtil {
//    //@Value("${google.cloudStorage.projectID}")
//    private String projectID;
//    //@Value("${google.cloudStorage.bucketName}")
//    private String bucketName;

    @Autowired
    private cloudStorage storage;

    public String uploadFile(MultipartFile file) throws IOException {

        String bucketName = storage.getBucketName();
        String projectID = storage.getProjectID();

        // get service
        Storage storage = StorageOptions.newBuilder().setProjectId(projectID)
                .build().getService();
        // use UUID to set only one fileName
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;


        BlobId blobId = BlobId.of(bucketName, newFileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        // upload to Cloud Storage
        storage.createFrom(blobInfo, file.getInputStream());
        // file's access url
        String url = "https://storage.cloud.google.com/" + bucketName + "/" + newFileName;

        System.out.println("File " + file.getOriginalFilename() + " uploaded to bucket " + bucketName + " as " + newFileName + " file url is: " + url);

        return url;
    }
}
