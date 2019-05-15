package cc.mrbird.web.service.subject;
import cc.mrbird.common.service.IService;
import cc.mrbird.web.domain.Subject;

import java.util.List;

public interface SubjectService extends IService<Subject> {


	List<Subject> findSubjects(Subject subject);
	Subject findByName(String name,Long gradeId);

	Subject findById(Long id);
	
	void addSubject(Subject subject);
	
	void updateSubject(Subject subject);

	void deleteSubjects(String subject);
}
