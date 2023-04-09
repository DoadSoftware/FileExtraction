package com.fileextraction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fileextraction.dao.FileExtractionDao;
import com.fileextraction.service.FileExtractionService;

@Service("fileExtractionService")
@Transactional
public class FileExtractionServiceImpl implements FileExtractionService {

 @SuppressWarnings("unused")
@Autowired
 private FileExtractionDao fileExtractionDao;

}