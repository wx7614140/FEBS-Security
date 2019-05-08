package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.Class;
import cc.mrbird.web.domain.Subject;

import java.util.List;

public interface SubjectMapper extends MyMapper<Subject> {

    List<Subject> findSubjects(Subject subject);
}