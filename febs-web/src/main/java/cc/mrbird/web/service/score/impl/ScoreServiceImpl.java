package cc.mrbird.web.service.score.impl;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.web.dao.ScoreMapper;
import cc.mrbird.web.domain.Score;
import cc.mrbird.web.domain.Subject;
import cc.mrbird.web.service.score.ScoreService;
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

@Service("scoreService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ScoreServiceImpl extends BaseService<Score> implements ScoreService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ScoreMapper scoreMapper;
	@Override
	public List<Score> findAllScores(Score score) {
		try {
			return this.scoreMapper.findScores(score);
		} catch (Exception e) {
			log.error("获取课程列表失败", e);
			return new ArrayList<>();
		}

	}
	@Override
	public List<Score> findScores(Score score) {
		try {
			return this.scoreMapper.findScores(score);
		} catch (Exception e) {
			log.error("error", e);
			return new ArrayList<>();
		}
	}

	@Override
	public Score checkScore(Long stuId,Long subId) {
		Example example = new Example(Score.class);
		example.createCriteria().andCondition("stu_id =", stuId).andCondition("sub_id =", subId);
		List<Score> list = this.selectByExample(example);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	@Transactional
	public void addScore(Score score) {
		score.setCreateDate(new Date());
		this.save(score);
	}

	@Override
	@Transactional
	public void deleteScores(String ids) {
		List<String> list = Arrays.asList(ids.split(","));
		this.batchDelete(list, "id", Score.class);
	}

	@Override
	public Score findById(Long id) {
		return this.selectByKey(id);
	}

	@Override
	@Transactional
	public void updateScore(Score score) {
		this.updateNotNull(score);
	}

}
