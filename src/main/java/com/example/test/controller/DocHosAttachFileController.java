package com.example.test.controller;

import com.example.test.model.user.vo.DocHosAttachFileVO;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/hosupload/*")
public class DocHosAttachFileController {

    @PostMapping(value = "hosuploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<DocHosAttachFileVO> hosUploadAjaxAction(MultipartFile[] hosUploadFiles) {
        log.info("hosupload ajax action...........");
        List<DocHosAttachFileVO> hosFileList = new ArrayList<>();

        log.info("----------hosFileList------------");
        log.info(String.valueOf(hosFileList.size()));
        log.info("---------------------------------");
        log.info("---------multipart length--------------");
        log.info(String.valueOf(hosUploadFiles.length));
        log.info("---------------------------------------");

        String uploadFolder = "C:/hosupload";
        String uploadFolderPath = getFolder();

//        년/월/일 폴더 생성
        File uploadPath = new File(uploadFolder, uploadFolderPath);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        log.info("Hospital upload path : " + uploadPath);
        for (MultipartFile multipartFile : hosUploadFiles) {
            log.info("-------------------------");
            log.info("Hospital Upload File Name : " + multipartFile.getOriginalFilename());
            log.info("Hospital Upload File Size : " + multipartFile.getSize());

            DocHosAttachFileVO docHosAttachFileVO = new DocHosAttachFileVO();

            String hosuploadFileName = multipartFile.getOriginalFilename();

//            UUID
//            네트워크 상에서 각각의 개체들을 식별하기 위하여 사용되었다.
//            중복될 가능성이 거의 없다고 인정되기 때문에 많이 사용된다.

//            UUID 개수
//            340,282,366,920,938,463,463,374,607,431,768,211,456개
//            10의 38승 : 만4, 억8, 조12, 경16, 해20, 자24, 양28, 구32, 간36
//            340간
            UUID uuid = UUID.randomUUID();
            hosuploadFileName = uuid.toString() + "_" + hosuploadFileName;
            //IE에서는 파일 이름을 포함한 전체 경로가 나오기 때문에 잘라야한다.
            hosuploadFileName = hosuploadFileName.substring(hosuploadFileName.lastIndexOf("\\") + 1);
            log.info("file name : " + hosuploadFileName);

            docHosAttachFileVO.setHosFileName(hosuploadFileName);

            try {
                File saveFile = new File(uploadPath, hosuploadFileName);
                multipartFile.transferTo(saveFile);
                InputStream in = new FileInputStream(saveFile);

                docHosAttachFileVO.setHosUuid(uuid.toString());
                docHosAttachFileVO.setHosUploadPath(uploadFolderPath);

                if (checkImageType(saveFile)) {
                    docHosAttachFileVO.setHosImage(true);
                    FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + hosuploadFileName));
                    Thumbnailator.createThumbnail(in, thumbnail, 100, 100);
                    thumbnail.close();
                }
                in.close();

                //가비지 컬렉터 호출
                System.gc();
                //가비지 컬렉터가 포착한 해제 필드들을 모두 즉시 해제
                System.runFinalization();

                hosFileList.add(docHosAttachFileVO);
                log.info("---------------hosList------------------");
                log.info(String.valueOf(hosFileList.size()));
                log.info("---------------hosList end------------------");
            } catch (IOException e) {
                log.error(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                log.info("GIF 파일의 용량이 큽니다.");
            }
        }
        return hosFileList;
    }

    private String getFolder(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String now = sdf.format(date);

//        - 대신 각 디렉토리의 경로를 구분할 수 있도록 하기 위해서
//        replace()를 사용한다.
        return now.replace("-", "/");
    }

//    서버에 업로드된 파일은 시간이 걸리더라도 파일 자체가 이미지인지를 정확하게 체크한 뒤 저장해야 한다.
    private boolean checkImageType(File file){
        try {
            String contentType = Files.probeContentType(file.toPath());
            return contentType.startsWith("image");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @GetMapping("hosdisplay")
    @ResponseBody
    public ResponseEntity<byte[]> getFile(String hosFileName){
        File file = new File("C:/hosupload/" + hosFileName);
        log.info("Hospital file : " + file);
        HttpHeaders header = new HttpHeaders();
        ResponseEntity<byte[]> result = null;
        try {
            //png 파일이면 image/png타입, jpeg파일이면 image/jpeg 타입으로 설정
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping(value = "download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(String fileName){
        log.info("download file : " + fileName);
        Resource resource = new FileSystemResource("C:/hosupload/" + fileName);
        log.info("resource : " + resource);
        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();
//        UUID 제거
        String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);
        try {
            headers.add("Content-Disposition", "attachment; filename=" + new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }

    @PostMapping("hosdeleteFile")
    @ResponseBody
    public ResponseEntity<String> deleteFile(String fileName, String type){
        log.info("hosdeleteFile : " + fileName);
        try {
            fileName = URLDecoder.decode(fileName, "UTF-8");
            File file = new File("C:/hosupload/" + fileName);
            file.delete();
            if(type.equals("image")){
                //원본 삭제
                new File(file.getPath().replace("s_", "")).delete();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

}

















