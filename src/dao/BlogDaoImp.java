package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Blog;
import utility.ConnectionManager;

public class BlogDaoImp implements BlogDaoInterface {
	final private  String INSERT_BLOG_QUERY = "INSERT INTO BLOg_BLOG (blogid, blogTitle, blogDescription, postedOn) VALUES (seq_blog_blog.nextval, ?, ?, ?)";
	  final String SELECT_ALL_BLOGS ="SELECT * FROM BLOG_BLOG";
	  final String  DELETE_BLOG_ID="DELETE FROM BLOG_BLOG WHERE blogid=?";
	
	  
	  @Override
	  public void insertBlog(Blog blog) throws Exception {
		
		
	            System.out.println(INSERT_BLOG_QUERY);
		try{
			Connection con = ConnectionManager.getConnection();
			PreparedStatement ps= con.prepareStatement(INSERT_BLOG_QUERY);
//			ps.setInt(1, blog.getBlogId());
			ps.setString(1, blog.getBlogTitle());
			ps.setString(2,blog.getBlogDescription());
			ps.setDate(3,java.sql.Date.valueOf(blog.getPostedOn()));
			System.out.println(ps);
			ps.executeUpdate();
			con.close();
		}
		catch(SQLException | ClassNotFoundException e) 
		{
		System.out.println(e);
		}
	}


	@Override
	public Blog selectBlog(int blogId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Blog> selectAllBlogs() throws Exception {
		Blog blog = null;
		List <Blog> bloglist = new ArrayList<>();
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_ALL_BLOGS);
		System.out.println(ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			int Id = rs.getInt("blogid");
			String blogTitle = rs.getString("blogTitle");
			String blogDescription = rs.getString("blogDescription");			
			LocalDate postedOn = rs.getDate("postedOn").toLocalDate();
			
			blog = new Blog();
			blog.setBlogId(Id);
			blog.setBlogTitle(blogTitle);
			blog.setBlogDescription(blogDescription);
			blog.setPostedOn(postedOn);
			
			bloglist.add(blog);
		}
		return bloglist;
	}

	
	@Override
	public boolean updateBlog(Blog blog) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteBlog(int id) throws Exception {
		System.out.println(id);
		boolean rowDeleted;
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(DELETE_BLOG_ID);
		ps.setInt(1, id);
		rowDeleted= ps.executeUpdate()>0;
		
		return rowDeleted;
	}

	
}
