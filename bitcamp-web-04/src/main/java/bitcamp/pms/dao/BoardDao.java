package bitcamp.pms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bitcamp.pms.domain.Board;
import bitcamp.pms.domain.Member;

public class BoardDao {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public BoardDao(String jdbc, String username, String password) {
        this.jdbc = jdbc;
        this.username = username;
        this.password = password;
    }
    String jdbc;
    String username;
    String password;
    
    public List<Board> selectList() throws Exception {
        try (
            Connection con = DriverManager.getConnection(jdbc, username, password);
            PreparedStatement stmt = con.prepareStatement(
                    "select bno, titl, cont, cdt from pms2_board");
            ResultSet rs = stmt.executeQuery();) {
            ArrayList<Board> list = new ArrayList<>();
            while (rs.next()) {
                Board board = new Board();
                board.setNo(rs.getInt("bno"));
                board.setTitle(rs.getString("titl"));
                board.setDate(rs.getString("cdt"));
                list.add(board);
            }
            return list;
        }
    }
    
    public Board selectOne(String no) throws Exception {
        try (
            Connection con = DriverManager.getConnection(jdbc, username, password);
            PreparedStatement stmt = con.prepareStatement(
                "select bno, titl, cont, cdt  from pms2_board where bno=?");) {
            stmt.setString(1, no);
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    Board board = new Board();
                    board.setNo(rs.getInt("bno"));
                    board.setTitle(rs.getString("titl"));
                    board.setContents(rs.getString("cont"));
                    board.setDate(rs.getString("cdt"));
                    return board;
                } else {
                    return null;
                }
            }
        }
    }
    
    public void add(Board board) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (
                Connection con = DriverManager.getConnection(jdbc, username, password);
                PreparedStatement stmt = con.prepareStatement(
                "insert into pms2_board(titl,cont,cdt) values(?,?,now())");) {
            
            stmt.setString(1, board.getTitle());
            stmt.setString(2, board.getContents());
            stmt.executeUpdate();
        }
    }
    
    public int update(Board board) throws Exception {
        try (
            Connection con = DriverManager.getConnection(jdbc, username, password);
            PreparedStatement stmt = con.prepareStatement(
                    "update pms2_board set titl=?, cont=?, cdt=now() where bno=?");) {
            stmt.setString(1, board.getTitle());
            stmt.setString(2, board.getContents());
            stmt.setInt(3, board.getNo());
            return stmt.executeUpdate();
        }
    }
    public int delete(String no) throws Exception {
        try (
                Connection con = DriverManager.getConnection(jdbc, username, password);
                PreparedStatement stmt = con.prepareStatement(
                        "delete from pms2_board where bno=?");) {
                stmt.setString(1, no);
                return stmt.executeUpdate();
            }  
    }
}
