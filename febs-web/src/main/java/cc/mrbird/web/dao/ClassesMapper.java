package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.Class;

import java.util.List;

public interface ClassesMapper extends MyMapper<Class> {

    List<Class> findClasses(Class clazz);
}