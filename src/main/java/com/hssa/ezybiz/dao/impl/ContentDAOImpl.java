package com.hssa.ezybiz.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hssa.ezybiz.dao.ContentDAO;
import com.hssa.ezybiz.dto.Content;
import com.hssa.ezybiz.dto.ContentImage;
import com.hssa.ezybiz.dto.Igimage;
import com.hssa.ezybiz.utils.LoadJPAFQueries;


@Repository
public class ContentDAOImpl extends JdbcDaoSupport implements ContentDAO {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public List<Content> getAll(Integer companyId) {

		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_ALL_Content"), new Object[] { companyId },
				BeanPropertyRowMapper.newInstance(Content.class));

	}

	@Override
	public int insertContent(Content content) {

		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("INSERT_Content"),
				new Object[] { content.getId(), content.getCreateDate(), content.getCreator(), content.getEntityUid(),
						content.getModifier(), content.getUpdateDate(), content.getOptLock(), content.getPosition(),
						content.getStatus(), content.getTemplate(), content.getDetailTextId(), content.getShortTextId(),
						content.getIsDeleted(), content.getCompanyId(), content.getSalesOfficeId(),
						content.getSalesRegionId() });
	}

	@Override
	public int updateContent(Content content) {

		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("UPDATE_Content_BY_ID"),
				new Object[] { content.getModifier(), content.getUpdateDate(), content.getOptLock(),
						content.getPosition(), content.getStatus(), content.getTemplate(), content.getDetailTextId(),
						content.getShortTextId(), content.getIsDeleted(), content.getId() });
	}

	@Override
	public Content findContentById(Integer id) {
		return (Content) getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("SELECT_Content_BY_ID"),
				new Object[] { id }, new BeanPropertyRowMapper<Content>(Content.class));
	}

	@Override
	public int insertContentImage(ContentImage contentImage) {
		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("INSERT_Content_Image"),
				new Object[] { contentImage.getId(), contentImage.getCreateDate(), contentImage.getCreator(),
						contentImage.getEntityUid(), contentImage.getModifier(), contentImage.getUpdateDate(),
						contentImage.getContentId(), contentImage.getImageId(), });
	}

	@Override
	public List<ContentImage> getImagesByContentId(Integer id) {
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_ContentImage_By_ContentId"),
				new Object[] { id }, BeanPropertyRowMapper.newInstance(ContentImage.class));
	}

	@Override
	public List<Igimage> getImageList(String pageName, Integer companyId) {
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_Page_Images"),
				new Object[] { pageName, companyId }, BeanPropertyRowMapper.newInstance(Igimage.class));
	}

	@Override
	public List<ContentImage> findContentImagesById(Integer id) {
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_ContentImage_By_ContentId"),
				new Object[] { id }, new BeanPropertyRowMapper<ContentImage>(ContentImage.class));
	}

	@Override
	public String getPageContent(String pageName, Integer companyId) {
		return getJdbcTemplate().queryForObject(LoadJPAFQueries.getQueryById("SELECT_Page_Content"),
				new Object[] { pageName, companyId }, String.class);
	}

	@Override
	public int deleteImageById(Integer imageId) {
		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("DELETE_Igimage_BY_ID"), new Object[] { imageId });
	}

	@Override
	public int deleteContentImage(Integer contentId, Integer imageId) {
		return getJdbcTemplate().update(LoadJPAFQueries.getQueryById("DELETE_ContentImage"),
				new Object[] { contentId, imageId });
	}

	@Override
	public Content findContentByTemplate(String template, Integer companyId) {
		List<Content> lst = getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_Content_BY_Template"),
				new Object[] { template, companyId }, BeanPropertyRowMapper.newInstance(Content.class));
		if (lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Content findContentBySalesRegion(String template, Integer salesRegionId, Integer salesOfficeId,
			Integer companyId) {
		List<Content> lst = null;
		if (salesRegionId != null && salesOfficeId != null) {
			lst = getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_Content_BY_SalesRegion_SalesOffice"),
					new Object[] { template, salesOfficeId, salesRegionId, companyId },
					BeanPropertyRowMapper.newInstance(Content.class));
		} else if (salesRegionId != null) {
			lst = getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_Content_BY_SalesRegion"),
					new Object[] { template, salesRegionId, companyId },
					BeanPropertyRowMapper.newInstance(Content.class));
		} else {
			lst = getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_Content_BY_Template"),
					new Object[] { template, companyId }, BeanPropertyRowMapper.newInstance(Content.class));
		}

		/*
		 * if(null == lst || lst.isEmpty()){ lst =
		 * getJdbcTemplate().query(LoadJPAFQueries.getQueryById(
		 * "SELECT_Content_BY_Template"), new Object[]{template,
		 * companyId},BeanPropertyRowMapper.newInstance(Content.class)); }
		 */

		if (lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}

	}

	@Override
	public List<Igimage> getDashboardImages(String template, Integer salesRegionId, Integer salesOfficeId,
			Integer companyId) {
		List<Igimage> lst = new ArrayList<>();
		if (salesRegionId != null && salesOfficeId != null) {
			lst = getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_Page_Images_By_SalesRegion_SalesOffice"),
					new Object[] { template, companyId, salesOfficeId, salesRegionId },
					BeanPropertyRowMapper.newInstance(Igimage.class));
		} else if (salesRegionId != null) {
			lst = getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_Page_Images_By_SalesRegion"),
					new Object[] { template, companyId, salesRegionId },
					BeanPropertyRowMapper.newInstance(Igimage.class));
		} else {
			lst = getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_Page_Images"),
					new Object[] { template, companyId }, BeanPropertyRowMapper.newInstance(Igimage.class));
		}

		return lst;
	}

	public class ContentResultSetExtractor implements ResultSetExtractor<Map<String, Content>> {

		public Map<String, Content> extractData(ResultSet rs) throws SQLException, DataAccessException {
			// List<Map<String,Content>> contentList = new ArrayList<Map<String,Content>>();
			Content currentContent = null;
			Map<String, Content> map = new HashMap<String, Content>();
			List<Igimage> imageList = new ArrayList<Igimage>();
			Igimage img = null;
			while (rs.next()) {
				int id = rs.getInt("id");
				if (currentContent == null) { // initial object
					currentContent = mapContent(rs);
					img = mapIgimage(rs);
					imageList.add(img);
				} else if (currentContent.getId() != id) { // break
					currentContent.setImageList(imageList);
					imageList = new ArrayList<Igimage>();
					map.put(currentContent.getTemplate(), currentContent);
					// contentList.add(map);
					currentContent = mapContent(rs);
					img = mapIgimage(rs);
					imageList.add(img);
				} else {
					img = mapIgimage(rs);
					imageList.add(img);
				}
			}
			if (currentContent != null) {
				currentContent.setImageList(imageList);
				map.put(currentContent.getTemplate(), currentContent);
			} // contentList.add(map);

			return map;
		}

		Content mapContent(ResultSet rs) throws SQLException {
			Content content = new Content();
			content.setId(rs.getInt("id"));
			content.setContentText(rs.getString("CONTENTTEXT"));
			content.setCompanyId(rs.getInt("COMPANYID"));
			content.setTemplate(rs.getString("TEMPLATE"));
			content.setUrl(rs.getString("CONTENTURL"));

			return content;
		}

		Igimage mapIgimage(ResultSet rs) throws SQLException {
			Igimage img = new Igimage();
			img.setId(rs.getInt("IGIMAGEID"));
			img.setDescription(rs.getString("DESCRIPTION"));
			img.setImagePath(rs.getString("IMAGEPATH"));
			img.setUrl(rs.getString("IMAGEURL"));
			return img;
		}

	}

	// public class ContentMapper implements RowMapper<List<Content>> {
	//
	// public List<Content> mapRow(ResultSet rs, int rowNum) throws SQLException {
	// ContentResultSetExtractor extractor = new ContentResultSetExtractor();
	// return extractor.extractData(rs);
	// }
	//
	// }

	@Override
	public Map<String, Content> findContentByCompanyID(int companyid) {
		System.out.println("companyid==="+companyid);
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_CONTENT_BY_companyID"),
				new Object[] { companyid }, new ContentResultSetExtractor());

	}
	
	@Override
	public Map<String, Content> findContentForLoginPage() {
		return getJdbcTemplate().query(LoadJPAFQueries.getQueryById("SELECT_CONTENT_For_Login_Page"),
				new Object[] {}, new ContentResultSetExtractor());

	}

	@Override
	public List<Map<String, Object>> getDistinctContentHeader(int companyId) {
		return getJdbcTemplate().queryForList(LoadJPAFQueries.getQueryById("SELECT_DISTINCT_CONTENT_HEADER"),
				new Object[] { companyId });

	}
}
