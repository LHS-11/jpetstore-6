package org.mybatis.jpetstore.web.actions;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.mybatis.jpetstore.domain.Account;
import org.mybatis.jpetstore.domain.Board;
import org.mybatis.jpetstore.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BoardActionBean extends AbstractActionBean{

    private static final String BOARD_LIST="/WEB-INF/jsp/board/BoardList.jsp";
    private static final String VIEW_POST="/WEB-INF/jsp/board/ViewPost.jsp";
    private static final String WRITE_POST="/WEB-INF/jsp/board/WritePostForm.jsp";

    @SpringBean
    private transient BoardService boardService;



    private Board board = new Board();
    private Account account = new Account();


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    private int bno;
    private String userid;
    private String title;
    private String contents;
    private List<Board> boardList;
    private Board post;

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getUserid() {
        return userid;
    }

    public void setBoardList(List<Board> boardList) {
        this.boardList = boardList;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public void setUserid(String userid) {
        account.setUsername(userid);
    }
    public String getUserid(String userid){
        return account.getUsername();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Board getBoard() {
        return board;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public List<Board> getBoardList(){
        return boardList;
    }


    public Resolution newPostForm(){
        HttpSession session = context.getRequest().getSession();
        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");

        if(accountBean == null || !accountBean.isAuthenticated()){
            setMessage("You must sign on before attempting to check out.  Please sign on and try checking out again.");
            return new ForwardResolution(AccountActionBean.class);
        }else{
            board.setUserid(accountBean.getUsername());
            return new ForwardResolution(WRITE_POST);
        }
    }

    public Resolution newPost(){
        HttpSession session = context.getRequest().getSession();
        HttpServletRequest request = context.getRequest();

        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");

        board.setUserid(accountBean.getUsername());
        board.setTitle(request.getParameter("title"));
        board.setContents(request.getParameter("contents"));

        boardService.insertPost(board);

        return new ForwardResolution(VIEW_POST);
    }

    public Resolution boardListForm(){

        return new ForwardResolution(BOARD_LIST);
    }

    public Resolution listBoards(){
        HttpSession session = context.getRequest().getSession();
        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
        boardList = boardService.getBoardList();
        return new ForwardResolution(BOARD_LIST);
    }

    public Resolution viewPost(){
        HttpSession session = context.getRequest().getSession();
        HttpServletRequest request = context.getRequest();

        board = boardService.getBoard(bno);
        return new ForwardResolution(VIEW_POST);
    }

}
