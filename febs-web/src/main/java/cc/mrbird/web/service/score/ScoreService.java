package cc.mrbird.web.service.score;
import cc.mrbird.common.service.IService;
import cc.mrbird.web.domain.Score;

import java.util.List;

public interface ScoreService extends IService<Score> {


	List<Score> findScores(Score score);

	Score checkScore(Long stuId,Long subId);

	Score findById(Long id);
	
	void addScore(Score score);
	
	void updateScore(Score score);

	void deleteScores(String scores);
}
