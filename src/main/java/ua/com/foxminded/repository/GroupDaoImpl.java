package ua.com.foxminded.repository;

import lombok.extern.log4j.Log4j;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.Group;

import java.util.Comparator;
import java.util.List;

@Log4j
@Transactional
public class GroupDaoImpl extends AbstractDao<Group> implements GroupDao {
    public GroupDaoImpl(){
        setClazz(Group.class);
    }

    @Override
    public List<Group> getAll(){
        List<Group> list = super.getAll();
        list.sort(Comparator.comparing(Group::getId));
        return list;
    }
}
