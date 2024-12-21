package com.fileextraction.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FileExtractionFunctions 
{
	
	public static void FTPDownload(int port,int match_number,String user,String pass) {
		
		FTPClient ftpClient = new FTPClient();
		ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		try {
			 
            //ftpClient.connect(FileExtractionUtil.FTP_SERVER_LINK, port);
			ftpClient.connect(FileExtractionUtil.FTP_SERVER_LINK);
            
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
            	ftpClient.disconnect();
                throw new IOException("Exception in connecting to FTP Server");
            }
            
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            String remoteFile1 = FileExtractionUtil.SPORTVUSTATISTIC + FileExtractionUtil.ZIP;
            File downloadFile1 = new File(FileExtractionUtil.FOOTBALL_SPORTS_DIRECTORY + FileExtractionUtil.STATISTIC_DIRECTORY + remoteFile1);
           
            try (FileOutputStream out = new FileOutputStream(downloadFile1)) {
                boolean success = ftpClient.retrieveFile(remoteFile1, out);

                if (success) {
                    System.out.println("File has been downloaded successfully.");
                } else {
                    throw new IOException("Failed to download file from FTP: " + remoteFile1);
                }
            }
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	}
	
	public static void FTPDownloadXMLFile(int port,int match_number,String user,String pass) {
		
		FTPClient ftpClient = new FTPClient();
		ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		try {
			 
            //ftpClient.connect(FileExtractionUtil.FTP_SERVER_LINK, port);
			ftpClient.connect(FileExtractionUtil.FTP_SERVER_LINK);
            
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
            	ftpClient.disconnect();
                throw new IOException("Exception in connecting to FTP Server");
            }
            
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            String remoteFile1 = FileExtractionUtil.SPORTVUSTATISTIC + FileExtractionUtil.XML;  // Update this to XML path
            File downloadFile1 = new File(FileExtractionUtil.FOOTBALL_SPORTS_DIRECTORY + FileExtractionUtil.STATISTIC_DIRECTORY + 
            		FileExtractionUtil.MATCH_DATA_DIRECTORY + FileExtractionUtil.SPORTVUSTATISTIC + FileExtractionUtil.XML); // Save as XML file
           
            try (FileOutputStream out = new FileOutputStream(downloadFile1)) {
                boolean success = ftpClient.retrieveFile(remoteFile1, out);

                if (success) {
                    System.out.println("XML file has been downloaded successfully.");
                } else {
                    throw new IOException("Failed to download XML file from FTP: " + remoteFile1);
                }
            }
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	}
	
	public static void UnzipDownloadFile(int match_number) throws IOException {
		
		String fileZip = FileExtractionUtil.FOOTBALL_SPORTS_DIRECTORY + FileExtractionUtil.STATISTIC_DIRECTORY + FileExtractionUtil.SPORTVUSTATISTIC + FileExtractionUtil.ZIP;
        File destDir = new File(FileExtractionUtil.FOOTBALL_SPORTS_DIRECTORY + FileExtractionUtil.STATISTIC_DIRECTORY + FileExtractionUtil.MATCH_DATA_DIRECTORY);
        
        byte[] buffer = new byte[1024];
        try (ZipArchiveInputStream zis = new ZipArchiveInputStream(new FileInputStream(fileZip))) {
        	ArchiveEntry zipEntry = zis.getNextEntry();
			while (zipEntry != null) {
				File newFile = newFile(destDir, zipEntry);
				if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + newFile);
                    }
                } else {
                    // fix for Windows-created archives
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("Failed to create directory " + parent);
                    }

                    // write file content
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zipEntry = zis.getNextEntry();
			}
			//zis.closeEntry();
			//zis.close();
		}
	}
	
	public static File newFile(File destinationDir, ArchiveEntry zipEntry) throws IOException {
	    File destFile = new File(destinationDir, zipEntry.getName());

	    String destDirPath = destinationDir.getCanonicalPath();
	    String destFilePath = destFile.getCanonicalPath();

	    if (!destFilePath.startsWith(destDirPath + File.separator)) {
	        throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
	    }

	    return destFile;
	}
	
}
