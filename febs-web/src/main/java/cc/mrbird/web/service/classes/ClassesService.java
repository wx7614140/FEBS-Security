package cc.mrbird.web.service.classes;
import cc.mrbird.common.service.IService;
import cc.mrbird.web.domain.Class;

import java.util.List;

public interface ClassesService extends IService<Class> {


	List<Class> findAllClasses(Class clazz);
	List<Class> findClasses(Class clazz);
	Class findByName(String className);

	Class findById(Long classId);
	
	void addClass(Class clazz);
	
	void updateClass(Class clazz);

	void deleteClasses(String clazz);
}
