package models.file.service;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import models.file.FileInfoDao;
import models.file.FileInfoDto;
import models.member.MemberDto;

@Service
public class FileDeleteService {
	
	@Value("${fileUploadPath}")
	private String fileUploadPath;
	
	@Autowired
	private FileInfoDao fileInfoDao;
	
	@Autowired
	private HttpSession session;
	
	public void process(Long id) {
		FileInfoDto fileInfo = fileInfoDao.get(id);
		if (fileInfo == null) {
			throw new RuntimeException("등록되지 않은 파일입니다.");
		}
		
		String path = fileUploadPath + File.separator + "" + (id % 10) + File.separator + id + fileInfo.getExtension();
		File _path = new File(path);
		if (!_path.exists()) { 
			throw new RuntimeException("파일이 존재하지 않습니다.");
		}
		
		MemberDto member = (MemberDto)session.getAttribute("member");
		if (member != null && fileInfo.getMember() != null && fileInfo.getMember().getMemNo() != member.getMemNo()) {
			throw new RuntimeException("본인이 등록한 파일만 삭제가능합니다.");
		}
		
		fileInfoDao.delete(id);
		_path.delete();
	}
	
}
