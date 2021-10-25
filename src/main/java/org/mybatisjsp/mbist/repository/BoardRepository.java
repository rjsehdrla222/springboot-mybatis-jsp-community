package org.mybatisjsp.mbist.repository;

import org.mybatisjsp.mbist.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository {
    List<Board> getList(int num);
    void boardInsert(Board board);
    void boardUpdate(Board board);
    Board detailView(int id);
    void deleteContent(int id);
    void viewCnt(int id);
    int totalCount();
}
