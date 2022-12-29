package dialect;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class MySqlDialect extends MySQL5Dialect {

    public MySqlDialect() {
        registerFunction("group_concat", new StandardSQLFunction("group_concat",
                StandardBasicTypes.STRING));
    }
}
