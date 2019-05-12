package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.Score;
import java.util.List;

public interface ScoreMapper extends MyMapper<Score> {

    List<Score> findScores(Score score);
}