package cc.mrbird.web.service.classes;
import cc.mrbird.common.service.IService;
import cc.mrbird.domain.Class;

import java.util.List;

public interface ClassesService extends IService<cc.mrbird.domain.Class> {


	List<cc.mrbird.domain.Class> findAllClasses(cc.mrbird.domain.Class clazz);

	cc.mrbird.domain.Class findByName(String className);

	cc.mrbird.domain.Class findById(Long classId);
	
	void addClass(cc.mrbird.domain.Class clazz);
	
	void updateClass(cc.mrbird.domain.Class clazz);

	void deleteClasses(String clazz);
}
