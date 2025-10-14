package com.library.controller;

import com.library.dto.board.BoardDetailDTO;
import com.library.dto.board.BoardListDTO;
import com.library.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/*
    게시글 Controller
        - 게시글 관련 HTTP 요청을 처리하고 뷰를 반황함
        - URL 매핑 : /boards
 */

@Controller                     // Spring MVC Controller로 등록
@RequestMapping("/boards")      // 기본 URL 매핑 : /boards
@RequiredArgsConstructor        // final 필드에 대한 생성자 자동 생성 (DI)
public class BoardController {

    private final BoardService boardService;        // 게시글 Service (DI)

    /*
        게시글 목록 페이지
            - ACTIVE 상태의 게시글 목록을 페이징하여 조회함
            - View와 로직 분리 원칙에 따라 페이징 계산은 Controller 에서 처리함
            - 작성일 최신순 정렬로 인해 id가 순차적이지 않을 수 있음

        1-based 페이징 시스템 (URL : page 1부터 시작)
            URL : GET /board?page=1&size=10

        페이징 그룹 개념
            - 한 번에 10개의 페이지 번호만 표시 (예: [1][2]...[10], [11][12]...[20])
            - 전체 115개 게시글, 페이지당 10개 -> 총 12페이지
            - 그룹 1 : 1~10 페이지 표시
            - 그룹 2 : 11~12 페이지 표시
     */

    @GetMapping
    public String list(
            @RequestParam(defaultValue = "1") int page,     //페이지 번호 (기본값:0)
            @RequestParam(defaultValue = "10") int size,    //한 페이지에 보여줄 게시글 갯수 (기본값: 10개)
            Model model                                     //뷰에 데이터 전달용 Model
    ) {
        // Service를 통해 게시글 목록 조회 (page -1을 전달하여 0-based로 변환)
        Page<BoardListDTO> boards = boardService.getBoardList(page -1, size);

        int totalPages = boards.getTotalPages();    // 전체 페이지 수

        int pageGroupSize = 10;     // 한 그룹에 표시할 페이지 버튼 수
        int currentGroup = (page - 1) / pageGroupSize;  // 현재 페이지가 속한 그룹 번호

        int startPage = currentGroup * pageGroupSize + 1;   // 그룹의 시작 페이지 번호
        int endPage = Math.min(startPage + pageGroupSize -1, totalPages);    // 그룹의 종료 페이지 번호

        boolean hasPrevGroup = startPage > 1;   // [이전 그룹] 버튼 표시 여부
        int prevGroupPage = startPage - 1;      // 이전 그룹의 마지막 페이지

        boolean hasNextGroup = endPage < totalPages;    // [다음 그룹] 버튼 표시 여부
        int nextGroupPage = endPage + 1;    // 다음 그룹의 첫 페이지

        // Model 데이터 추가 (Thymeleaf로 전달)
        model.addAttribute("boards", boards);           // 게시글 목록
        model.addAttribute("currentPage", page);        // 현재 페이지 번호 (1-based)
        model.addAttribute("totalPages", totalPages);   // 전체 페이지 수
        model.addAttribute("totalElements", boards.getTotalElements()); // 전체 게시글 수

        model.addAttribute("startPage", startPage); // 그룹 시작 페이지 (1-based)
        model.addAttribute("endPage", endPage);     // 그룹 종료 페이지 (1-based)

        model.addAttribute("hasPrevGroup", hasPrevGroup);
        model.addAttribute("hasNextGroup", hasNextGroup);
        model.addAttribute("prevGroupPage", prevGroupPage); // 이전 그룹으로 이동 시 페이지 번호
        model.addAttribute("nextGroupPage", nextGroupPage); // 다음 그룹으로 이동 시 페이지 번호


        return "board/list";    // 게시글 목록 뷰
    }

    @GetMapping("/{id}")
    public String detail(
            @PathVariable Long id,                      // URL의 {id}를 메서드 파라미터로 바인딩
            @RequestParam(defaultValue = "1") int page,  // 페이지 번호
            Model model
    ) {
        // Service를 통해 게시글 상세 정보 조회 (조회수 자동 증가)
        BoardDetailDTO board = boardService.getBoard(id);

        model.addAttribute("board", board);
        model.addAttribute("page", page);   // 목록으로 돌아갈 페이지 번호

        return "board/detail";      // 게시글 상세 뷰
    }

}
