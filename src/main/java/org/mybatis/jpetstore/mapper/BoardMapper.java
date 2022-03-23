package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.Board;

import java.util.List;

public interface BoardMapper {

    List<Board> getBoardList();

    void insertPost(Board board);

    Board getBoard(int bno);

    List<Board> getBoardListByBno(int bno);


}
