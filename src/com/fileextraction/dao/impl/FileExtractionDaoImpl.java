package com.fileextraction.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fileextraction.dao.FileExtractionDao;

@Transactional
@Repository("fileExtractionDao")
public class FileExtractionDaoImpl implements FileExtractionDao {

 @SuppressWarnings("unused")
@Autowired
 private SessionFactory sessionFactory;


}