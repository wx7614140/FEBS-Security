package cc.mrbird.web.service.subject;
import cc.mrbird.common.service.IService;
import cc.mrbird.web.domain.Subject;

import java.util.List;

public interface SubjectService extends IService<Subject> {


	List<Subject> findAllSubjects(Subject subject);
	List<Subject> findSubjects(Subject subject);
	Subject findByName(String name);

	Subject findById(Long id);
	
	void addSubject(Subject subject);
	
	void updateSubject(Subject subject);

	void deleteSubjects(String subject);
}
