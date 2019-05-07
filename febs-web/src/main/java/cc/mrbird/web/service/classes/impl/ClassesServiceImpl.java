package cc.mrbird.web.service.classes.impl;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.web.domain.Class;
import cc.mrbird.web.dao.ClassesMapper;
import cc.mrbird.web.service.classes.ClassesService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("classesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ClassesServiceImpl extends BaseService<Class> implements ClassesService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ClassesMapper classesMapper;
	@Override
	public List<Class> findAllClasses(Class clazz) {
		try {
			Example example = new Example(Class.class);
			if (StringUtils.isNotBlank(clazz.getClassName())) {
				example.createCriteria().andCondition("class_name=", clazz.getClassName());
			}
			if (clazz.getGradeId()!=null) {
				example.createCriteria().andCondition("grade_id=", clazz.getGradeId());
			}
			return this.selectByExample(example);
		} catch (Exception e) {
			log.error("获取班级列表失败", e);
			return new ArrayList<>();
		}

	}

	@Override
	public List<Class> findClasses(Class clazz) {
		try {
			return this.classesMapper.findClasses(clazz);
		} catch (Exception e) {
			log.error("error", e);
			return new ArrayList<>();
		}
	}

	@Override
	public Class findByName(String className) {
		Example example = new Example(Class.class);
		example.createCriteria().andCondition("lower(class_name) =", className.toLowerCase());
		List<Class> list = this.selectByExample(example);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	@Transactional
	public void addClass(Class clazz) {
		clazz.setCreateTime(new Date());
		this.save(clazz);
	}

	@Override
	@Transactional
	public void deleteClasses(String classIds) {
		List<String> list = Arrays.asList(classIds.split(","));
		this.batchDelete(list, "classId", Class.class);
	}

	@Override
	public Class findById(Long classId) {
		return this.selectByKey(classId);
	}

	@Override
	@Transactional
	public void updateClass(Class clazz) {
		this.updateNotNull(clazz);
	}

}
