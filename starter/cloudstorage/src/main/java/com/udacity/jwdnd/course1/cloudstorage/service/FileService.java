package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FileService {
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public ArrayList<File> getFiles(Integer userId) {
        return this.fileMapper.getUserFiles(userId);
    }

    public void addFile(FileForm fileForm) {
        File file = new File();
        file.setUserId(Integer.valueOf(fileForm.getUserId()));
        file.setContentType(fileForm.getContentType());
        file.setFileName(fileForm.getFileName());
        file.setFileSize(fileForm.getFileSize());
        file.setFileData(fileForm.getFileData());
        this.fileMapper.createFile(file);
    }

    public void updateFile(FileForm fileForm) {
        File file = new File();
        file.setUserId(Integer.valueOf(fileForm.getUserId()));
        file.setContentType(fileForm.getContentType());
        file.setFileName(fileForm.getFileName());
        file.setFileSize(fileForm.getFileSize());
        file.setFileData(fileForm.getFileData());
        this.fileMapper.updateFile(file);
    }

    public Integer deleteFile(Integer fileId) { return this.fileMapper.deleteFile(fileId); }
}
