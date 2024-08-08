package com.kh.service;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.dto.UserProfile;
import com.kh.mapper.ProfileMapper;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	private ProfileMapper profileMapper;
	
	//application.properties에 존재하는 파일 경로 가져와서 변수에 넣어주기
	@Value("${file.upload-dir}")
	private String profileDir;
	
	/*
	어떤 코드를 작성해야할지 감이 오지 않을 때는
	ctrl + space와 특정 변수명 뒤에 .을 작성해서 어떤 기능이 나오는지 살펴보기
	*/
	@Override
	public List<UserProfile> getProfile() {
		// TODO Auto-generated method stub
		// TODO 당신에게 필요한 기능을 추가로 기입하세요.
		return profileMapper.getProfile();
	}
	
	@Override
	public void insertProfile(UserProfile userProfile) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void uploadProfile(MultipartFile[] files, String username, String profileImageUrl) {
		//폴더 존재하는지 확인 후 폴더 없으면 생성
		//폴더도 하나의 파일이므로 File로 폴더 확인
		//글자 이외에는 모두 File이라 생각하면 됨
		File uploadDirFile = new File(profileDir);
		
		//만약에 폴더가 존재하지 않으면 폴더들 생성하기
		if (!uploadDirFile.exists()) {
			if(!uploadDirFile.mkdirs()) {
				System.out.println("디렉토리 폴더에 대한 설정 완료!");
				//throw new Exception("디렉토리 생성에 실패하였습니다.");
			}
			
		}
		//프로필을 업데이트 하기위해 받은 이미지가 초기에는 없기 때문에 처음 이미지들의 이름은 null로 설정
		List<String> fileNames = null;
		
		fileNames = List.of(files).stream().map(file -> {
			String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			File df = new File(profileDir + File.separator + fileName);
			try {
				file.transferTo(df);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return fileName;
		}).collect(Collectors.toList());
		
		//UserProfile 객체 생성 각 객체에 현재 작성한 값들 넣어주는 set 작성
		//image = "join" 과 "," 를 이용해서 넣어줌
		UserProfile up = new UserProfile();
		up.setUsername(username);
		up.setProfileImageUrl(String.join(",", fileNames));
		insertProfile(up);//set으로 추가된 값을 DB에 넣어주기
	}
}
