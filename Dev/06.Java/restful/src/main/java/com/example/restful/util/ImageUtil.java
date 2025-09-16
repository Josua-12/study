package com.example.restful.util;

import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// 이미지 처리 관련 유틸리티 클래스  (static 메소드들로 구성)
public class ImageUtil {

    // 단일 이미지 삭제                    기본 업로드 경로     책ID (풀더명)   삭제할 파일명
    public static boolean deleteImage(String uploadPath, Long book_id, String fileName) {
        try {
            //삭제할 파일의 절대 경로 생성
            // uploadPath + "\\" + book_id + "\\" + fileName
            File file = new File(uploadPath  + book_id + "\\" + fileName);

            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // 업로드 경로 생성 메소드
    public static String makePath(String uploadPath, String fileName, Long book_id) throws IOException {
        // /uploads/5/ -- 각 책마다 별도 폴더로 이미지 처리
        String path = uploadPath + book_id;
        System.out.println("========================" + path);

        //중간 경로 포함 모든 디렉토리 생성
        Files.createDirectories(Paths.get(path));

        return new File(path).getAbsolutePath() +"\\"+ fileName;
    }

    // 썸네일 생성 메소드
    // 리사이징된 BufferedImage 객체 (반환타입)                          //썸네일 목표 너비 (픽셀)
    public static BufferedImage getThumbnail(MultipartFile originFile, Integer width) throws IOException {
        BufferedImage thumbImg = null;      // 썸네일 이미지를 담을 변수
        BufferedImage img = ImageIO.read(originFile.getInputStream());  // 원본 이미지 읽기
        thumbImg = Scalr.resize(img,
                Scalr.Method.AUTOMATIC, // 리사이징 알고리즘 최적 알고리즘 자동 선택
                Scalr.Mode.AUTOMATIC, // 가로/세로 비율 자동 결정
                width,  // 비율에 맞춰 자동 계산됨
                Scalr.OP_ANTIALIAS);  // 계단 현상 제거로 부드러운 이미지 생성
        return thumbImg;            // 메모리상의 이미지이므로 별도 파일로 저장 필요
    }

    // 폴더 전체 삭제 (책 삭제 시 사용) // uploadPath : 기본 업로드 경로, book_id : 삭제할 책 ID(폴더명)
    public static boolean deleteFolder(String uploadPath, Long book_id) {
        File directory = new File(uploadPath + book_id);    // 예 : "/uploads/5"

        if (directory.exists()) {   //exists() : 존재 여부 확인  => directory.exists() : 디렉토리 존재 여부 확인
            return false;   // 디렉토리 없음
        }

        try {
            // 폴더 내 모든 파일 삭제
            File[] files = directory.listFiles();   // 디렉토리 내 모든 파일/하위 디렉토리 배열 반환
            if (files != null) {
                for (File file : files) {
                    if (!file.delete()) {   // 파일 삭제 시도 // 하나라도 실패하면 전체 작업 중단
                        return false;
                    }
                }
            }

            // 모든 파일 삭제 후 폴더 삭제
            return directory.delete();      // 빈 디렉토리 삭제
        } catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            return false;
        }
    }

    // 이미지 파일 경로 가져오기
    public static Path getFileResource(String uploadPath, Long book_id, String fileName) {
        // 전체 파일 경로 조합 -- "\\" 사용 (File.seperator 권장) "/uploads/5\...jpg"
        String location = uploadPath + book_id + "\\" + fileName;
        File file = new File(location); // File 객체 생성

        if (file.exists()) { // 파일 존재 여부 확인
            Path path = Paths.get(file.getAbsolutePath());  // 절대 경로 문자열 => Path 객체로 변환
            return path;    // 파일이 존재하면 Path 경로 반환
        } else {
            return null;    // 파일이 없으면 null 반환
        }
    }
}
