package org.mybatisjsp.mbist.service;

import lombok.RequiredArgsConstructor;
import org.mybatisjsp.mbist.domain.Board;
import org.mybatisjsp.mbist.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;
    int a;

    public List<Board> getList(int num) {
        if (num == 1) { a = 0; }
        else { a = (num-1) * 10; }
        return repository.getList(a);
    }

    public void boardInsert(Board board) {
        repository.boardInsert(board);
    }

    public void boardUpdate(Board board) {
        repository.boardUpdate(board);
    }

    public Board detailView(int id) {
        return repository.detailView(id);
    }

    public void viewCnt(int id) {
        repository.viewCnt(id);
    }

    public void deleteContent(int id) {
        repository.deleteContent(id);
    }

    public int[] totalCount() {
        int a = repository.totalCount();
        int b = (a / 10) + 1;
        int[] arr = {a, b};
        return arr;
    }
}