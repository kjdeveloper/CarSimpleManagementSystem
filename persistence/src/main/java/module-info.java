module persistence {
    exports persistence.model;
    exports persistence.repository.impl;
    exports persistence.model.enums;

    requires gson;
    requires java.sql;

    opens persistence.model;
}