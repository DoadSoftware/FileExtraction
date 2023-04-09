package com.fileextraction.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FileExtractionFunctions 
{
	
	public static void FTPDownload(int port,int match_number,String user,String pass) {
		
		FTPClient ftpClient = new FTPClient();
		try {
			 
            ftpClient.connect(FileExtractionUtil.FTP_SERVER_LINK, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: using retrieveFile(String, OutputStream)
            //String remoteFile1 = FileExtractionUtil.SPORTVUSTATISTIC + FileExtractionUtil.ZIP;
            //File downloadFile1 = new File(FileExtractionUtil.FOOTBALL_SPORTS_DIRECTORY + FileExtractionUtil.STATISTIC_DIRECTORY + remoteFile1);
            String remoteFile1 = FileExtractionUtil.SPORTVUSTATISTIC + FileExtractionUtil.XML;
            File downloadFile1 = new File(FileExtractionUtil.FOOTBALL_SPORTS_DIRECTORY + FileExtractionUtil.STATISTIC_DIRECTORY + 
            		FileExtractionUtil.MATCH_DATA_DIRECTORY + remoteFile1);
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
            outputStream1.close();
 
            if (success) {
                System.out.println("File has been downloaded successfully.");
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
		String fileZip = FileExtractionUtil.FOOTBALL_SPORTS_DIRECTORY + FileExtractionUtil.STATISTIC_DIRECTORY + 
				FileExtractionUtil.SPORTVUSTATISTIC + FileExtractionUtil.ZIP;
        File destDir = new File(FileExtractionUtil.FOOTBALL_SPORTS_DIRECTORY + FileExtractionUtil.STATISTIC_DIRECTORY + FileExtractionUtil.MATCH_DATA_DIRECTORY);
        
        byte[] buffer = new byte[1024];
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip))) {
			ZipEntry zipEntry = zis.getNextEntry();
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
			        FileOutputStream fos = new FileOutputStream(newFile);
			        int len;
			        while ((len = zis.read(buffer)) > 0) {
			            fos.write(buffer, 0, len);
			        }
			        fos.close();
			    }
			   zipEntry = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();
		}
	}
	
	public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
	    File destFile = new File(destinationDir, zipEntry.getName());

	    String destDirPath = destinationDir.getCanonicalPath();
	    String destFilePath = destFile.getCanonicalPath();

	    if (!destFilePath.startsWith(destDirPath + File.separator)) {
	        throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
	    }

	    return destFile;
	}
	
}
