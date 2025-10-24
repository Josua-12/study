package com.library.controller;

import com.library.dto.board.BoardCreateDTO;
import com.library.dto.board.BoardDetailDTO;
import com.library.dto.board.BoardListDTO;
import com.library.dto.board.BoardUpdateDTO;
import com.library.entity.board.BoardCategory;
import com.library.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

/*
    게시글 Controller
        - 게시글 관련 HTTP 요청을 처리하고 뷰를 반환함
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
            - View와 로직 분리 원칙에 따라 페이징 계산은 Controller에서 처리함
            - 작성일 최신순 정렬로 인해 ID가 순차적이지 않을 수 있음

        1-based 페이징 시스템 (URL: page=1부터 시작)
            - URL : GET /boards?page=1&size=10

        페이징 그룹 개념
            - 한 번에 10개의 페이지 번호만 표시 (예: [1][2]...[10], [11][12]...[20])
            - 전체 115개 게시글, 페이지당 10개 -> 총 12페이지
            - 그룹1 : 1~10페이지 표시
            - 그룹2 : 11~12페이지 표시
     */
    @GetMapping
    public String list(
            @RequestParam(defaultValue = "1") int page,     //조회할 페이지 번호 (기본값:1)
            @RequestParam(defaultValue = "10") int size,    //페이지당 보여줄 게시글 수 (기본값: 10개)
            Model model                                     //뷰에 전달할 데이터를 담는 Model 객체
    ) {
        // Service를 통해 게시글 목록 조회 (page-1을 전달하여 0-based로 변환)
        Page<BoardListDTO> boards = boardService.getBoardList(page -1, size);


        int totalPages = boards.getTotalPages();    // 전체 페이지 수

        int pageGroupSize = 10;     // 한 그룹에 표시할 페이지 버튼 개수
        int currentGroup = (page - 1) / pageGroupSize;  // 현재 페이지가 속한 그룹 번호

        int startPage = currentGroup * pageGroupSize + 1;  // 그룹의 시작 페이지 번호
        int endPage = Math.min(startPage + pageGroupSize -1, totalPages); // 그룹의 종료 페이지 번호

        boolean hasPrevGroup = startPage > 1;    // [이전 그룹] 버튼 표시 여부
        int prevGroupPage = startPage - 1;   // 이전 그룹의 마지막 페이지

        boolean hasNextGroup = endPage < totalPages;  // [다음 그룹] 버튼 표시 여부
        int nextGroupPage = endPage + 1;   // 다음 그룹의 첫 페이지

        // Model 데이터 추가 (Thymeleaf로 전달)
        model.addAttribute("boards", boards);       // 게시글 목록
        model.addAttribute("currentPage", page);    // 현재 페이지 번호(1-based)
        model.addAttribute("totalPages", totalPages);  // 전체 페이지 수
        model.addAttribute("totalElements", boards.getTotalElements()); // 전체 게시글 수

        model.addAttribute("startPage", startPage);  //그룹 시작 페이지 (1-based)
        model.addAttribute("endPage", endPage);      //그룹 종료 페이지 (1-based)

        model.addAttribute("hasPrevGroup", hasPrevGroup);
        model.addAttribute("hasNextGroup", hasNextGroup);
        model.addAttribute("prevGroupPage", prevGroupPage); //이전 그룹으로 이동시 페이지 번호
        model.addAttribute("nextGroupPage", nextGroupPage); //다음 그룹으로 이동시 페이지 번호

        return "board/list";        // 게시글 목록 뷰
    }

    @GetMapping("/{id}")
    public String detail(
            @PathVariable Long id,          // URL의 {id}를 메서드 파라미터로 바인딩
            @RequestParam(defaultValue = "1") int page,  // 페이지 번호
            Model model
    ) {
        // Service를 통해 게시글 상세 정보 조회 (조회수 자동 증가)
        BoardDetailDTO board =  boardService.getBoard(id);

        model.addAttribute("board", board);
        model.addAttribute("page", page);  //목록으로 돌아갈 페이지 번호

        return "board/detail";  // 게시글 상세 뷰
    }

    /*
        게시글 작성 폼 페이지
            - 새 게시글 작성하기 위한 폼을 표시함
            - 카테고리 목록을 함께 전달하여 선택 가능하도록 함
            - URL : GET /boards/new
     */
    @GetMapping("/new")
    public String createForm(Model model) {
        //빈 DTO 객체 생성 (Thymeleaf Form 바인딩용)
        model.addAttribute("board", new BoardCreateDTO());

        //카테고리 목록 전달 (select 옵션으로 선택)
        model.addAttribute("categories", BoardCategory.values());

        // 작성 모드 플래그 (false 또는 명시적으로 추가하지 않음)
        model.addAttribute("isEditMode", false);

        return "board/form";        //게시글 작성 폼 뷰

    }

    /*
        게시글 작성 처리
     */
    @PostMapping
    public String create(
           @Valid @ModelAttribute BoardCreateDTO boardCreateDTO, //@Valid: 검증 활성화, @ModelAttribute: 폼 데이터 바인딩
           BindingResult bindingResult, // @Valid 검증 결과, Thymeleaf #fields 객체 사용 가능
           Principal principal,  //Spring Security 로그인 사용자, principal.getName() 이메일 획득
           Model model,     //View에 전달할 데이터
           RedirectAttributes redirectAttributes    // 리다이렉트 시 일회용 데이터 전달
    ) {
        // 검증 실패 처리
        if (bindingResult.hasErrors()) {  //@Valid로 검증한 결과 에러 있음
            model.addAttribute("categories", BoardCategory.values());  //카테고리 목록 재추가
            model.addAttribute("isEditMode", false);  // 작성모드
            return "board/form";  // 폼으로 돌아감 (필드별 에러 메시지 포함)
        }

        try {
            // 성공 처리
            String userEmail = principal.getName();  //현재 로그인 사용자 이메일 획득
            Long boardId = boardService.createBoard(boardCreateDTO, userEmail); // 게시글 생성 (DB 저장)

            redirectAttributes.addFlashAttribute("success", "게시글이 작성되었습니다."); //성공 메시지

            return "redirect:/boards/" +boardId;  //detail.html로 이동 (success 메시지 표시)
        } catch (Exception e) {
            // 예외 발생 처리
            // 게시글 생성 중 예외 발생 (DB 오류, 파일 업로드 오류 등) // 에러 메시지를 모델에 추가
            model.addAttribute("error", "게시글 작성 중 오류가 발생했습니다. " + e.getMessage());
            model.addAttribute("errorType", "system_error");
            // 카테고리 목록 다시 추가 (폼 재표시용)
            model.addAttribute("categories", BoardCategory.values());
            model.addAttribute("isEditMode", false);  //작성 모드
            return "board/form"; //form.html로 돌아감 (에러 메시지 표시)
        }

    }

    /*
    게시글 삭제 처리
        - 코드 흐름
            - 1) Principal에서 현재 로그인한 사용자 이메일 획득
              2) Service의 deleteBoard() 호출 (권한 검증 포함)
              3) 성공 메시지를 FlashAttribute로 추가
              4) 게시글 목록으로 리다이렉트
     */
    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id,      /* URL의 {id}를 파라미터로 바인딩 */
            Principal principal,        // 현재 로그인한 사용자 정보
            RedirectAttributes redirectAttributes   //리다이렉트 시 메시지 전달용
    ) {
        try {
            // 현재 로그인한 사용자 이메일 획득
            String userEmail = principal.getName();
            boardService.deleteBoard(id, userEmail); // Service를 통해 게시글 삭제

            redirectAttributes.addFlashAttribute("success", "게시글이 삭제되었습니다.");

            return "redirect:/boards";

        } catch (RuntimeException e) {
            // 예외 발생시 에러 메시지와 함께 목록으로
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/boards";
        }
    }

    /*
        게시글 수정 폼 페이지
            - 기존 게시글 정보를 조회하여 폼에 표시함
            - 작성자 본인만 접근 가능
            - URL : GET /boards/{id}/edit
     */
    @GetMapping("/{id}/edit")
    public String editForm(
            @PathVariable Long id,
            Principal principal,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        try {
            // 현재 로그인한 사용자 이메일
            String userEmail = principal.getName();

            // 수정할 게시글 조회 (권한 검증 포함)
            BoardDetailDTO board = boardService.getBoardForEdit(id, userEmail);

            // BoardUpdateDTO로 변환하여 폼에 바인딩
            BoardUpdateDTO updateDTO = BoardUpdateDTO.builder()
                    .title(board.getTitle())
                    .content(board.getContent())
                    .category(board.getCategory())
                    .build();
            model.addAttribute("board", updateDTO);
            model.addAttribute("boardId", id);
            model.addAttribute("existingFiles", board.getFiles());  // 기존 첨부파일 목록
            model.addAttribute("categories", BoardCategory.values());
            model.addAttribute("isEditMode", true); // 수정 모드 플래그

            return "board/form";

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/boards/" + id;
        }
    }

    /*
        게시글 수정 처리
            - 제목, 내용, 카테고리 수정
            - 기존 파일 삭제 및 새 파일 추가
            - 작성자 본인만 수정 가능 (권한 검증)
            - URL : PUT /boards/{id}
     */
    @PutMapping("/{id}")
    public String update(
           @PathVariable Long id,
           @Valid @ModelAttribute("board") BoardUpdateDTO updateDTO,
           BindingResult bindingResult,
           Principal principal,
           Model model,
           RedirectAttributes redirectAttributes
    ) {
        // 1. 유효성 검증 실패 처리
        if (bindingResult.hasErrors()) {
            model.addAttribute("boardId", id);
            model.addAttribute("categories", BoardCategory.values());
            model.addAttribute("isEditMode", true);

            // 기존 첨부 파일 목록 다시 조회
            try {
                BoardDetailDTO board = boardService.getBoardForEdit(id, principal.getName());
                model.addAttribute("existingFiles", board.getFiles());
            } catch (Exception e) {
                // 파일 목록 조회 실패해도 폼은 표시
            }

            return "board/form";
        }

        // 2. 수정 처리
        try {
            // 현재 로그인한 사용자 이메일
            String userEmail = principal.getName();
            // 게시글 수정 처리
            boardService.updateBoard(id, updateDTO, userEmail);

            redirectAttributes.addFlashAttribute("success", "게시글이 수정되었습니다.");
            return "redirect:/boards/" + id;

        } catch (Exception e) {
            model.addAttribute("error", "게시글 수정 중 오류가 발생했습니다." + e.getMessage());
            model.addAttribute("errorType", "system_error");
            model.addAttribute("boardId", id);
            model.addAttribute("categories", BoardCategory.values());
            model.addAttribute("isEditMode", true);

            // 기존 파일 목록 다시 조회
            try {
                BoardDetailDTO board = boardService.getBoardForEdit(id, principal.getName());
                model.addAttribute("existingFiles", board.getFiles());
            } catch (Exception ex) {
                // 파일 목록 조회 실패해도 폼은 표시
            }
        }

        return "board/form";
    }
}



















