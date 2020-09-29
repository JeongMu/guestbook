package org.edwith.webbe.guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.edwith.webbe.guestbook.dto.Guestbook;
import org.edwith.webbe.guestbook.util.DBUtil;

public class GuestbookDao {
    public List<Guestbook> getGuestbooks(){
        List<Guestbook> list = new ArrayList<>();
        
        String sql = "SELECT id, name, content, regdate FROM guestbook";
        try (Connection conn = DBUtil.getConnection();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
        	try (ResultSet rs = ps.executeQuery()) {
        		
        		while(rs.next()) {
        			Long id = rs.getLong("id");
        			String name = rs.getString("name");
        			String content = rs.getString("content");
        			Date regdate = rs.getDate("regdate");
        			Guestbook guestbook = new Guestbook(id, name, content, regdate);
        			list.add(guestbook);
        		} 
        	} catch (Exception e) {
				e.printStackTrace();
			}
        } catch (Exception ex) {
			ex.printStackTrace();
		}

        return list;
    }

    public void addGuestbook(Guestbook guestbook){
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
        	conn = DBUtil.getConnection();
        	
        	String sql = "INSERT INTO guestbook (id, name, content, regdate) VALUES ( ?, ?, ?, ? )";
        	
        	ps = conn.prepareStatement(sql);
        	
        	ps.setLong(1, guestbook.getId());
        	ps.setString(2, guestbook.getName());
        	ps.setString(3, guestbook.getContent());
        	ps.setDate(4, new java.sql.Date(guestbook.getRegdate().getTime()));
        	
        	ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	if(ps != null) {
        		try {
        			ps.close();
        		} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        	
        	if(conn != null) {
        		try {
        			conn.close();
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
        	}
        }
    }
    
    public Long findAvailableId() {
    	List<Long> list = new ArrayList<>();
        
        String sql = "SELECT id FROM guestbook";
        try (Connection conn = DBUtil.getConnection();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
        	try (ResultSet rs = ps.executeQuery()) {
        		
        		while(rs.next()) {
        			Long id = rs.getLong("id");
        			list.add(id);
        		} 
        	} catch (Exception e) {
				e.printStackTrace();
			}
        } catch (Exception ex) {
			ex.printStackTrace();
		} 
        
        Long max = Collections.max(list);
    	
    	return max + 1;
    }
}
