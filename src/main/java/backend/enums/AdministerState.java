package backend.enums;

import javax.persistence.Table;

@Table(name = "administerState")
public enum AdministerState {
    REGISTERING, JUNIOR_CHECKING, SENIOR_CHECKING
}
