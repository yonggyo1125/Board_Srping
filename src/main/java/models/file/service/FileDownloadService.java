package models.file.service;

import java.io.*;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import models.file.FileInfoDao;
import models.file.FileInfoDto;

@Service
public class FileDownloadService {
	
	@Value("${fileUploadPath}")
	private String fileUploadPath;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private FileInfoDao fileInfoDao;
	
	public void download(Long id) {
		FileInfoDto fileInfo = fileInfoDao.get(id);
		if (fileInfo == null) { // 파일 정보가 없다면 진행 X
			return;
		}
		
		String path = fileUploadPath + File.separator + "" + (id % 10) + File.separator + id + fileInfo.getExtension();
		File _path = new File(path);
		if (!_path.exists()) { // 파일이 존재하지 않으면 진행 X
			return;
		}
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(_path))) {
			OutputStream out = response.getOutputStream();
			response.setHeader("Content-Description", "File Transfer");
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileInfo.getFileName().getBytes(), "ISO8859_1"));
			response.setHeader("Content-Type", "application/octet-stream");
			response.setIntHeader("Expires", 0);
			response.setHeader("Cache-Control", "must-revalidate");
			response.setHeader("Pragma", "public");
			response.setHeader("Content-Length", String.valueOf(_path.length()));
			
			int i;
			while((i = bis.read()) != -1) {
				out.write(i);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
