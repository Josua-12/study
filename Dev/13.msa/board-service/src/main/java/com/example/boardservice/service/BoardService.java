package com.example.boardservice.service;

import com.example.boardservice.client.UserClient;
import com.example.boardservice.domain.Board;
import com.example.boardservice.domain.BoardRepository;
import com.example.boardservice.dto.BoardResponseDto;
import com.example.boardservice.dto.CreateBoardRequestDto;
import com.example.boardservice.dto.UserDto;
import com.example.boardservice.dto.UserResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserClient userClient;

    public BoardService(BoardRepository boardRepository, UserClient userClient) {
        this.boardRepository = boardRepository;
        this.userClient = userClient;
    }

    @Transactional      // 메서드 실행 중 예외 발생 시 롤백, 성공 시 커밋
    public void create(CreateBoardRequestDto createBoardRequestDto) {
        Board board = new Board(    // Board 엔티티 객체 생성 (DTO 데이터를 도메인 모델로 변환)
                createBoardRequestDto.getTitle(),       // DTO에서 제목 추출
                createBoardRequestDto.getContent(),     // DTO에서 내용 추출
                createBoardRequestDto.getUserId()       // DTO에서 작성자 ID 추출
        );
        this.boardRepository.save(board);   // JPA를 통해 DB에 게시글 저장 (INSERT 쿼리 실행)
    }

    public BoardResponseDto getBoard(Long boardId) { // 게시글 + 사용자 정보 통합 데이터 반환
        // board-service의 DB에서 게시글 조회
        Board board = boardRepository.findById(boardId)     // DB에서 ID로 게시글 조회 (SELECT 쿼리 실행)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")); //조회 결과 없으면 예외 발생

        // user-service로부터 사용자 정보 불러오기 (MSA간 HTTP 통신)
        // UserClient를 통해 간결하게 호출
        Optional<UserResponseDto> optionalUserResponseDto = userClient.fetchUser(board.getUserId());

        UserDto userDto = null;
        // 응답값 조합하기 (두 서비스의 데이터를 하나의 DTO로 통합)
        // UserDto 생성
        if (optionalUserResponseDto.isPresent()) {
            UserResponseDto userResponseDto = optionalUserResponseDto.get();      // 실제 값을 꺼내옴
            userDto = new UserDto(
                    userResponseDto.getUserId(),    // 사용자 ID
                    userResponseDto.getName()       // 사용자 이름
            );
        }


        // BoardResponseDto 생성
        BoardResponseDto boardResponseDto = new BoardResponseDto(
                board.getBoardId(),     //게시글 ID
                board.getTitle(),       //게시글 제목
                board.getContent(),     // 게시글 내용
                userDto // 작성자 정보 (user-service로부터 조회한 데이터)
        );

        return boardResponseDto;    //통합된 응답 DTO (응답 조합)
    }
}



















