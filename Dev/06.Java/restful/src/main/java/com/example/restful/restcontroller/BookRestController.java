package com.example.restful.restcontroller;

import com.example.restful.entity.Book;
import com.example.restful.entity.BookImage;
import com.example.restful.entity.BookPayloadDTO;
import com.example.restful.entity.BookViewDTO;
import com.example.restful.service.BookImageService;
import com.example.restful.service.BookService;
import com.example.restful.util.ImageUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@RestController     // RESTFul 웹 서비스 컨트롤러 (@Controller + @ResponseBody)
@RequestMapping("/api")     // 기본 URL 경로
@Tag(name = "Book Controller", description = "책을 관리하는 컨트롤러")  //Swagger 문서화
public class BookRestController {

    private final BookService bookService;
    private final BookImageService bookImageService;

    @Value("${upload.path}")    // application.yml에서 업로드 경로 주입
    private String uploadPath;

    // 생성자 주입
    public BookRestController(BookService bookService, BookImageService bookImageService) {
        this.bookService = bookService;
        this.bookImageService = bookImageService;
    }

    @GetMapping("/test")
    @Tag(name = "User API")
    @Operation(summary = "User 조회", description = "User 정보를 조회합니다.")
    public String test() {
        return "Hello World!";      // JSON 형태로 응답
    }

    // 책 등록 API
    @PostMapping(value = "/books", consumes = "application/json",
                                        produces = "application/json")
    @ApiResponse(responseCode = "200", description = "Book added")
    @ApiResponse(responseCode = "400", description = "Please add valid name")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add an Book") // Swagger 문서화용
    public ResponseEntity<BookViewDTO>
            addBook(@Valid @RequestBody BookPayloadDTO bookPayloadDTO) {
        // @RequestBody: HTTP 요청 본문의 JSON을 BookPayloadDTO 객체로 자동 변환
        try {
            // DTO를 엔터티로 변환 (클라이언트 데이터 => DB 저장용 객체)
            Book book = new Book();     //새 Book 엔터티 생성
            book.setSubject(bookPayloadDTO.getSubject());
            book.setPrice(bookPayloadDTO.getPrice());
            book.setAuthor(bookPayloadDTO.getAuthor());
            book.setPage(bookPayloadDTO.getPage());
            // 데이터베이스 저장
            book = bookService.save(book);

            // 엔터티를 응답 DTO로 변환 (DB 데이터 => 클라이언트 응답용 객체)
            BookViewDTO bookViewDTO = new BookViewDTO(
                    book.getId(),           //자동 생성된 ID
                    book.getSubject(),
                    book.getPrice(),
                    book.getAuthor(),
                    book.getPage(),
                    book.getCreatedAt()     //생성 시간
            );
            return ResponseEntity.ok(bookViewDTO);      // HTTP 200 OK 상태코드와 함께
                                                        // bookViewDTO를 JSON으로 변환
        } catch (Exception e) {
            e.printStackTrace();   //(디버깅용)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }                               // 응답 본문없이 상태코드만 반환
    }

    // ======전체 책 목록 조회 API=====
    @GetMapping(value = "/books", produces = "application/json")
    @ApiResponse(responseCode = "200", description = "List of books")
    @Operation(summary = "List book API")
    public List<BookViewDTO> books() {
        // Spring이 자동으로 JSON 배열로 변환
        List<BookViewDTO> books = new ArrayList<>();
        for(Book book : bookService.findAll()) {
            //Entity -> DTO 변환 (Entity 직접 반환 안함-불필요한 정보 노출, DB구조 노출)
            books.add(new BookViewDTO(
                    book.getId(),
                    book.getSubject(),
                    book.getPrice(),
                    book.getAuthor(),
                    book.getPage(),
                    book.getCreatedAt()
            ));
        }
        return books;       //JSON 배열로 자동 변환되어 반환
    }


    // ======특정 책 조회 API=====
    @GetMapping(value = "/books/{id}", produces = "application/json")
    @ApiResponse(responseCode = "200", description = "id에 해당하는 책정보를 출력")
    @Operation(summary = "book id API")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Book> optionalBook = bookService.findById(id);
        Book book;

        if (optionalBook.isPresent()) {
            book = optionalBook.get();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // DTO로 변환하여 반환
        BookViewDTO bookViewDTO = new BookViewDTO(
               book.getId(),
               book.getSubject(),
               book.getPrice(),
               book.getAuthor(),
               book.getPage(),
               book.getCreatedAt()
        );
        return ResponseEntity.ok(bookViewDTO);
            // HTTP 200 OK + bookViewDTO를 JSON으로 변환하여 응답.
    }


    // ======특정 책 수정 API=====
    @PutMapping(value = "/books/{id}", consumes = "application/json",
            produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "204", description = "Book Update")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @Operation(summary = "Update a Book")
    public ResponseEntity<?> updateBook(@Valid @RequestBody BookPayloadDTO payloadDTO,
                                        @PathVariable Long id) {
        // @RequestBody : 요청 본문의 JSON을 BookPayloadDTO로 자동 변환

        // 수정할 책 조회
        Optional<Book> optionalBook = bookService.findById(id);
        // DB에서 해당 ID의 책 조회 (select * from where id = ?)

        Book book;      // 수정할 Book 엔터티를 담을 변수
        if (optionalBook.isPresent()) {
            book = optionalBook.get();      //실제 Book 객체 추출
        } else {
            //책이 존재하지 않는 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // 수정 데이터 적용 (기존 엔터티의 필드값 변경)
        book.setSubject(payloadDTO.getSubject());       //제목 수정
        book.setPrice(payloadDTO.getPrice());           //가격 수정
        book.setAuthor(payloadDTO.getAuthor());         //저자 수정
        book.setPage(payloadDTO.getPage());             //페이지 수정
        //id와 createdAt은 수정하지 않음(보전되어야 할 값)

        //저장 (JPA는 수정시 save 메소드 사용)
        book = bookService.save(book);

        // Entity => DTO 변환하여 응답 준비
        BookViewDTO bookViewDTO = new BookViewDTO(
                book.getId(),           //ID 변경 없음
                book.getSubject(),      //수정된 제목
                book.getPrice(),        //수정된 가격
                book.getAuthor(),       //수정된 저자
                book.getPage(),         //수정된 페이지
                book.getCreatedAt()     // 생성일 변경 안됨
        );

        return ResponseEntity.ok(bookViewDTO);
                // HTTP 200 OK + 수정된 책 정보를 JSON으로 응답
    }

    // === 책 삭제 API ===
    @DeleteMapping("/books/{id}")   // {id}: 삭제할 책의 ID를 URL에서 받음
    @Operation(summary = "Delete a Book")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        // DB에서 삭제할 책 조회 (SELECT * FROM book WHERE id = ?) //삭제전 존재 여부 확인
        Optional<Book> optionalBook = bookService.findById(id);
        Book book;      // 삭제할 Book 엔터티를 담을 변수
        if (optionalBook.isPresent()) { // 책이 존재하는 경우
            book = optionalBook.get();  // Optional에서 실제 Book 객체 추출
        } else {    // 책이 존재하지 않는 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        bookService.delete(book);       // 데이터베이스에서 삭제 DELETE FROM book WHERE id = ? 실행
        // CascadeType.ALL 설정되어 있으면 연관되어 있는 BookImage도 함께 삭제됨

        ImageUtil.deleteFolder(uploadPath, id);     // 관련 이미지 삭제, 파일 시스템/uploads/{id} 폴더와 내부 파일들 삭


        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null); //202
    }


    // === 이미지 업로드 API ===
    @PostMapping(value = "/{book_id}/{type}/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<?> imagesUpload(
            //@RequestPart : multipart요청의 특정 파트를 받음. MultipartFile[]: 여러 파일 동시 업로드 가능
            @RequestPart(required = true) MultipartFile[] files,
            @PathVariable Long book_id,   // 책 ID, {book_id} 추출 // /api/5/1/upload
            @PathVariable int type) {     // 이미지 타입 (1:썸네일, 2: 일반 등)

        // 책 존재 여부 확인 (SELECT * FROM book WHERE id = ?)
        Optional<Book> optionalBook = bookService.findById(book_id);
        Book book;      // 조회된 Book 엔터티를 담을 변수

        if(optionalBook.isPresent()) {
            book = optionalBook.get();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 성공/실패 파일명 저장 리스트
        List<String> successImageName = new ArrayList<>();  // 업로드 성공한 파일명들을 저장
        List<String> errorImageName = new ArrayList<>();  // 업로드 실패한 파일명들을 저장

        // 각 파일 처리
        Arrays.asList(files).stream().forEach(file -> {
            String contentType = file.getContentType(); //파일의 MIME 타입 가져오기 ("image/png, image/jpeg")

            //이미지 파일 형식 검증
            if (contentType.equals("image/png") ||
                    contentType.equals(("image/jpg")) ||
                    contentType.equals(("image/jpeg"))) {
                // 정상적인 경우 원본 파일명 저장
                successImageName.add(file.getOriginalFilename());

                try {
                    String fileName = file.getOriginalFilename();   // 원본 파일명 저장 ("photo.jpg")
                    // 10자리 랜덤 문자열 생성 (중복 방지) - 10자리, 문자 포함, 숫자 포함 ("a3Bb910Bct")
                    String generatedString = RandomStringUtils.random(10, true, true);
                    // 새파일명 생성
                    String newImageName = generatedString + fileName;  //  "a3Bb910Bctphoto.jpg"
                    if (type == 1) {
                        newImageName = "thumb_" + generatedString + fileName; //  "thumb_a3Bb910Bctphoto.jpg"
                    }

                    // 파일 저장 경로 생성  /uploads/1/thumb_a3Bb910Bctphoto.jpg
                    String absoluteFileLocation
                            = ImageUtil.makePath(uploadPath, newImageName, book_id);
                    Path path =Paths.get(absoluteFileLocation);   // Path 객체로 반환

                    // 일반 이미지는 바로 저장
                    if (type !=1) {
                        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    }

                    // 데이터베이스에 이미지 정보 저장
                    BookImage bookImage = new BookImage();  //새 BookImage엔터티 생성
                    bookImage.setOriginalFileName(fileName);    // 원본 파일명 저장
                    bookImage.setFileName(newImageName);    // 새저장된 파일명 저장
                    bookImage.setBook(book);    // Book 엔터티와 연관관계 설정 (외래키)
                    bookImage.setType(type);    // 이미지 타입 저장 (1:썸네일, 2:일반등)
                    bookImageService.save(bookImage);       // DB에 저장 (INSERT INTO book_image...)

                    // 썸네일 생성 및 저장
                    if (type == 1) {    // 썸네일 타입인 경우만 처리
                        // 원본이미지를 300px로 리사이징 이미지
                        BufferedImage thumbnail = ImageUtil.getThumbnail(file, 300);
                        // 섬네일 이미지 저장 경로 생성
                        String thumbnailLocation = ImageUtil.makePath(uploadPath, newImageName, book_id);
                        // BufferedImage를 파일로 저장
                        ImageIO.write(thumbnail,
                              file.getContentType().split("/")[1], new File(thumbnailLocation));

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    errorImageName.add(file.getOriginalFilename()); //실패한 파일명을 에러 리스트에 추가
                }
            } else {
                // 이미지가 아닌 파일
                errorImageName.add(file.getOriginalFilename());  // 지원하지 않는 형식의 파일명을 에러 리스트에 추가
            }
        });

        // 결과를 담을 HashMap 생성
        HashMap<String, List<String>> result = new HashMap<>();
        result.put("SUCCESS", successImageName);  //성공한 파일명 리스트 저장
        result.put("ERRORS", errorImageName);  // 실패한 파일명 리스트 저장

        //최종 응답 구조 생성 (리스트안에 HashMap)
        List<HashMap<String, List<String>>> response = new ArrayList<>();
        response.add(result);  //결과 Hashmap을 리스트에 추가
        return ResponseEntity.ok(response); // HTTP 200와 함께 JSON 형태로 응답
    }


    // === 이미지 삭제 API ===
    @DeleteMapping(value = "/{image_id}/delete")
    public ResponseEntity<String> deletePhoto(@PathVariable Long image_id) {
        try {
            // DB에서 삭제할 이미지 정보 조회
            Optional<BookImage> optionalBookImage = bookImageService.findById(image_id);

            if (optionalBookImage.isPresent()) {    //이미지 정보가 존재한다면
                BookImage bookImage = optionalBookImage.get();  //실제 BookImage 객체 추출

                // 파일시스템에서 이미지 삭제 (디스크에서 삭제)
                ImageUtil.deleteImage(uploadPath,
                        bookImage.getBook().getId(), bookImage.getFileName());

                // 데이터베이스에서 삭제
                bookImageService.delete(bookImage);

                return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
            } else {
                //이미지 정보가 없는 경우
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // === 이미지 조회 API ===            예) /api/5/imageSrc
    @GetMapping("{image_id}/imageSrc")  // /api/{image_id}/imageSrc
    public ResponseEntity<byte[]> getimage(@PathVariable("image_id") Long image_id) throws IOException {
        Optional<BookImage> optionalBook = bookImageService.findById(image_id); // DB에서 이미지 정보 조회

        byte[] imageBytes;  // 이미지 파일의 바이트 배열을 담을 변수

        if (optionalBook.isPresent()) {     // 이미지 정보가 존재하는 경우
            BookImage bookImage = optionalBook.get();   // 실제 BookImage 객체 추출
            // 이미지 파일 경로 가져오기   // 파일 시스템에서 실제 파일 경로 구성 예) /uploads/1/thumb_a#fF15uy2photo.jpg
            Path imagePath = ImageUtil.getFileResource(uploadPath,
                                                       bookImage.getBook().getId(),
                                                       bookImage.getFileName());
            // 바이트 배열로 읽기 (대용량 파일의 경우 메모리 문제 발생 가능)
            imageBytes = Files.readAllBytes(imagePath);

            // 파일 확장자 추출
            String fileName = bookImage.getFileName();  // 저장된 파일명 가져오기
            String fileExtension = "";      // 확장자를 담을 변수 초기화
            /*
                "photo.jpg"     => lastDotIndex = 5, length = 9
                                   5 < 8 (9-1)
                ".gitignore"    => lastDotIndex = 5, length = 6
                                   5 < 5 (6-1)      (확장자가 없음)
                "README"
             */
            int lastDotIndex =
                    fileName.lastIndexOf(".");  // 파일명에서 마지막 . 위치 찾기
            if (lastDotIndex > 0 && lastDotIndex < fileName.length()) {
                fileExtension = // '.' 다음 문자부터 끝까지 추출하고 소문자로 변환
                    fileName.substring(lastDotIndex+1).toLowerCase();
            }

            // Content-Type 설정
            MediaType mediaType = null;
            switch (fileExtension) {
                case "png":
                    mediaType = MediaType.IMAGE_PNG;      // PNG 이미지 : "image/png"
                    break;
                case "jpg":
                case "jpeg":
                    mediaType = MediaType.IMAGE_JPEG;       // PNG 이미지 : "image/jpeg"
                    break;
                case "gif":
                    mediaType = MediaType.IMAGE_GIF;        // GIF 이미지 : "image/gif"
                    break;
            }

            // HTTP 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(mediaType);  // Content-Type 헤더 설정 (브라우저가 이미지로 인식하도록)
            // HTTP 200 OK + 이미지 바이트 + 헤어 정보 반환 => 브라우저가 이를 이미지로 랜더링
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {    // 이미지가 정보 없는 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}


















