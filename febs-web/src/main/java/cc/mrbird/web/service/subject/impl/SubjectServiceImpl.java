package cc.mrbird.web.service.subject.impl;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.web.dao.SubjectMapper;
import cc.mrbird.web.domain.Subject;
import cc.mrbird.web.service.subject.SubjectService;
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

@Service("subjectService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SubjectServiceImpl extends BaseService<Subject> implements SubjectService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SubjectMapper subjectMapper;

	@Override
	public List<Subject> findSubjects(Subject subject) {
		try {
			return this.subjectMapper.findSubjects(subject);
		} catch (Exception e) {
			log.error("error", e);
			return new ArrayList<>();
		}
	}

	@Override
	public Subject findByName(String name,Long gradeId) {
		Example example = new Example(Subject.class);
		Example.Criteria c= example.createCriteria().andCondition("lower(name) =", name.toLowerCase());
		if(gradeId!=null){
			c.andCondition("grade_id=",gradeId);
		}
		List<Subject> list = this.selectByExample(example);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	@Transactional
	public void addSubject(Subject subject) {
		subject.setCreateDate(new Date());
		this.save(subject);
	}

	@Override
	@Transactional
	public void deleteSubjects(String ids) {
		List<String> list = Arrays.asList(ids.split(","));
		this.batchDelete(list, "id", Subject.class);
	}

	@Override
	public Subject findById(Long id) {
		return this.selectByKey(id);
	}

	@Override
	@Transactional
	public void updateSubject(Subject subject) {
		this.updateNotNull(subject);
	}

}
