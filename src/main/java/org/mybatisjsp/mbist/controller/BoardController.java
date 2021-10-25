package org.mybatisjsp.mbist.controller;

import lombok.RequiredArgsConstructor;
import org.mybatisjsp.mbist.domain.Board;
import org.mybatisjsp.mbist.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value = {"/page={num}", "/"})
    public String home(Model model, @PathVariable(required = false) Integer num) {
        if (num == null) num = 1;
        List list = boardService.getList(num);
        int[] totalCount = boardService.totalCount();
        int total = totalCount[0];
        int page = totalCount[1];
        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("list", list);
        return "index";
    }

    @GetMapping("/update/{id}")
    public String contentUpdate(HttpServletRequest request, @PathVariable int id, Model model) throws Exception {
        if (request.getHeader("REFERER") == null){
            return "redirect:/errors/2";
        }
        model.addAttribute("detail", boardService.detailView(id));
        return "update";
    }

    @PostMapping("/update/{id}")
    public String contentUpdated(HttpServletRequest request, @PathVariable Long id) throws Exception {
        Board board = new Board();
        board.setId(id);
        board.setContent(request.getParameter("content"));
        //board.setContent(request.getParameter("content").replace("\r\n", "<br>"));
        board.setTitle(request.getParameter("title"));
        boardService.boardUpdate(board);
        return "redirect:/detail/{id}";
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @RequestMapping("/createInsert")
    public String createInsert(HttpServletRequest request) throws Exception {
        Board board = new Board();
        board.setTitle(request.getParameter("title"));
        board.setContent(request.getParameter("content"));
        //board.setContent(request.getParameter("content").replace("\r\n", "<br>"));
        board.setName(request.getParameter("name"));
        board.setPw(request.getParameter("pw"));
        boardService.boardInsert(board);
        return "redirect:/";
    }

    @GetMapping(value = "/check/{id}")
    public String pwTest(@PathVariable int id, Model model) {
        model.addAttribute("detail", boardService.detailView(id));
        return "check";
    }

    @GetMapping(value = "/deleteCheck/{id}")
    public String deletePw(@PathVariable int id, Model model) {
        model.addAttribute("detail", boardService.detailView(id));
        return "delete";
    }

    @PostMapping(value = "/check/{id}")
    public String pwTest(HttpServletRequest request, @PathVariable int id) {
        String pw = request.getParameter("pw");
        Board p1 = boardService.detailView(id);
        String p2 = p1.getPw();

        if(pw.equals(p2)) {
            return "redirect:/update/{id}";
        }
        else {
            return "redirect:/errors/1";
        }
    }

    @GetMapping("errors/1")
    public String errorView1(Model model) {
        model.addAttribute("message", "비밀번호가 올바르지 않습니다.");
        return "../errors/message";
    }

    @GetMapping("errors/2")
    public String errorView2(Model model) {
        model.addAttribute("message", "비정상적인 접근입니다.");
        return "../errors/message";
    }

    @GetMapping(value = "/detail/{id}")
    public String viewDetail(@PathVariable int id, Model model) {
        boardService.viewCnt(id);
        model.addAttribute("detail", boardService.detailView(id));
        return "detail";
    }

    @PostMapping("/delete/{id}")
    public String deleteContent(@PathVariable int id, HttpServletRequest request) {
        String pw = request.getParameter("pw");
        Board p1 = boardService.detailView(id);
        String p2 = p1.getPw();
        if(pw.equals(p2)) {
            boardService.deleteContent(id);
            return "redirect:/";
        }
        else {
            return "redirect:/errors/1";
        }
    }
}
