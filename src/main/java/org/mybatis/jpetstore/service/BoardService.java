    package org.mybatis.jpetstore.service;

    import org.mybatis.jpetstore.domain.Board;
    import org.mybatis.jpetstore.domain.Sequence;
    import org.mybatis.jpetstore.mapper.BoardMapper;


    import org.mybatis.jpetstore.mapper.SequenceMapper;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.List;

    @Service
    public class BoardService {
        private final BoardMapper boardMapper;

        public BoardService(BoardMapper boardMapper){
            this.boardMapper = boardMapper;
        }

        @Transactional
        public List<Board> getBoardList(){
            return boardMapper.getBoardList();
        }

        public Board getBoard(int bno){
            return boardMapper.getBoard(bno);
        }

        @Transactional
        public void insertPost(Board board){
            boardMapper.insertPost(board);
        }

        public List<Board> getBoardListByBno(int bno){
            return boardMapper.getBoardListByBno(bno);
        }



    }
