package models.file.service;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import commons.errors.CommonRestException;
import models.file.FileInfoDao;
import models.file.FileInfoDto;
import models.member.MemberDto;

@Service
public class FileUploadService {
	
	@Value("${fileUploadPath}")
	private String uploadPath;
	
	@Autowired
	private FileInfoDao fileInfoDao;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;
	
	public List<FileInfoDto> upload(MultipartFile[] files) {
		return upload(files, null);
	}
	
	public List<FileInfoDto> upload(MultipartFile[] files, String gid) {
		return upload(files, gid, false);
	}
	
	public List<FileInfoDto> upload(MultipartFile[] files, String gid, boolean imageOnly) {
		gid = gid == null ? "" + System.currentTimeMillis() : gid; // 그룹 ID가 없는 경우는 새로 생성
		
		/**
		 * 0. imageOnly 값이 있으면 이미지가 포함되어 있지 않는지 체크 
		 * 1. 파일 정보를 DB 기록 
		 * 2. 파일 등록 번호 -> 분산될 디렉토리 번호 결정, 파일 명으로 업로드
		 */
		  
		if (imageOnly) {
			for (MultipartFile file : files) {
				String contentType = file.getContentType();
				if (contentType.indexOf("image") == -1) { // 이미지가 아닌 파일이 섞여 있다.
					throw new CommonRestException("이미지 형식의 파일만 업로드 하세요.");
				}
			}
		}
		
		MemberDto member = (MemberDto)session.getAttribute("member");
		
		if (uploadPath == null) {
			uploadPath = request.getServletContext().getRealPath(".") + "/../resources/static/uploads";
		}
		
		List<FileInfoDto> fileInfos = new ArrayList<>();
		
		for (MultipartFile file : files) {
			// 1. 파일 정보를 DB 기록 
			String fileName = file.getOriginalFilename();
			String extension = fileName.substring(fileName.lastIndexOf("."));
			FileInfoDto param = new FileInfoDto();
			param.setGid(gid);
			param.setFileName(fileName);
			param.setContentType(file.getContentType());
			param.setExtension(extension);
			param.setMember(member);
			
			FileInfoDto fileInfo = fileInfoDao.register(param);
			
			// 2. 파일 등록 번호 -> 분산될 디렉토리 번호 결정, 파일 명으로 업로드
			long id = fileInfo.getId();
			String folder = "" + (id % 10);
			File uploadDir = new File(uploadPath + File.separator + folder);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			String filePath = uploadPath + File.separator + folder + File.separator + id;
			if (!extension.isBlank()) filePath +=  extension;
						
			String fileUrl = request.getContextPath() + "/uploads/" + folder + "/"  + id;
			if (!extension.isBlank()) fileUrl += extension;
			
			fileInfo.setFilePath(filePath);
			fileInfo.setFileUrl(fileUrl);
			System.out.println(filePath);
			try (FileOutputStream fos = new FileOutputStream(filePath);
				BufferedOutputStream bos = new BufferedOutputStream(fos)) {
				bos.write(file.getBytes());
				
				fileInfos.add(fileInfo); // 파일 업로드 성공시 정보 추가 
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return fileInfos;
	}
}



