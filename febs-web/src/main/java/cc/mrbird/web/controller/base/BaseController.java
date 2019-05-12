package cc.mrbird.web.controller.base;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.security.domain.FebsSocialUserDetails;
import cc.mrbird.security.domain.FebsUserDetails;
import cc.mrbird.system.domain.MyUser;
import cc.mrbird.web.util.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BaseController {

	private Map<String, Object> getDataTable(PageInfo<?> pageInfo) {
		Map<String, Object> rspData = new HashMap<>();
		rspData.put("rows", pageInfo.getList());
		rspData.put("total", pageInfo.getTotal());
		return rspData;
	}

	protected Map<String, Object> selectByPageNumSize(QueryRequest request, Supplier<?> s) {
		//加入排序功能
		if(StringUtils.isNotBlank(request.getSortColumn())){
			String column=StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(request.getSortColumn()),"_").toLowerCase();
			StringUtils.splitByCharacterTypeCamelCase(request.getSortColumn());
			String _sort=StringUtils.isNotBlank(request.getOrder())?(request.getAlias()+"."+column+" "+request.getOrder()):(request.getAlias()+"."+column);
			PageHelper.startPage(request.getPageNum(), request.getPageSize(),_sort);
		}else{
			PageHelper.startPage(request.getPageNum(), request.getPageSize());
		}
		PageInfo<?> pageInfo = new PageInfo<>((List<?>) s.get());
		PageHelper.clearPage();
		return getDataTable(pageInfo);
	}

	protected MyUser getCurrentUser(){
		return UserUtils.getCurrentUser();
	}
}
