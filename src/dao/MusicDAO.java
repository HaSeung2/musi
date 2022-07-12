package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import dto.MusicDTO;

public class MusicDAO {
	Connection conn;
	ResultSet rs;
	PreparedStatement ps;
	public MusicDAO() {
		conn = DBConnection.getConnection();
	}
	public boolean addMusic(MusicDTO song) {
		String sql = "insert into song(songname, sing, songlyrics, songlyricist, songcomposer, songtime, musicianid) values(?,?,?,?,?,now(),?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, song.songname);
			ps.setString(2, song.sing);
			ps.setString(3, song.songlyrics);
			ps.setString(4, song.songlyricist);
			ps.setString(5, song.songcomposer);
			ps.setString(6, Session.get("login_id"));
			
			return ps.executeUpdate() == 1;
			
		} catch (SQLException e) {
			System.out.println("노래 등록 오류");
//			System.out.println(e);
		}
		return false;
	}
	public String getMyMusic() {
		String sql = "select * from song where musicianid = ? ";
		String result = "";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Session.get("login_id"));
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result += rs.getString(1)+"\t"+ rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5).substring(0, 6)+"...";
			}
			return result;
			
		} catch (SQLException e) {
			System.out.println("내 음악 리스트 가져오기 실패");
		}
		return result;
	}
	public boolean modifyMusicname(String music, String musics) {
		String sql = "update song set songname = ? where musicianid = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, musics);
			ps.setString(2, Session.get("login_id"));
			
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			System.out.println("음악명 변경 오류");
//			System.out.println(e);
		}
		return false;
	}
	public boolean deleteMusic(String music) {
		String sql = "delete from song where songname = ? and musicianid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, music);
			ps.setString(2, Session.get("login_id"));
			
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			System.out.println("음악 삭제 오류");
		}
		return false;
	}
	public boolean modifyMusic(String music, int choice, String result) {
		String [] date = {"songname","sing", "songlyrics", "songlyricist", "songcomposer"};
		String sql = "update song set " + date[choice-1] + " = ? where songname = ? and musicianid = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, result);
			ps.setString(2, music);
			ps.setString(3, Session.get("login_id"));
			
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			System.out.println("음악명 변경 오류");
			System.out.println(e);
		}
		return false;
	}
	public boolean deleteMyMusic() {
		String sql = "delete from song where musicianid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Session.get("login_id"));
			
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			System.out.println("내 모든 노래 삭제 실패");
		}
		return false;
	}
	public String getList() {
		String sql = "select * from song order by songlickcnt desc";
		String result = "";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result += rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5).substring(0, 6)+"..."+"\t"+rs.getString(8)+"\n";
			}
			return result;
		} catch (SQLException e) {
			System.out.println("뮤지 차트 오류"+e);
		}
		return result;
	}
	public String searchSong(String songs) {
		String sql = "select * from song where songname like ? ";
		String result = ""; 
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+songs+"%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result += rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5).substring(0, 6)+"..."+"\t"+rs.getString(8)+"\n";
			}
			return result;
		} catch (SQLException e) {
		}
		return result;
	}
	public String song(String attentisong) {
		String sql = "select * from song where songname = ?";
		String result = "";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, attentisong);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result += rs.getString(1);
				return result;
			}
		} catch (SQLException e) {
		}
		return result;
	}
	public String sing(String attentisong) {
		String sql = "select * from song where songname = ?";
		String result = "";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, attentisong);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result += rs.getString(2);
				return result;
			}
		} catch (SQLException e) {
		}
		return result;
	}
	public boolean likeup(String attentisong) {
		String sql = "update song set songlickcnt = songlickcnt + 1 where songname = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, attentisong);
			
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			System.out.println("좋아요 오류");
		}
		return false;
	}
	public String songcomposer(String attentisong) {
		String sql = "select * from song where songname = ?";
		String result = "";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, attentisong);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result += rs.getString(5);
				return result;
			}
		} catch (SQLException e) {
			System.out.println("가사 보기 오류");
		}
		return result;
	}
}
