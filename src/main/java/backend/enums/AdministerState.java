package backend.enums;

import javax.persistence.Table;

//@Table(name = "administerState") 枚举应该不用存到数据库里
public enum AdministerState {
    REGISTERING, JUNIOR_CHECKING, SENIOR_CHECKING
}
